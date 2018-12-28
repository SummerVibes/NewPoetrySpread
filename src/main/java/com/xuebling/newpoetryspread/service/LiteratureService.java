package com.xuebling.newpoetryspread.service;

import com.alibaba.fastjson.JSONObject;
import com.xuebling.newpoetryspread.pojo.literaturelib.Literature;


public interface LiteratureService {
    //修改文献状态为删除
    boolean DeleteLiterature(Literature literature);
    //json转换为Literature
    Literature Exchange(JSONObject object);
    //修改已发布的文献信息
    Object ChangeLiterature(Literature literature);

}
