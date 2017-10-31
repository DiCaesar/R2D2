package com.r2d2.repository.test.utils;

import com.r2d2.repository.common.utils.RedisManager;
import com.r2d2.repository.test.BaseTest;
import lombok.extern.slf4j.Slf4j;
import org.junit.Test;
import org.springframework.beans.factory.annotation.Autowired;

import java.util.concurrent.TimeUnit;

/**
 * Created by DiCaesar on 2017/10/31
 */
@Slf4j
public class RedisTest  extends BaseTest {

    @Autowired
    RedisManager redisManager;

    @Test
    public void queryTest(){
        String key = "key1";
        String value = redisManager.queryObjectByKey(key);
        log.info("val====={}",value);
    }

    @Test
    public void insertTest(){
        String key = "1223";
        assert redisManager.insertObject("AAAAAA",key, TimeUnit.DAYS.toMillis(1));
    }
}
