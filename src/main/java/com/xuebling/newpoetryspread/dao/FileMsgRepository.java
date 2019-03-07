package com.xuebling.newpoetryspread.dao;

import com.xuebling.newpoetryspread.pojo.fileupload.FileMsg;
import org.springframework.data.mongodb.repository.MongoRepository;

import java.util.Optional;

//继承jpa-mongo的方法
public interface FileMsgRepository extends MongoRepository<FileMsg, String>,FileMsgCustomRepository {
    Optional<FileMsg> findBySourceFileMD5(String md5);
        void deleteById(String id);
}
