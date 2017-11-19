package com.r2d2.pecan.dao.manager;

import com.r2d2.pecan.dao.model.JobLogDO;

/**
 * Created by Administrator on 2017/11/18.
 */
public interface JobLogManager {

    /**
     * 新增定时任务执行日志
     *
     * @param jobLogDO 任务日志对象
     * @return 受影响行数
     */
    JobLogDO addJobLog(JobLogDO jobLogDO);

    /**
     * 统计定时任务执行次数（日期为数据库时间）
     *
     * @param jobLogDO 任务日志对象
     * @return 统计值
     */
    int countByExecDate(JobLogDO jobLogDO);

    /**
     * 更新任务执行日志
     *
     * @param jobLogDO 任务日志对象
     * @return 受影响行数
     */
    int modifyJobLog(JobLogDO jobLogDO);
}
