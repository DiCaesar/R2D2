package com.r2d2.pecan.service.thread;

import com.google.common.base.Splitter;
import com.r2d2.pecan.common.enums.StatusEnum;
import com.r2d2.pecan.common.utils.FileUtil;
import com.r2d2.pecan.dao.manager.StorageDetailManager;
import com.r2d2.pecan.dao.model.StorageDetailDO;
import lombok.extern.slf4j.Slf4j;
import org.apache.commons.lang3.StringUtils;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 * 下载线程
 *
 * Created by Administrator on 2017/11/4.
 */
@Slf4j
public class CategoriesDownLoadThread implements Runnable{

    private String filePath;

    private StorageDetailDO storageDetailDO;

    private StorageDetailManager manager;

    public CategoriesDownLoadThread(String filePath, StorageDetailDO storageDetailDO, StorageDetailManager manager){
        this.filePath = filePath;
        this.storageDetailDO = storageDetailDO;
        this.manager = manager;
    }

    @Override
    public void run() {
        StatusEnum statusEnum = StatusEnum.Processing;
        try{
            //status change
            statusChange(statusEnum);
            List<String> urlList = Splitter.on(",").splitToList(storageDetailDO.getUrlStr());
            Map<String,String> fileInfo = createFileInfo(urlList);
            for (Map.Entry<String,String>  entry : fileInfo.entrySet()){
                String fileName = entry.getKey();
                String url = entry.getValue();
                FileUtil.urlFileDownload(fileName,filePath,url);
            }
            statusEnum = StatusEnum.Success;
        }catch (Exception e){
            log.error("download faile group {}-{}",storageDetailDO.getId(),storageDetailDO.getGroupName());
            statusEnum = StatusEnum.Failure;

        }finally {
            statusChange(statusEnum);
        }

    }

    /**
     * 文件信息
     *
     * @param urlList
     * @return
     */
    private Map<String,String> createFileInfo(List<String> urlList){
        Map<String,String> fileInfo = new HashMap<String, String>();
        for(String url : urlList){
            String fileName = StringUtils.substringAfterLast(url,"/");
            fileInfo.put(fileName,url);
        }
        return fileInfo;
    }

    /**
     * 更改状态
     *
     * @param status
     */
    public  void statusChange(StatusEnum status){
        storageDetailDO.setStatus(status.getCode());
        if (StatusEnum.Success.getCode().equals(status.getCode())){
            storageDetailDO.setFilePath(filePath);
        }
        manager.updateStorage(storageDetailDO);
    }

}
