package com.r2d2.pecan.service.job;

import com.r2d2.pecan.dao.model.JobConfigDO;
import lombok.extern.slf4j.Slf4j;
import org.quartz.SchedulerException;
import org.springframework.stereotype.Component;

import java.text.ParseException;
import java.util.List;

/**
 * Created by DiCaesar on 2017/11/14
 */
@Slf4j
@Component
public class QuartzJobBizImp implements QuartzJobBiz {
    /**
     * 1、修改/重置计划任务
     *
     * @param scheduleJob JOB基本信息参数
     * @throws SchedulerException 调度异常信息
     */
    @Override
    public void modifyJob(JobConfigDO scheduleJob) throws SchedulerException, ParseException, ClassNotFoundException {

    }

    /**
     * 2、加入计划任务
     *
     * @param scheduleJob JOB基本信息参数
     * @throws SchedulerException 调度异常信息
     */
    @Override
    public void addJob(JobConfigDO scheduleJob) throws SchedulerException, ParseException, ClassNotFoundException {

    }

    /**
     * 3、暂停任务
     *
     * @param scheduleJob JOB基本信息参数
     * @throws SchedulerException 调度异常信息
     */
    @Override
    public void pauseJob(JobConfigDO scheduleJob) throws SchedulerException, ParseException, ClassNotFoundException {

    }

    /**
     * 4、删除任务
     *
     * @param scheduleJob JOB基本信息参数
     * @throws SchedulerException 调度异常信息
     */
    @Override
    public boolean removeJob(JobConfigDO scheduleJob) throws SchedulerException, ParseException, ClassNotFoundException {
        return false;
    }

    /**
     * 5、立即运行任务
     *
     * @param scheduleJob JOB基本信息参数
     * @throws SchedulerException 调度异常信息
     */
    @Override
    public void triggerJob(JobConfigDO scheduleJob) throws SchedulerException, ParseException, ClassNotFoundException {

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
        return null;
    }

    /**
     * 监控内存数据
     *
     * @return JobConfigDO    JOB基本信息参数
     */
    @Override
    public List<JobConfigDO> jobManager() throws SchedulerException, ParseException, ClassNotFoundException {
        return null;
    }
}
