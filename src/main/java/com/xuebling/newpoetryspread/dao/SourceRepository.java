package com.xuebling.newpoetryspread.dao;

import com.xuebling.newpoetryspread.pojo.literaturelib.Literature;
import org.springframework.data.mongodb.repository.MongoRepository;
import com.alibaba.fastjson.JSONArray;
import com.alibaba.fastjson.JSONObject;

public interface SourceRepository extends MongoRepository<JSONObject,String> {
}
