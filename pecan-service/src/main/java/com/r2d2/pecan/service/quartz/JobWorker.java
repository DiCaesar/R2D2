package com.r2d2.pecan.service.quartz;

import com.r2d2.pecan.dao.model.JobConfigDO;
import lombok.extern.slf4j.Slf4j;
import org.quartz.DisallowConcurrentExecution;
import org.quartz.Job;
import org.quartz.JobExecutionContext;
import org.quartz.JobExecutionException;

/**
 * JOB任务执行有状态处理基类
 * <p>
 * 1.工作准备逻辑
 * 2.Job任务执行方法
 * 3.工作收尾逻辑
 * </p>
 */
@Slf4j
@DisallowConcurrentExecution
public abstract class JobWorker extends WorkFactory implements Job, Task {

    /**
     * 1、工作准备逻辑
     *
     * @param jobConfigDO 任务上下文对象
     * @throws Exception 服务异常
     */
    @Override
    public void beforeWorker(JobConfigDO jobConfigDO) throws Exception {

    }

    /**
     * 2、Job任务执行方法
     *
     * @param context 任务上下文对象
     * @throws JobExecutionException 服务异常
     */
    @Override
    public void execute(JobExecutionContext context) throws JobExecutionException {

     //   MDC.put(BestpayMarker.TRACE_LOG_ID, TraceLogIdUtil.createTraceLogId());
        log.info("===job {} ====", this.getClass().getSimpleName());
        process(context, this);
      //  MDC.clear();
    }

    /**
     * 3、工作收尾逻辑
     *
     * @param jobConfigDO 任务上下文对象
     * @throws Exception 服务异常
     */
    @Override
    public void afterWorker(JobConfigDO jobConfigDO) throws Exception {

    }
}
