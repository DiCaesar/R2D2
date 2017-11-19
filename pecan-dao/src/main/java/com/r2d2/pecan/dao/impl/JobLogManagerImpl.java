package com.r2d2.pecan.dao.impl;

import com.r2d2.pecan.dao.manager.JobLogManager;
import com.r2d2.pecan.dao.model.JobLogDO;
import org.springframework.stereotype.Repository;

/**
 * Created by Administrator on 2017/11/18.
 */
@Repository
public class JobLogManagerImpl implements JobLogManager {
    @Override
    public JobLogDO addJobLog(JobLogDO jobLogDO) {
        return null;
    }

    @Override
    public int countByExecDate(JobLogDO jobLogDO) {
        return 0;
    }

    @Override
    public int modifyJobLog(JobLogDO jobLogDO) {
        return 0;
    }
}
