package com.r2d2.pecan.service.quartz;


import com.r2d2.pecan.common.constant.PecanConstant;
import com.r2d2.pecan.common.enums.JobLogStatusEnum;
import com.r2d2.pecan.common.exception.CommonErrorCode;
import com.r2d2.pecan.common.exception.ServiceException;
import com.r2d2.pecan.common.utils.CharacterUtil;
import com.r2d2.pecan.common.utils.DateUtil;
import com.r2d2.pecan.common.utils.RedisManager;
import com.r2d2.pecan.dao.manager.JobConfigManager;
import com.r2d2.pecan.dao.manager.JobLogManager;
import com.r2d2.pecan.dao.model.JobConfigDO;
import com.r2d2.pecan.dao.model.JobLogDO;
import lombok.extern.slf4j.Slf4j;
import org.quartz.JobDataMap;
import org.quartz.JobExecutionContext;
import org.springframework.beans.factory.annotation.Autowired;

import java.util.Date;



/**
 * job定时任务执行抽象
 *
 * <p>
 * 1、服务执行方法
 * 2、执行定时任务
 * 3、判断任务执行状态
 * 4、任务执行成功
 * 5、任务执行失败
 * 6、获取任务配置参数
 * </p>
 */
@Slf4j
public abstract class WorkFactory {

    /**
     * 定时任务Manager服务
     */
    @Autowired
    private QuartzJobBiz quartzManager;

    /**
     * 定时任务配置Manager服务
     */
    @Autowired
    private JobConfigManager jobConfigManager;

    /**
     * 定时任务日志Manager服务
     */
    @Autowired
    private JobLogManager jobLogManager;

    /**
     * ZK锁
     */
 //   @Autowired
 //   private ZookeeperLock zookeeperLock;

    /**
     * Redis Manager服务
     */
    @Autowired
    private RedisManager redisManager;

    /**
     * 1、服务执行方法
     *
     * @param context JOB执行上下文
     */
    protected void process(JobExecutionContext context, Task task) {

        String patch = null;
        //InterProcessMutex lock = null;
        JobConfigDO jobConfigDO = null;
        try {
            //获取JOB配置（实时）
            jobConfigDO = getJobDetailBO(context);
            log.info("jobConfig========{}",jobConfigDO);
           // patch = LockPathUtil.getJobZkPath(jobConfigDO.getJobName(), DateUtil.getCurrentDate());
           // lock = zookeeperLock.createInterProcessMutex(patch);
          //  zookeeperLock.acquire(lock);

          /*  if (StringUtils.isNotEmpty(redisManager.queryObjectByKey(
                    GlobalRedisKey.CURRENT_JOB_KEY + jobConfigDO.getJobName()))) {
                log.warn("定时任务运行中：{}", jobConfigDO.getJobDesc());
                return;
            }
            redisManager.insertObject(DateUtil.getCurrent(),
                    GlobalRedisKey.CURRENT_JOB_KEY + jobConfigDO.getJobName(), 1000 * 60 * 5);
            */
            execute(context, task, jobConfigDO);
        } catch (Exception e) {
            log.error("定时任务执行异常：{}", e);
        } finally {
          ///  zookeeperLock.release(lock, patch);
            log.info("job 执行结束：{} 释放锁 ", jobConfigDO == null ? "[NULL_JOB]" : jobConfigDO.getJobDesc());
        }
    }

    /**
     * 2、执行定时任务
     *
     * @param context 执行定时配置
     * @param task    具体执行定时任务类
     */
    private void execute(JobExecutionContext context, Task task, JobConfigDO jobConfigDO) {

        long startTime = System.currentTimeMillis();
        try {
            String status = quartzManager.scheduleJob(jobConfigDO);
            if (null == status) {
                log.error("定时任务调度{}获取执行状态异常，Status:为空", jobConfigDO);
                return;
            }
            log.info("定时任务：{}，数据库状态：{},内存状态:{}", jobConfigDO.getJobDesc(), jobConfigDO.getStatus(), status);
            // 锁定定时任务配置信息、新增定时任务执行日志
            if (jobConfigManager.queryLockJobConfig(jobConfigDO)) {
                log.warn("定时任务{}运行中不允许重复执行 ", jobConfigDO.getJobDesc());
                return;
            }
            if (runnable(jobConfigDO)) {
                log.warn("定时任务当天运行次数超限:{}，{}", jobConfigDO.getJobDesc(), jobConfigDO.getJobExecCount());
                jobConfigDO.getJobLogDO().setErrorMsg("定时任务当天运行次数超限!");
                jobConfigDO.setJobUsedTime(System.currentTimeMillis() - startTime);
                success(context, jobConfigDO);
                return;
            }

            log.info("定时任务执行开始：{}", jobConfigDO.getJobDesc());
            task.beforeWorker(jobConfigDO);
            task.doWorker(jobConfigDO);
            task.afterWorker(jobConfigDO);

            jobConfigDO.getJobLogDO().setErrorMsg("成功!");
            jobConfigDO.setJobUsedTime(System.currentTimeMillis() - startTime);
            success(context, jobConfigDO);
            log.info("定时任务执行成功：{}", jobConfigDO.getJobDesc());
        } catch (Exception e) {
            log.error("定时任务执行失败：{}，异常：{}", jobConfigDO.getJobDesc(), e);
            if (null != jobConfigDO.getJobLogDO()) {
                jobConfigDO.setJobUsedTime(System.currentTimeMillis() - startTime);
                jobConfigDO.getJobLogDO().setErrorMsg(
                        CharacterUtil.substringByByte(e.getMessage(), 500, PecanConstant.ENCODING_CODE_UTF8));
                fail(context, jobConfigDO);
            }
        } finally {
            if (jobConfigDO != null) {
                jobConfigDO.setJobLogDO(null);
            }
        }
    }

    /**
     * 3、判断任务执行状态
     *
     * @param jobConfigDO JOB执行上下文
     * @return 业务执行标识
     */
    private boolean runnable(JobConfigDO jobConfigDO) {

        JobLogDO jobLogBO = jobConfigDO.getJobLogDO();
        int execTimes = jobLogManager.countByExecDate(jobLogBO);
        Long retryTimes = jobConfigDO.getRetryTimes() == null ? 0L : jobConfigDO.getRetryTimes();
        log.info("定时任务执行次数阀值校验，当前运行次数：{}，允许运行次数：{}",
                execTimes, retryTimes == 0 ? "无限制" : retryTimes);
        if (retryTimes > 0 && retryTimes < execTimes) {
            log.error("定时任务执行次数超限：{}，{}", retryTimes, execTimes);
            return true;
        }
        return false;
    }

    /**
     * 4、任务执行成功
     *
     * @param context JOB执行上下文
     */
    private void success(JobExecutionContext context, JobConfigDO jobConfigDO) {

        log.debug("定时任务执行成功，更新前参数:{}", jobConfigDO);

        //任务配置（交易日期、上次执行时间、下次执行时间、用时、执行次数）
        JobConfigDO modifyJobConfigDO = new JobConfigDO();
        modifyJobConfigDO.setJobNo(jobConfigDO.getJobNo());
        modifyJobConfigDO.setLastExecTime(context.getFireTime());
        modifyJobConfigDO.setNextExecTime(context.getNextFireTime());
        modifyJobConfigDO.setJobUsedTime(jobConfigDO.getJobUsedTime());
        modifyJobConfigDO.setUpdatedBy(PecanConstant.USER_SYSTEM);
        modifyJobConfigDO.setJobExecCount((jobConfigDO.getJobExecCount() == null ?
                0 : jobConfigDO.getJobExecCount()) + 1);

        //任务日志（运行结果、运行日期）
        JobLogDO jobLogDO = jobConfigDO.getJobLogDO();
        jobLogDO.setStatus(JobLogStatusEnum.FINISHED.getCode());
        jobLogDO.setExecDate(DateUtil.formatToDate(new Date(), DateUtil.datePattern));
        modifyJobConfigDO.setJobLogDO(jobLogDO);

        jobConfigManager.modifyJobConfig(modifyJobConfigDO);

        log.debug("定时任务执行成功，更新后参数:{} ", modifyJobConfigDO);
    }

    /**
     * 5、任务执行失败
     *
     * @param context     JOB执行上下文
     * @param jobConfigDO Job配置
     */
    private void fail(JobExecutionContext context, JobConfigDO jobConfigDO) {

        log.debug("定时任务执行失败，更新前参数:{}", jobConfigDO);

        //任务配置（交易日期、上次执行时间、下次执行时间、用时、执行次数）
        JobConfigDO modifyJobConfigDO = new JobConfigDO();
        modifyJobConfigDO.setJobNo(jobConfigDO.getJobNo());
        modifyJobConfigDO.setLastExecTime(context.getFireTime());
        modifyJobConfigDO.setNextExecTime(context.getNextFireTime());
        modifyJobConfigDO.setJobUsedTime(jobConfigDO.getJobUsedTime());
        modifyJobConfigDO.setUpdatedBy(PecanConstant.USER_SYSTEM);
        modifyJobConfigDO.setJobExecCount((jobConfigDO.getJobExecCount() == null ?
                0 : jobConfigDO.getJobExecCount()) + 1);

        //任务日志（运行结果、运行日期）
        JobLogDO jobLogDO = jobConfigDO.getJobLogDO();
        jobLogDO.setStatus(JobLogStatusEnum.EXCEPTION.getCode());
        jobLogDO.setExecDate(DateUtil.formatToDate(new Date(), DateUtil.datePattern));
        modifyJobConfigDO.setJobLogDO(jobLogDO);

        jobConfigManager.modifyJobConfig(modifyJobConfigDO);
        log.debug("定时任务执行失败，更新后参数:{}", modifyJobConfigDO);
    }

    /**
     * 6、获取任务配置参数
     *
     * @param context JOB执行上下文
     * @return JOB配置对象
     */
    private JobConfigDO getJobDetailBO(JobExecutionContext context) {

        JobDataMap jobDataMap = context.getJobDetail().getJobDataMap();

        if (jobDataMap == null || jobDataMap.get(PecanConstant.JOB_DATA_MAP) == null) {
            log.error("无法获取JOB_DATA_MAP，{}", jobDataMap);
            throw new ServiceException(CommonErrorCode.JOB_DATA_MAP_IS_NULL);
        }

        JobConfigDO jobConfigDO = (JobConfigDO) jobDataMap.get(PecanConstant.JOB_DATA_MAP);
        log.info("开始运行定时任务，JOB_NO:{}，JOB_DESC：{}", jobConfigDO.getJobNo(), jobConfigDO.getJobDesc());

        //每次从数据库中获取最新的JOB配置，不从内存中获取（避免存在信息不准的问题）
       /* jobConfigDO = jobConfigManager.queryJobConfig(jobConfigDO.getJobNo());
        log.info("实时获取最新定时任务配置信息：{}", jobConfigDO);
        if (jobConfigDO == null) {
            log.error("定时任务不存在");
            throw new ServiceException(CommonErrorCode.JOB_CONFIG_NOT_EXISTS);
        }*/
        return jobConfigDO;
    }
}