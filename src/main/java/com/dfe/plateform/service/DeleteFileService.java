package com.dfe.plateform.service;

import java.util.List;
import java.util.Set;

public interface DeleteFileService {

    /**
     * 获取需要删除的聊天文件的file_id集合
     * @return
     * @throws Exception
     */
    List<String> getFileIdOfDeleteFiles() throws Exception;

    /**
     * 获取file_extra表中需要删除的文件的file_uuid
     * @return
     * @throws Exception
     */
    List<String> getFileUuidOfFileExtra() throws Exception;

    /**
     * 获取file_paths表中需要删除的目录的path_id
     * @return
     * @throws Exception
     */
    List<String> getPathIdOfFIlePaths() throws Exception;

    /**
     * 根据路径获取file_extra表中需要删除的文件的file_uuid
     * @param set
     * @return
     * @throws Exception
     */
    List<String> getFileUuidByPath(Set<String> set) throws Exception;

    /**
     * 删除file_extra表中符合条件的文件信息
     * @return
     * @throws Exception
     */
    int deleteFileExtra() throws Exception;

    /**
     * 删除file_extra表中符合条件的文件信息
     * @return
     * @throws Exception
     */
    int deleteFileExtraByPath(Set<String> set) throws Exception;

    /**
     * 删除file_paths表中符合条件的目录信息
     * @param set
     * @return
     * @throws Exception
     */
    int deleteFilePaths(Set<String> set) throws Exception;

    /**
     * 更新files表中的is_deleted字段
     * @param idList
     * @return
     * @throws Exception
     */
    int updateFilesIsDeleted(List<String> idList) throws Exception;

    /**
     * 更新files表中的deleted_at字段
     * @param idList
     * @return
     * @throws Exception
     */
    int updateFilesDeletedAt(List<String> idList) throws Exception;

    /**
     * 获取需要删除的云盘文件的file_id集合
     * @return
     * @throws Exception
     */
    List<String> getFileIdOfDeleteFilesCloud() throws Exception;

    /**
     * 删除聊天文件或者云盘文件
     * @param idList
     * @return
     * @throws Exception
     */
    int deleteChatOrCloudFiles(List<String> idList) throws Exception;

}
