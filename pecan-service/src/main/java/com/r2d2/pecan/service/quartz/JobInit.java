package com.r2d2.pecan.service.quartz;

import com.r2d2.pecan.common.enums.DeleteFlagEnum;
import com.r2d2.pecan.common.enums.JobConfigStatusEnum;
import com.r2d2.pecan.dao.manager.JobConfigManager;
import com.r2d2.pecan.dao.model.JobConfigDO;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.util.CollectionUtils;
import java.util.List;

/**
 * Created by DiCaesar on 2017/11/14
 */
@Slf4j
@Service
public class JobInit {

    /**
     * 任务配置信息管理接口
     */
    @Autowired
    private JobConfigManager jobConfigManager;

    /**
     * 任务调度
     */
    @Autowired
    private QuartzJobBiz quartzManager;


    /**
     * 1、初始化定时任务状态
     */
    public void init() {

        log.info("系统启动初始化定时任务!");
        try {
            List<JobConfigDO> jobConfigBOList = jobConfigManager.queryJobConfig(new JobConfigDO());
            if (CollectionUtils.isEmpty(jobConfigBOList)) {
                log.warn("没有可用的任务");
                return;
            }
            run(jobConfigBOList);
        } catch (Exception e) {
            log.error("定时任务加载异常：{}", e);
        }
    }

    /**
     * 2、将需调度的任务加入Quertz配置中心
     */
    public void run(List<JobConfigDO> jobConfigDOList) {

        log.info("任务调度加载未处理的任务信息-----开始-----");
        try {
            log.debug("本次任务调度处理查询出未处理的任务信息数为：{}", jobConfigDOList.size());
            for (JobConfigDO jobConfigDO : jobConfigDOList) {
                log.info("定时任务：{} 触发时间：{} 状态：{}", jobConfigDO.getJobDesc(), jobConfigDO.getJobCronExpress(),
                        jobConfigDO.getDeleteFlag());

                if (!DeleteFlagEnum.USABLE.getCode().equals(jobConfigDO.getDeleteFlag())) {
                    quartzManager.removeJob(jobConfigDO);
                    log.warn("定时任务移除成功：{}", jobConfigDO.getJobDesc());
                    continue;
                }

                //将任务按DB指定状态加载
                schedulerJob(jobConfigDO);
            }
            log.info("任务调度加载未处理的任务信息-----结束-----");
        } catch (Exception e) {
            log.error("将需调度的任务加入Quertz配置中心异常：{}", e);
        }
    }

    /**
     * 3、将任务按DB指定状态加载
     *
     * @param jobConfigDO 任务配置对象
     */
    private void schedulerJob(JobConfigDO jobConfigDO) {
        JobConfigStatusEnum jobConfigStatusEnum = JobConfigStatusEnum.getEnumsByCode(jobConfigDO.getStatus());
        if (null == jobConfigStatusEnum) {
            log.error("定时任务状态有误");
            return;
        }
        log.info("job : {},status:{}", jobConfigDO.getJobDesc(), jobConfigStatusEnum);
        try {
            switch (jobConfigStatusEnum) {
                case NORMAL:    // 加载定时任务
                    quartzManager.modifyJob(jobConfigDO);
                    break;
                case PAUSED:    // 暂停任务
                    quartzManager.pauseJob(jobConfigDO);
                    break;
                case NONE:     // 删除任务
                    if (!quartzManager.removeJob(jobConfigDO)) {
                        throw new RuntimeException("定时任务删除失败！");
                    }
                    break;
                default:
                    break;
            }
        } catch (Exception e) {
            log.error("加载定时任务{}，异常信息为：{}", jobConfigDO, e);
        }
    }
}
