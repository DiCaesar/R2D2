package com.r2d2.pecan.service.quartz;

import org.quartz.spi.TriggerFiredBundle;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.config.AutowireCapableBeanFactory;
import org.springframework.scheduling.quartz.AdaptableJobFactory;
import org.springframework.stereotype.Component;

/**
 * Created by DiCaesar on 2017/11/14
 */
@Component
public class JobFactory extends AdaptableJobFactory {

    /**
     * The one of Spring BeanFactory
     */
    @Autowired
    private AutowireCapableBeanFactory capableBeanFactory;

    /**
     * 1、JOB 注入
     *
     * @param bundle TriggerFiredBundle
     * @return Object        Autowire Bean
     * @throws Exception 异常
     */
    protected Object createJobInstance(TriggerFiredBundle bundle) throws Exception {

        Object jobInstance = super.createJobInstance(bundle);
        capableBeanFactory.autowireBean(jobInstance);
        return jobInstance;
    }

}
