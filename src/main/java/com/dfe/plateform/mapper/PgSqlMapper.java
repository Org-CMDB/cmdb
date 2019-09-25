package com.dfe.plateform.mapper;

import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface PgSqlMapper {

    List<String> getFileIdOfDeleteFiles() throws Exception;

    List<String> getFileIdOfDeleteFilesCloud() throws Exception;

    int deleteChatOrCloudFiles(List<String> idList) throws Exception;

    int updateFilesIsDeleted(List<String> idList) throws Exception;

    int updateFilesDeletedAt(List<String> idList) throws Exception;
}
