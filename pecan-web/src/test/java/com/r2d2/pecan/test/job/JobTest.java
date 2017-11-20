package com.r2d2.pecan.test.job;

import com.google.common.collect.Lists;
import com.r2d2.pecan.common.enums.DeleteFlagEnum;
import com.r2d2.pecan.common.enums.JobConfigStatusEnum;
import com.r2d2.pecan.dao.model.JobConfigDO;
import com.r2d2.pecan.dao.model.JobLogDO;
import com.r2d2.pecan.service.quartz.JobInit;
import com.r2d2.pecan.test.BaseTest;
import lombok.extern.slf4j.Slf4j;
import org.junit.Test;
import org.springframework.beans.factory.annotation.Autowired;

import java.util.List;
import java.util.concurrent.TimeUnit;

/**
 * Created by Administrator on 2017/11/18.
 */
@Slf4j
public class JobTest extends BaseTest{

    @Autowired
    JobInit jobInit;

    @Test
    public void trigeTest() throws Exception{
        JobConfigDO jobConfigDO = new JobConfigDO();
        JobLogDO jobLogDO = new JobLogDO();
        jobConfigDO.setJobClass("quartzDemoJob");
        jobConfigDO.setJobCronExpress("*/5 * * * * ?"); //5s
        jobConfigDO.setStatus(JobConfigStatusEnum.NORMAL.getCode());
        jobConfigDO.setJobGroup("group1");
        jobConfigDO.setJobName("test1");
        jobConfigDO.setJobDesc("test1_desc");
        jobConfigDO.setJobNo("1");
        jobConfigDO.setDeleteFlag(DeleteFlagEnum.USABLE.getCode());

        jobLogDO.setJobId("1");
        jobLogDO.setJobName("test1");

        jobConfigDO.setJobLogDO(jobLogDO);


        List<JobConfigDO> jobList = Lists.newArrayList(jobConfigDO);
        jobInit.run(jobList);

        Thread.sleep(TimeUnit.SECONDS.toMillis(100000));
    }

}
