package com.r2d2.pecan.dao.impl;

import com.r2d2.pecan.common.constant.PecanConstant;
import com.r2d2.pecan.common.enums.JobLogStatusEnum;
import com.r2d2.pecan.common.exception.CommonErrorCode;
import com.r2d2.pecan.common.exception.ServiceException;
import com.r2d2.pecan.common.utils.DateUtil;
import com.r2d2.pecan.common.utils.IPUtil;
import com.r2d2.pecan.dao.manager.JobConfigManager;
import com.r2d2.pecan.dao.manager.JobLogManager;
import com.r2d2.pecan.dao.mapper.JobConfigMapper;
import com.r2d2.pecan.dao.model.JobConfigDO;
import com.r2d2.pecan.dao.model.JobLogDO;
import lombok.extern.slf4j.Slf4j;
import org.apache.commons.lang3.StringUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.util.CollectionUtils;

import java.util.Date;
import java.util.List;

/**
 * Created by DiCaesar on 2017/11/14
 */
@Slf4j
@Component
public class JobConfigManagerImpl implements JobConfigManager {

    /**
     * Job配置数据接口映射器
     */
    @Autowired(required = false)
    private JobConfigMapper jobConfigMapper;

    /**
     * 任务日志数据库操作接口
     */
    @Autowired
    private JobLogManager jobLogManager;


    /**
     * 查询并锁定JOB配置信息
     *
     * @param jobConfigDO 任务配置对象
     * @return 任务配置对象
     */
    @Override
    @Transactional(readOnly = false, rollbackFor = Exception.class)
    public boolean queryLockJobConfig(JobConfigDO jobConfigDO) {

        List<JobConfigDO> JobConfigDOList = jobConfigMapper.selectForUpdateBySelective(jobConfigDO);
        if (CollectionUtils.isEmpty(JobConfigDOList)) {
            log.error("查询不到定时任务配置：{}", jobConfigDO);
            return true;
        }

        //修改job状态
        JobConfigDO jobConfig = new JobConfigDO();
        jobConfig.setJobNo(jobConfigDO.getJobNo());
        jobConfig.setUpdatedBy(jobConfigDO.getUpdatedBy());
        jobConfigMapper.updateBySelective(jobConfig);

        //新增job运行日志
        JobLogDO jobLogDO = buildJobLog(jobConfigDO);
        jobLogManager.addJobLog(jobLogDO);

        jobConfigDO.setJobLogDO(jobLogDO);
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

        return jobConfigMapper.selectBySelective(jobConfigDO);
    }

    /**
     * 更新任务配置信息
     *
     * @param jobConfigDO 任务配置对象
     */
    @Override
    @Transactional(readOnly = false, rollbackFor = Exception.class)
    public void modifyJobConfig(JobConfigDO jobConfigDO) {

        //修改任务配置
        int result = jobConfigMapper.updateBySelective(jobConfigDO);
      //  ParamValidate.validate(result);

        //修改任务日志
        JobLogDO jobLogDO = jobConfigDO.getJobLogDO();
        if (null == jobLogDO || StringUtils.isEmpty(jobLogDO.getId())) {
            log.error("查询不到任务日志：{}", jobLogDO);
            return;
        }
        jobLogManager.modifyJobLog(jobLogDO);
    }

    /**
     * 新增任务配置信息
     *
     * @param jobConfigDO 任务配置对象
     */
    @Override
    public void addJobConfig(JobConfigDO jobConfigDO) {

     //   LogicIdVO logicId = idManager.getLogicId(SeqRuleEnum.SEQ_JOB_CONFIG_NO);

        jobConfigDO.setId("12");
        jobConfigDO.setJobNo("232");

        int result = jobConfigMapper.insertJobConfig(jobConfigDO);
      //  ParamValidate.validate(result);
    }

    /**
     * 删除任务
     *
     * @param jobNo 任务编号
     */
    @Override
    public JobConfigDO queryJobConfig(String jobNo) {

        JobConfigDO jobConfigDO = jobConfigMapper.selectByJobNo(jobNo);
        if (jobConfigDO == null) {
            log.error("删除任务异常；【参数】 任务编号：{}", jobNo);
            throw new ServiceException(CommonErrorCode.JOB_CONFIG_NOT_EXISTS);
        }
        return jobConfigDO;
    }

    /**
     * 查询数据记录数
     *
     * @param jobConfigDO 条件
     * @return 数据记录数
     */
    @Override
    public int queryJobConfigCount(JobConfigDO jobConfigDO) {

        int row = jobConfigMapper.selectJobConfigCount(jobConfigDO);
        if (row < 1) {
            log.error("查询Job任务配置记录数异常；【参数】 任务配置查询对象：{}", jobConfigDO);
            throw new ServiceException(CommonErrorCode.JOB_CONFIG_NOT_EXISTS);
        }
        return row;
    }

    /**
     * 查询数据记录数(分页)
     *
     * @param jobConfigDO 条件
     * @param startRow    开始行
     * @param endRow      结束行
     * @return 数据记录数
     */
    @Override
    public List<JobConfigDO> queryJobConfigPage(JobConfigDO jobConfigDO, Integer startRow, Integer endRow) {

        List<JobConfigDO> jobConfigDOs = jobConfigMapper.selectJobConfigPage(jobConfigDO, startRow, endRow);
        if (CollectionUtils.isEmpty(jobConfigDOs)) {
            log.error("分页查询Job任务配置异常；【参数】 任务配置查询对象：{}", jobConfigDOs);
            throw new ServiceException(CommonErrorCode.JOB_CONFIG_NOT_EXISTS);
        }
        return jobConfigDOs;
    }

    /**
     * 新增任务执行日志
     */
    private JobLogDO buildJobLog(JobConfigDO jobConfigDO) {

  //      LogicIdVO logicId = idManager.getLogicId(SeqRuleEnum.SEQ_JOB_LOG_NO);

        JobLogDO jobLogDO = new JobLogDO();
        jobLogDO.setId("111");  //TODO ID
        jobLogDO.setJobId(jobConfigDO.getJobNo());
        jobLogDO.setExecDate(DateUtil.formatToDate(new Date(), DateUtil.datePattern));
        jobLogDO.setExecTimes(1);
        jobLogDO.setJobName(jobConfigDO.getJobName());
        jobLogDO.setJobGroup(jobConfigDO.getJobGroup());
        jobLogDO.setStatus(JobLogStatusEnum.RUNNING.getCode());
        jobLogDO.setMachineIp(IPUtil.getLocalIP());
        jobLogDO.setCreatedBy(PecanConstant.USER_SYSTEM);
        jobLogDO.setUpdatedBy(PecanConstant.USER_SYSTEM);
        jobLogDO.setCreatedAt(DateUtil.getCurrentDate(DateUtil.fullPattern));
        log.info("新增任务执行日志，日志id：{}", jobLogDO.getId());
        return jobLogDO;
    }
}
