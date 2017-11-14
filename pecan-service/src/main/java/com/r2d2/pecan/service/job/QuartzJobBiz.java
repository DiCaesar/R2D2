package com.r2d2.pecan.service.job;

import com.r2d2.pecan.dao.model.JobConfigDO;
import org.quartz.SchedulerException;

import java.text.ParseException;
import java.util.List;

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
 */
public interface QuartzJobBiz {

    /**
     * 1、修改/重置计划任务
     *
     * @param scheduleJob JOB基本信息参数
     * @throws SchedulerException 调度异常信息
     */
    void modifyJob(JobConfigDO scheduleJob) throws SchedulerException, ParseException, ClassNotFoundException;

    /**
     * 2、加入计划任务
     *
     * @param scheduleJob JOB基本信息参数
     * @throws org.quartz.SchedulerException 调度异常信息
     */
    void addJob(JobConfigDO scheduleJob) throws SchedulerException, ParseException, ClassNotFoundException;

    /**
     * 3、暂停任务
     *
     * @param scheduleJob JOB基本信息参数
     * @throws org.quartz.SchedulerException 调度异常信息
     */
    void pauseJob(JobConfigDO scheduleJob) throws SchedulerException, ParseException, ClassNotFoundException;

    /**
     * 4、删除任务
     *
     * @param scheduleJob JOB基本信息参数
     * @throws org.quartz.SchedulerException 调度异常信息
     */
    boolean removeJob(JobConfigDO scheduleJob) throws SchedulerException, ParseException, ClassNotFoundException;

    /**
     * 5、立即运行任务
     *
     * @param scheduleJob JOB基本信息参数
     * @throws org.quartz.SchedulerException 调度异常信息
     */
    void triggerJob(JobConfigDO scheduleJob) throws SchedulerException, ParseException, ClassNotFoundException;

    /**
     * 6、根据任务名、组名查询任务状态
     *
     * @param scheduleJob JOB基本信息参数
     * @return 任务状态
     * @throws SchedulerException 运行中的任务列表
     */
    String scheduleJob(JobConfigDO scheduleJob) throws SchedulerException;

    /**
     * 监控内存数据
     *
     * @return JobConfigDO    JOB基本信息参数
     */
    List<JobConfigDO> jobManager() throws SchedulerException, ParseException, ClassNotFoundException;
}
