package com.dfe.plateform.schedule;

import com.dfe.plateform.service.DeleteFileService;
import com.dfe.plateform.util.FileUtil;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.scheduling.annotation.Scheduled;
import org.springframework.stereotype.Component;

import java.util.ArrayList;
import java.util.List;
import java.util.Set;
import java.util.HashSet;

@Slf4j
@Component
public class Schedule {

    @Autowired
    DeleteFileService deleteFileService;

    @Value("${deletefiles.path}")
    private String deleteFilesPath;

    @Scheduled(cron = "${cron.deleteFiles}")
    public void deleteFiles() throws Exception{
        //删除聊天文件
        List<String> file_ids = deleteFileService.getFileIdOfDeleteFiles();
        if(file_ids != null && file_ids.size() > 0){
            for (String file_id:file_ids) {
                FileUtil.delFolder(deleteFilesPath+file_id);
            }
            //int chatFilesNumber = deleteFileService.deleteChatOrCloudFiles(file_ids);
            //if(chatFilesNumber == file_ids.size()){
                log.info("聊天文件删除成功! 共删除"+file_ids.size()+"条");
            //}
        } else {
            log.info("没有需要删除的聊天文件");
        }

        List<String> totalFileUuidList = new ArrayList<>();
        //删除网盘文件
        List<String> fileUuidList = deleteFileService.getFileUuidOfFileExtra();
        if(fileUuidList != null && fileUuidList.size() > 0){
            int deleteFileExtraNumber = deleteFileService.deleteFileExtra();
            if(deleteFileExtraNumber > 0){
                log.info("file_extra表中文件删除成功!");
                /*int updateFilesDeletedNumber  = deleteFileService.updateFilesDeleted(fileUuidList);
                if(updateFilesDeletedNumber > 0){
                    log.info("files表的deleted_at和is_deleted字段更新成功");
                }*/
            }
            totalFileUuidList.addAll(fileUuidList);
        } else {
            log.info("file_extra表中没有需要删除的文件!");
        }

        List<String> deleteFilePaths = deleteFileService.getPathIdOfFIlePaths();
        if(deleteFilePaths != null && deleteFilePaths.size() > 0){
            Set<String> pathSet = new HashSet<>();
            for(String str : deleteFilePaths) {
                if(str != null && str.length() > 0) {
                    String[] pathArray = str.split(",");
                    for(String temp : pathArray) {
                        if(temp != null && temp.length() > 0) {
                            pathSet.add(temp);
                        }
                    }
                }
            }
            if(pathSet.size() > 0) {
                fileUuidList = deleteFileService.getFileUuidByPath(pathSet);
                totalFileUuidList.addAll(fileUuidList);
                deleteFileService.deleteFileExtraByPath(pathSet);
                deleteFileService.deleteFilePaths(pathSet);
            }
            log.info("file_paths表中目录删除成功!");
        } else {
            log.info("file_paths表中没有需要删除的目录!");
        }

        if(totalFileUuidList != null && totalFileUuidList.size() > 0){
            int cloudFilesNumber = deleteFileService.updateFilesIsDeleted(totalFileUuidList);
            log.info("files表的is_deleted字段更新成功, 共更新" + cloudFilesNumber + "条数据");
            List<String> file_ids_Cloud = deleteFileService.getFileIdOfDeleteFilesCloud();
            if(file_ids_Cloud != null && file_ids_Cloud.size() > 0){
                for (String file_id:file_ids_Cloud) {
                    FileUtil.delFolder(deleteFilesPath+file_id);
                }
                log.info("网盘文件删除成功! 共删除"+file_ids_Cloud.size()+"个文件");
            } else {
                log.info("没有需要删除的网盘文件");
            }
            cloudFilesNumber = deleteFileService.updateFilesDeletedAt(totalFileUuidList);
            log.info("files表的deletedAt字段更新成功, 共更新" + cloudFilesNumber + "条数据");
        } else {
            log.info("没有需要删除的网盘文件");
        }
       /* List<String> file_ids_Cloud = deleteFileService.getFileIdOfDeleteFilesCloud();
        file_ids_Cloud.addAll(fileUuidList);
        if(file_ids_Cloud != null && file_ids_Cloud.size() > 0){
            for (String file_id:file_ids_Cloud) {
                FileUtil.delFolder(deleteFilesPath+file_id);
            }
            int cloudFilesNumber = deleteFileService.updateFilesDeleted(file_ids_Cloud);
            log.info("网盘文件删除成功! 共删除"+file_ids_Cloud.size()+"个文件, " + cloudFilesNumber + "条数据");
        } else {
            log.info("没有需要删除的网盘文件");
        }*/
    }

}
