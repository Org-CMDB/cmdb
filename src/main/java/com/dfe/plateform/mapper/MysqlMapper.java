package com.dfe.plateform.mapper;

import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Set;

@Repository
public interface MysqlMapper {

    List<String> getFileUuidOfFileExtra() throws Exception;

    List<String> getPathIdOfFIlePaths() throws Exception;

    List<String> getFileUuidByPath(Set<String> set) throws Exception;

    int deleteFileExtra() throws Exception;

    int deleteFileExtraByPath(Set<String> set) throws Exception;

    int deleteFilePaths(Set<String> set) throws Exception;
}
