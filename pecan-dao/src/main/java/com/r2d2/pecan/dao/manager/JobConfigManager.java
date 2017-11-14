package com.r2d2.pecan.dao.manager;

import com.r2d2.pecan.dao.model.JobConfigDO;

import java.util.List;

/**
 * JOB任务配置
 * <p>
 * 1、查询并锁定JOB配置信息
 * 2、查询JOB配置信息
 * 3、更新任务配置信息
 * 4、新增任务配置信息
 * 5、删除任务
 * 6、查询数据记录数
 * 7、查询数据记录数（分页）
 * </p>
 */
public interface JobConfigManager {

    /**
     * 查询并锁定JOB配置信息
     *
     * @param jobConfigDO 任务配置对象
     * @return 任务配置对象
     */
    boolean queryLockJobConfig(JobConfigDO jobConfigDO);

    /**
     * 查询JOB配置信息
     *
     * @param jobConfigDO 任务配置对象
     * @return 任务配置对象集合
     */
    List<JobConfigDO> queryJobConfig(JobConfigDO jobConfigDO);

    /**
     * 更新任务配置信息
     *
     * @param jobConfigDO 任务配置对象
     */
    void modifyJobConfig(JobConfigDO jobConfigDO);

    /**
     * 新增任务配置信息
     *
     * @param jobConfigDO 任务配置对象
     */
    void addJobConfig(JobConfigDO jobConfigDO);

    /**
     * 删除任务
     *
     * @param jobNo 任务编号
     */
    JobConfigDO queryJobConfig(String jobNo);

    /**
     * 查询数据记录数
     *
     * @param jobConfigDO 条件
     * @return 数据记录数
     */
    int queryJobConfigCount(JobConfigDO jobConfigDO);

    /**
     * 查询数据记录数（分页）
     *
     * @param jobConfigDO 条件
     * @return 数据记录数
     */
    List<JobConfigDO> queryJobConfigPage(JobConfigDO jobConfigDO, Integer startRow, Integer endRow);
}
