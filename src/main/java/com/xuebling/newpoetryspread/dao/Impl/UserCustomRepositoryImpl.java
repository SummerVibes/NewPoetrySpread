package com.xuebling.newpoetryspread.dao.Impl;

import com.xuebling.newpoetryspread.dao.UserCustomRepository;
import com.xuebling.newpoetryspread.pojo.user.Editor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.mongodb.core.FindAndModifyOptions;
import org.springframework.data.mongodb.core.MongoTemplate;
import org.springframework.data.mongodb.core.query.Criteria;
import org.springframework.data.mongodb.core.query.Query;
import org.springframework.data.mongodb.core.query.Update;

public class UserCustomRepositoryImpl implements UserCustomRepository {
    @Autowired
    protected MongoTemplate mongoTemplate;
    public void updateById(String id, Editor editor) {
        Query query = new Query(Criteria.where("_id").is(id));
        Update update = new Update();
        update.set("nickname", editor.getNickname());
        update.set("avatar", editor.getAvatar());
        update.set("level", editor.getLevel());
        update.set("lock", editor.getLock());
        mongoTemplate.updateFirst(query, update, Editor.class, "User");
    }
    public void updateLockById(String id, Integer lock) {
        Query query = new Query(Criteria.where("_id").is(id));
        Update update = new Update().set("lock", lock);
        FindAndModifyOptions options = new FindAndModifyOptions();
        options.returnNew(true);
        mongoTemplate.findAndModify(query, update, options, boolean.class);
    }
    public void updateLevelById(String id, Integer level) {
        Query query = new Query(Criteria.where("_id").is(id));
        Update update = new Update().set("level", level);
        FindAndModifyOptions options = new FindAndModifyOptions();
        options.returnNew(true);
        mongoTemplate.findAndModify(query, update, options, Integer.class);
    }
}
