package com.r2d2.pecan.service.quartz;

import com.r2d2.pecan.common.constant.PecanConstant;
import com.r2d2.pecan.dao.model.JobConfigDO;
import lombok.extern.slf4j.Slf4j;
import org.quartz.*;
import org.quartz.impl.matchers.GroupMatcher;
import org.quartz.impl.triggers.CronTriggerImpl;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.ApplicationContext;
import org.springframework.scheduling.quartz.SchedulerFactoryBean;
import org.springframework.stereotype.Component;

import java.text.ParseException;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import java.util.Set;


/**
 * 定时任务管理服务
 * <p>
 * 1、运行/加入计划任务
 * 2、加入计划任务
 * 3、任务暂停
 * 4、任务移除
 * 5、立即执行任务，只会运行一次
 * 6、根据任务名、组名查询任务状态
 * 7、任务是否存在
 * 8、监控内存数据
 * </p>
 * User: LZQ Date: 2015/10/08 ProjectName: settleCenter Version: 1.0
 */
@Slf4j
@Component
public class QuartzJobBizImpl implements QuartzJobBiz {

    /**
     * Spring Scheduler
     */
    @Autowired
    private SchedulerFactoryBean schedulerFactoryBean;

    /**
     * spring 上下文
     */
    @Autowired
    private ApplicationContext context;

    /**
     * 1、运行/加入计划任务
     *
     * @param scheduleJob JOB基本信息参数
     * @throws SchedulerException     调度异常
     * @throws ParseException         解析异常
     * @throws ClassNotFoundException 类不存在异常
     */
    @Override
    public void modifyJob(JobConfigDO scheduleJob) throws SchedulerException, ParseException, ClassNotFoundException {

        TriggerKey triggerKey = TriggerKey.triggerKey(scheduleJob.getJobName(), scheduleJob.getJobGroup());
        CronTrigger trigger = (CronTrigger) schedulerFactoryBean.getScheduler().getTrigger(triggerKey);
        if (null == trigger) {
            addJob(scheduleJob);
            return;
        }

        Trigger.TriggerState triggerState = schedulerFactoryBean.getScheduler().getTriggerState(trigger.getKey());
        boolean pausedToNormal = Trigger.TriggerState.PAUSED.name().equals(triggerState.name())
                && Trigger.TriggerState.NORMAL.name().equals(scheduleJob.getStatus());
        boolean updateFlag = pausedToNormal || !trigger.getCronExpression().equals(scheduleJob.getJobCronExpress());

        log.info("JOB更新检查结果：{}，内存状态：{}，数据库状态：{}，内存Cron：{}，数据库Cron：{}", updateFlag ? "需要更新" :
                        "无需更新", triggerState.name(), scheduleJob.getStatus(), trigger.getCronExpression(),
                scheduleJob.getJobCronExpress());

        //修改条件：JOB状态由暂停状态改成正常状态 或 cron表达式变更
        if (updateFlag) {
            CronScheduleBuilder scheduleBuilder = CronScheduleBuilder.cronSchedule(scheduleJob.getJobCronExpress());
            trigger = trigger.getTriggerBuilder().withIdentity(triggerKey).withSchedule(scheduleBuilder).build();
            JobDetail jobDetail = schedulerFactoryBean.getScheduler().getJobDetail(trigger.getJobKey());

            log.info("【修改】定时任务：{}，{}，{}，{}", scheduleJob.getJobDesc(), scheduleJob.getJobCronExpress(),
                    scheduleJob.getJobCronExpressDesc(), scheduleJob.getStatus());
            jobDetail.getJobDataMap().put(PecanConstant.JOB_DATA_MAP, scheduleJob);
            //防止重置后立即执行
            ((CronTriggerImpl) trigger).setStartTime(new Date());
            //按新的trigger重新设置job执行,以当前时间为基准来调整下一次触发的时间
            schedulerFactoryBean.getScheduler().rescheduleJob(triggerKey, trigger);
        }
    }

    /**
     * 2、加入计划任务
     *
     * @param scheduleJob JOB基本信息参数
     * @throws SchedulerException     调度异常
     * @throws ParseException         解析异常
     * @throws ClassNotFoundException 类不存在异常
     */
    @Override
    public void addJob(JobConfigDO scheduleJob) throws SchedulerException, ParseException, ClassNotFoundException {

        Job bean = (Job) context.getBean(scheduleJob.getJobClass());
        if (bean == null) {
            log.error("定时任务不存在：{}", scheduleJob);
            throw new ClassNotFoundException("scheduleJob.getJobClass()");
        }

        JobDetail jobDetail = JobBuilder.newJob(bean.getClass()).withIdentity(
                scheduleJob.getJobName(), scheduleJob.getJobGroup()).build();

        log.info("【新增】定时任务：{}，{}，{}，{}", scheduleJob.getJobDesc(), scheduleJob.getJobCronExpress(),
                scheduleJob.getJobCronExpress(), scheduleJob.getStatus());

        jobDetail.getJobDataMap().put(PecanConstant.JOB_DATA_MAP, scheduleJob);
        CronScheduleBuilder scheduleBuilder = CronScheduleBuilder.cronSchedule(scheduleJob.getJobCronExpress());
        CronTrigger trigger = TriggerBuilder.newTrigger().withIdentity(
                scheduleJob.getJobName(), scheduleJob.getJobGroup())
                .withSchedule(scheduleBuilder).build();
        schedulerFactoryBean.getScheduler().scheduleJob(jobDetail, trigger);
    }

    /**
     * 3、任务暂停
     *
     * @param scheduleJob JOB基本信息参数
     * @throws SchedulerException 调度异常
     */
    @Override
    public void pauseJob(JobConfigDO scheduleJob) throws SchedulerException, ParseException, ClassNotFoundException {

        if (!isExistsJob(scheduleJob)) {
            return;
        }
        log.info("【暂停】定时任务：{}", scheduleJob);
        JobKey jobKey = JobKey.jobKey(scheduleJob.getJobName(), scheduleJob.getJobGroup());
        schedulerFactoryBean.getScheduler().pauseJob(jobKey);
    }

    /**
     * 4、任务移除
     *
     * @param scheduleJob JOB基本信息参数
     * @return TRUE/FALSE
     * @throws SchedulerException 调度异常
     */
    @Override
    public boolean removeJob(JobConfigDO scheduleJob)
            throws SchedulerException, ParseException, ClassNotFoundException {

        if (!isExistsJob(scheduleJob)) {
            return true;
        }
        log.info("【移除】定时任务：{}", scheduleJob);
        JobKey jobKey = JobKey.jobKey(scheduleJob.getJobName(), scheduleJob.getJobGroup());
        return schedulerFactoryBean.getScheduler().deleteJob(jobKey);
    }

    /**
     * 5、立即执行任务，只会运行一次
     *
     * @param scheduleJob JOB基本信息参数
     * @throws SchedulerException 调度异常
     */
    @Override
    public void triggerJob(JobConfigDO scheduleJob) throws SchedulerException, ParseException, ClassNotFoundException {

        if (!isExistsJob(scheduleJob)) {
            addJob(scheduleJob);
        }
        log.info("【运行】定时任务：{}", scheduleJob);
        JobKey jobKey = new JobKey(scheduleJob.getJobName(), scheduleJob.getJobGroup());
        JobDetail jobDetail = schedulerFactoryBean.getScheduler().getJobDetail(jobKey);
        JobDataMap jobDataMap = jobDetail.getJobDataMap();
        jobDataMap.put(PecanConstant.JOB_DATA_MAP, scheduleJob);
        schedulerFactoryBean.getScheduler().triggerJob(jobKey, jobDataMap);
    }

    /**
     * 6、根据任务名、组名查询任务状态
     *
     * @param scheduleJob JOB基本信息参数
     * @return 任务状态
     * @throws SchedulerException 运行中的任务列表
     */
    @Override
    public String scheduleJob(JobConfigDO scheduleJob) throws SchedulerException {

        String status = null;
        TriggerKey triggerKey = TriggerKey.triggerKey(scheduleJob.getJobName(), scheduleJob.getJobGroup());
        CronTrigger trigger = (CronTrigger) schedulerFactoryBean.getScheduler().getTrigger(triggerKey);

        if (null != trigger) {
            Trigger.TriggerState triggerState = schedulerFactoryBean.getScheduler().getTriggerState(trigger.getKey());
            status = triggerState.name();
        }
        return status;
    }

    /**
     * 7、任务是否存在
     *
     * @param scheduleJob JOB基本信息参数
     * @return True/False
     * @throws SchedulerException 调度异常
     */
    private boolean isExistsJob(JobConfigDO scheduleJob) throws SchedulerException {

        TriggerKey triggerKey = TriggerKey.triggerKey(scheduleJob.getJobName(), scheduleJob.getJobGroup());
        CronTrigger trigger = (CronTrigger) schedulerFactoryBean.getScheduler().getTrigger(triggerKey);

        if (null == trigger) {
            log.warn("触发器不存在:{}", scheduleJob);
            return false;
        }
        return true;
    }

    /**
     * 8、监控内存数据
     *
     * @return JOB配置集合
     * @throws SchedulerException
     * @throws ParseException
     * @throws ClassNotFoundException
     */
    public List<JobConfigDO> jobManager() throws SchedulerException, ParseException, ClassNotFoundException {

        List<JobConfigDO> jobConfigDOs = new ArrayList<JobConfigDO>();
        Scheduler scheduler = schedulerFactoryBean.getScheduler();
        for (String triggerGroupName : scheduler.getTriggerGroupNames()) {

            GroupMatcher<TriggerKey> groupMatcher = GroupMatcher.triggerGroupEquals(triggerGroupName);
            Set<TriggerKey> keys = scheduler.getTriggerKeys(groupMatcher);

            for (TriggerKey triggerKey : keys) {
                Trigger.TriggerState triggerState = schedulerFactoryBean.getScheduler().getTriggerState(triggerKey);
                log.info("内存数据监控：Group:{},Name:{},State:{}", triggerKey.getGroup(), triggerKey.getName(),
                        triggerState.name());
                JobConfigDO jobConfigDO = new JobConfigDO();
                jobConfigDO.setStatus(triggerState.name());
                jobConfigDO.setJobGroup(triggerKey.getGroup());
                jobConfigDO.setJobName(triggerKey.getName());
                jobConfigDOs.add(jobConfigDO);
            }
        }
        return jobConfigDOs;
    }
}