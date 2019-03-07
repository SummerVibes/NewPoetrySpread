package com.xuebling.newpoetryspread.controller;

import com.alibaba.fastjson.JSONArray;
import com.alibaba.fastjson.JSONObject;
import com.xuebling.newpoetryspread.pojo.enums.ResponseMsg;
import com.xuebling.newpoetryspread.pojo.result.Response;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.mongodb.core.query.Criteria;
import org.springframework.data.mongodb.core.query.Query;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import org.springframework.data.mongodb.core.MongoTemplate;

import javax.servlet.http.HttpServletRequest;
import java.util.LinkedList;
import java.util.List;

/**
 * @Classname SourceController
 * @Description
 * @Date 2019/3/7 10:54
 * @Created by CQ
 */
@RestController
@RequestMapping("/source")
public class SourceController {
    private Logger logger =  LoggerFactory.getLogger(this.getClass());

    @Autowired
    MongoTemplate mongoTemplate;

    @PostMapping(path = "/getsource")
    public JSONObject getsource(HttpServletRequest request, @RequestBody JSONObject jsonObject) {
        //暂时不考虑json检查与过滤
        String statue = jsonObject.getString("statue");
        int group = jsonObject.getIntValue("group");
        int groupson = jsonObject.getIntValue("groupson");

        List<String> listgroup = getgroup(group);

        return getjsonobj(statue,listgroup,groupson);
    }

    public  List<String> getgroup(int group){
        List<String> list = new LinkedList<>();
        //通过ascii码定位,
        int A = 63;
        A += 5*(group - 1) + 1;
        for (int i = 0 ;i < 5;i++){
            list.add((String.valueOf((char)++A)));
        }
        if (group == 5 )
            list.add((String.valueOf((char)++A)));
        return list;
    }

    public JSONObject getjsonobj(String statue,List<String> keylist,int groupson){
        JSONObject jsonObject = new JSONObject();

        Query query = new Query();
        query.addCriteria(Criteria.where("statue").is(statue));
        List<JSONObject> list = mongoTemplate.find(query, com.alibaba.fastjson.JSONObject.class,"Source");
        JSONObject obj = list.get(0);

        //再一次达到O（N2）,如何在这种小问题上优化呢？？
        for (String key : keylist){
            int target = (groupson * 5) - 5;
            JSONArray jsonArray = obj.getJSONArray(key);
            JSONArray newjsonArray =  new JSONArray();
            for (int i = target;i< (target + 5);i++){
                if (i >=jsonArray.size()){
                    break;
                }else {
                    newjsonArray.add(jsonArray.get(i));
                }
            }
            jsonObject.put(key,newjsonArray);
        }
        return jsonObject;
    }
}
