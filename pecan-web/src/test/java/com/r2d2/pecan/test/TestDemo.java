package com.r2d2.pecan.test;

import lombok.extern.slf4j.Slf4j;
import org.apache.commons.lang3.StringUtils;
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

    public static void main(String[] args){
        String str = StringUtils.substringAfterLast("adads/ada/dasd/123.sd","/");
        System.out.println(str);
    }
}
