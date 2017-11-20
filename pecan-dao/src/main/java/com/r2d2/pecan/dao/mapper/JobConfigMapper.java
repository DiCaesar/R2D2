package com.r2d2.pecan.dao.mapper;


import com.r2d2.pecan.dao.model.JobConfigDO;
import org.apache.ibatis.annotations.Param;

import java.util.List;

/**
 * Job配置数据接口映射器
 * <p>
 *  1、新增JOB配置信息
 *  2、根据条件查询并锁定JOB配置信息(采用NOWAIT方式)
 *  3、根据条件查询JOB配置信息
 *  4、更新JOB配置信息
 *  5、查询JOB配置信息
 *  6、查询JOB配置数据记录数
 *  7、查询JOB配置数据
 * </p>
 */
public interface JobConfigMapper {

    /**
     * 1、新增JOB配置信息
     *
     * @param JobConfigDO JOB数据模型
     * @return 新增结果
     */
    int insertJobConfig(JobConfigDO JobConfigDO);

    /**
     * 2、根据条件查询并锁定JOB配置信息(采用NOWAIT方式)
     *
     * @param JobConfigDO JOB数据模型
     * @return 锁定结果
     */
    List<JobConfigDO> selectForUpdateBySelective(JobConfigDO JobConfigDO);

    /**
     * 3、根据条件查询JOB配置信息
     *
     * @param JobConfigDO JOB数据模型
     * @return JOB数据模型集合
     */
    List<JobConfigDO> selectBySelective(JobConfigDO JobConfigDO);

    /**
     * 4、更新JOB配置信息
     *
     * @param JobConfigDO JOB数据模型
     * @return 更新结果
     */
    int updateBySelective(JobConfigDO JobConfigDO);

    /**
     * 5、查询JOB配置信息
     *
     * @param jobNo 配置编号
     * @return JOB配置信息
     */
    JobConfigDO selectByJobNo(String jobNo);

    /**
     * 6、查询数据记录数
     *
     * @param jobConfigDO 条件
     * @return 数据记录数
     */
    int selectJobConfigCount(JobConfigDO jobConfigDO);

    /**
     * 7、查询数据
     *
     * @param jobConfigDO 条件
     * @return 数据
     */
    List<JobConfigDO> selectJobConfigPage(@Param("bean") JobConfigDO jobConfigDO,
                                          @Param("startRow") Integer startRow,
                                          @Param("pageSize") Integer pageSize);

}
