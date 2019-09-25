package com.dfe.plateform.service;

import com.dfe.plateform.datasource.TargetDataSource;
import com.dfe.plateform.mapper.MysqlMapper;
import com.dfe.plateform.mapper.PgSqlMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;
import java.util.Set;

@Service
public class DeleteFileServiceImpl implements DeleteFileService {

    @Autowired
    PgSqlMapper pgSqlMapper;

    @Autowired
    MysqlMapper mysqlMapper;

    @Override
    @TargetDataSource(value = "slave")
    public List<String> getFileUuidOfFileExtra() throws Exception {
        return mysqlMapper.getFileUuidOfFileExtra();
    }

    @Override
    @TargetDataSource(value = "slave")
    public List<String> getPathIdOfFIlePaths() throws Exception {
        return mysqlMapper.getPathIdOfFIlePaths();
    }

    @Override
    @TargetDataSource(value = "slave")
    public List<String> getFileUuidByPath(Set<String> set) throws Exception {
        return mysqlMapper.getFileUuidByPath(set);
    }

    @Override
    @TargetDataSource(value = "slave")
    @Transactional(rollbackFor = Exception.class)
    public int deleteFileExtra() throws Exception{
        return mysqlMapper.deleteFileExtra();
    }

    @Override
    @TargetDataSource(value = "slave")
    @Transactional(rollbackFor = Exception.class)
    public int deleteFileExtraByPath(Set<String> set) throws Exception{
        return mysqlMapper.deleteFileExtraByPath(set);
    }

    @Override
    @TargetDataSource(value = "slave")
    @Transactional(rollbackFor = Exception.class)
    public int deleteFilePaths(Set<String> set) throws Exception{
        return mysqlMapper.deleteFilePaths(set);
    }

    @Override
    @TargetDataSource(value = "master")
    public List<String> getFileIdOfDeleteFiles() throws Exception {
        return pgSqlMapper.getFileIdOfDeleteFiles();
    }

    @Override
    @TargetDataSource(value = "master")
    @Transactional(rollbackFor = Exception.class)
    public int updateFilesIsDeleted(List<String> idList) throws Exception {
        return pgSqlMapper.updateFilesIsDeleted(idList);
    }

    @Override
    @TargetDataSource(value = "master")
    @Transactional(rollbackFor = Exception.class)
    public int updateFilesDeletedAt(List<String> idList) throws Exception {
        return pgSqlMapper.updateFilesDeletedAt(idList);
    }

    @Override
    @TargetDataSource(value = "master")
    @Transactional(rollbackFor = Exception.class)
    public List<String> getFileIdOfDeleteFilesCloud() throws Exception {
        return pgSqlMapper.getFileIdOfDeleteFilesCloud();
    }

    @Override
    @TargetDataSource(value = "master")
    @Transactional(rollbackFor = Exception.class)
    public int deleteChatOrCloudFiles(List<String> idList) throws Exception {
        return pgSqlMapper.deleteChatOrCloudFiles(idList);
    }
}
