package com.r2d2.pecan.service.job;

import com.r2d2.pecan.dao.model.JobConfigDO;

/**
 * 任务父类
 *
 * <p>
 * 1、工作准备逻辑
 * 2、具体的工作执行逻辑
 * 3、工作收尾逻辑
 * </p>
 */
public interface Task {

    /**
     * 1、工作准备逻辑
     *
     * @param jobConfigDO 任务上下文对象
     * @throws Exception 服务异常
     */
    void beforeWorker(JobConfigDO jobConfigDO) throws Exception;

    /**
     * 2、具体的工作执行逻辑
     *
     * @param jobConfigDO 任务上下文对象
     * @throws Exception 服务异常
     */
    void doWorker(JobConfigDO jobConfigDO) throws Exception;

    /**
     * 3、工作收尾逻辑
     *
     * @param jobConfigDO 任务上下文对象
     * @throws Exception 服务异常
     */
    void afterWorker(JobConfigDO jobConfigDO) throws Exception;
}
