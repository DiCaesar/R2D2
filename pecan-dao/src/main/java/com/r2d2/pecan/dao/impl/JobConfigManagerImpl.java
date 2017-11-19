package com.r2d2.pecan.dao.impl;

import com.r2d2.pecan.dao.manager.JobConfigManager;
import com.r2d2.pecan.dao.model.JobConfigDO;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Component;

import java.util.List;

/**
 * Created by DiCaesar on 2017/11/14
 */
@Slf4j
@Component
public class JobConfigManagerImpl implements JobConfigManager {
    /**
     * 查询并锁定JOB配置信息
     *
     * @param jobConfigDO 任务配置对象
     * @return 任务配置对象
     */
    @Override
    public boolean queryLockJobConfig(JobConfigDO jobConfigDO) {
        return false;
    }

    /**
     * 查询JOB配置信息
     *
     * @param jobConfigDO 任务配置对象
     * @return 任务配置对象集合
     */
    @Override
    public List<JobConfigDO> queryJobConfig(JobConfigDO jobConfigDO) {
        return null;
    }

    /**
     * 更新任务配置信息
     *
     * @param jobConfigDO 任务配置对象
     */
    @Override
    public void modifyJobConfig(JobConfigDO jobConfigDO) {

    }

    /**
     * 新增任务配置信息
     *
     * @param jobConfigDO 任务配置对象
     */
    @Override
    public void addJobConfig(JobConfigDO jobConfigDO) {

    }

    /**
     * 删除任务
     *
     * @param jobNo 任务编号
     */
    @Override
    public JobConfigDO queryJobConfig(String jobNo) {
        return null;
    }

    /**
     * 查询数据记录数
     *
     * @param jobConfigDO 条件
     * @return 数据记录数
     */
    @Override
    public int queryJobConfigCount(JobConfigDO jobConfigDO) {
        return 0;
    }

    /**
     * 查询数据记录数（分页）
     *
     * @param jobConfigDO 条件
     * @param startRow
     * @param endRow      @return 数据记录数
     */
    @Override
    public List<JobConfigDO> queryJobConfigPage(JobConfigDO jobConfigDO, Integer startRow, Integer endRow) {
        return null;
    }
}
