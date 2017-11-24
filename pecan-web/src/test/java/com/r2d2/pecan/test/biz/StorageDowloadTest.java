package com.r2d2.pecan.test.biz;

import com.r2d2.pecan.service.biz.StorageDownloadBiz;
import com.r2d2.pecan.test.BaseTest;
import org.junit.Test;
import org.springframework.beans.factory.annotation.Autowired;

import java.util.concurrent.TimeUnit;

/**
 * Created by DiCaesar on 2017/11/24
 */
public class StorageDowloadTest extends BaseTest{

    @Autowired
    private StorageDownloadBiz storageDownloadBiz;

    @Test
    public void downTest() throws InterruptedException {
        storageDownloadBiz.doBiz();

        Thread.sleep(TimeUnit.SECONDS.toMillis(1000));

    }
}
