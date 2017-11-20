package com.r2d2.pecan.dao.model;


import lombok.Getter;
import lombok.Setter;
import lombok.ToString;

import java.util.Date;

/**
 * JOB配置信息
 *
 * */
@Getter
@Setter
@ToString(callSuper = true)
public class JobConfigDO extends BaseDO {

    /**
     * JOB编号
     */
    private String jobNo;

    /**
     * 任务名称
     */
    private String jobName;

    /**
     * 任务分组
     */
    private String jobGroup;

    /**
     * 任务状态 0禁用 1启用 2删除
     */
    private String status;

    /**
     * 任务运行时间表达式
     */
    private String jobCronExpress;

    /**
     * 任务运行时间表达式描述
     */
    private String jobCronExpressDesc;

    /**
     * 类名或者bean名
     */
    private String jobClass;

    /**
     * 交易日
     */
    private Date tradeDate;

    /**
     * Job执行次数
     */
    private Long jobExecCount;

    /**
     * 允许尝试次数
     */
    private Long retryTimes;

    /**
     * 上次运行时间
     */
    private Date lastExecTime;

    /**
     * 下次执行时间
     */
    private Date nextExecTime;

    /**
     * 耗时
     */
    private Long jobUsedTime;

    /**
     * 任务描述
     */
    private String jobDesc;

    /**
     * JOB执行日志
     */
    private JobLogDO jobLogDO;
}
