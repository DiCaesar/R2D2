package com.r2d2.pecan.test;

import lombok.extern.slf4j.Slf4j;
import org.junit.Test;

import java.util.concurrent.TimeUnit;

/**
 * Created by DiCaesar on 2017/11/14
 */
@Slf4j
public class TestDemo extends BaseTest {

    @Test
    public void test() throws Exception{
        log.info("start...");
        Thread.sleep(TimeUnit.SECONDS.toMillis(10000));
    }
}
