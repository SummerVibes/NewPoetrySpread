package com.xuebling.newpoetryspread.service;



import com.alibaba.fastjson.JSONObject;
import com.xuebling.newpoetryspread.pojo.literaturelib.Literature;
import javax.json.Json;
import javax.json.JsonArray;
import javax.json.JsonObject;
import javax.json.JsonValue;

public interface LiteratureService {

    //修改已发布的文献信息
    Object ChangeLiterature(JsonObject jsonObject);
    //添加文献
    Object AddLiterature(JsonObject jsonObject);
    //删除文献，但不删除文献本身，
    Object DeleteLiterature(JsonObject jsonObject);

}
