package com.xuebling.newpoetryspread.dao;

import com.xuebling.newpoetryspread.pojo.user.Editor;

public interface UserCustomRepository {
    void updateById(String userId, Editor editor);
    void updateLockById(String userId, Integer lock);
    void updateLevelById(String userId, Integer level);
}
