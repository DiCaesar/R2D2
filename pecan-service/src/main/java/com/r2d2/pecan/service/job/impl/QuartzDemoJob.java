package com.r2d2.pecan.service.job.impl;

import com.r2d2.pecan.dao.model.JobConfigDO;
import com.r2d2.pecan.service.job.JobWorker;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;

import java.util.concurrent.TimeUnit;

/**
 * Created by Administrator on 2017/11/18.
 */
@Slf4j
@Service
public class QuartzDemoJob extends JobWorker {

    @Override
    public void doWorker(JobConfigDO jobConfigDO) throws Exception {
        log.info("job开始===========");
        Thread.sleep(TimeUnit.SECONDS.toMillis(2));
    }

    @Override
    public void afterWorker(JobConfigDO jobConfigDO) throws Exception {
        log.info("job完成========================");
    }
}
