package com.r2d2.pecan.dao.model;

import lombok.Getter;
import lombok.Setter;
import lombok.ToString;

import java.io.Serializable;
import java.util.Date;

/**
 * Created by DiCaesar on 2017/11/14
 */
@Getter
@Setter
@ToString(callSuper = true)
public class JobLogDO extends BaseDO {

    /**
     * Job任务的Id
     */
    private String jobId;

    /**
     * 任务名称
     */
    private String jobName;

    /**
     * 任务组
     */
    private String jobGroup;

    /**
     * 机器IP地址
     */
    private String machineIp;

    /**
     * 执行日期
     */
    private Date execDate;

    /**
     * 任务执行状态
     */
    private String status;

    /**
     * 任务异常描述
     */
    private String errorMsg;

    /**
     * 任务开始执行时间
     */
    private Date startTime;

    /**
     * 任务执行完毕时间
     */
    private Date updatedTime;

    /**
     * 任务执行次数
     */
    private int execTimes;

}
