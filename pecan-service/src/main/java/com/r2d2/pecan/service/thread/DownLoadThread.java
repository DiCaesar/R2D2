package com.r2d2.pecan.service.thread;

import com.google.common.base.Splitter;
import com.r2d2.pecan.common.enums.StatusEnum;
import com.r2d2.pecan.dao.manager.CategoriesManager;
import com.r2d2.pecan.dao.model.StorageDetilDO;
import lombok.extern.slf4j.Slf4j;

import java.util.List;

/**
 * Created by Administrator on 2017/11/4.
 */
@Slf4j
public class DownLoadThread implements Runnable{

    private String filePath;

    private StorageDetilDO group;

    private CategoriesManager manager;

    public DownLoadThread(String filePath,StorageDetilDO group, CategoriesManager manager){
        this.filePath = filePath;
        this.group = group;
        this.manager = manager;
    }

    @Override
    public void run() {
        try{
            //status change
            statusChange(StatusEnum.Processing);
            List fileList = Splitter.on(",").splitToList(group.getPicList());

        }catch (Exception e){
            log.error("download faile group {}-{}",group.getGroupId(),group.getGroupName());
            statusChange(StatusEnum.Failure);
        }finally {
            statusChange(StatusEnum.Success);
        }

    }


    public  void statusChange(StatusEnum status){
       // manager.updateStatus(group.getGroupId(), status.getCode());
    }

}
