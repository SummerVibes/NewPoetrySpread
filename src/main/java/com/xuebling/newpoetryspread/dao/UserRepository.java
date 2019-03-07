package com.xuebling.newpoetryspread.dao;

import com.xuebling.newpoetryspread.pojo.user.Editor;
import org.springframework.data.mongodb.repository.MongoRepository;

public interface UserRepository extends MongoRepository<Editor, String>, UserCustomRepository{
//    Editor findByAccount(String account);
}
