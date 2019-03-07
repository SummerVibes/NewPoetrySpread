package com.xuebling.newpoetryspread.controller;

import com.alibaba.fastjson.JSONArray;
import com.alibaba.fastjson.JSONObject;
import com.mongodb.client.result.DeleteResult;

import com.xuebling.newpoetryspread.common.config.MongoConfig;
//import com.xuebling.newpoetryspread.dao.Impl.MongoRepositoryImpl;
import com.xuebling.newpoetryspread.dao.LibraryRepository;
import com.xuebling.newpoetryspread.dao.LiteratureRepository;
import com.xuebling.newpoetryspread.pojo.enums.ResponseMsg;
import com.xuebling.newpoetryspread.dao.SourceRepository;

import com.xuebling.newpoetryspread.pojo.literaturelib.Literature;
import com.xuebling.newpoetryspread.pojo.literaturelib.literature.BasePlace;
import com.xuebling.newpoetryspread.pojo.literaturelib.literature.BaseTime;
import com.xuebling.newpoetryspread.pojo.literaturelib.literature.BaseType;
import com.xuebling.newpoetryspread.pojo.result.Response;
//import com.xuebling.newpoetryspread.service.LiteratureService;
import com.xuebling.newpoetryspread.service.impl.LiteratureServiceImpl;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.mongodb.core.MongoTemplate;
import org.springframework.data.mongodb.core.query.Criteria;
import org.springframework.data.mongodb.core.query.Query;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.*;

import javax.json.Json;
import javax.json.JsonObject;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.StringReader;
import java.util.*;

@RestController
@RequestMapping("/lite")
public class LiteratureController {
    private Logger logger =  LoggerFactory.getLogger(this.getClass());

    @Autowired
    private LiteratureRepository literatureRepository;

    @Autowired
    LiteratureServiceImpl literatureService;


    @Autowired
    MongoTemplate mongoTemplate;
    @Autowired
    private LibraryRepository libraryRepository;
    @Autowired
    private HttpServletRequest request;

    @Autowired
    SourceRepository sourceRepository;

    private HttpServletResponse response;


    @PutMapping(path = "/add")
    public Object AddLite(@RequestBody JSONObject jsonObject){

        //在控制层不考虑过滤问题，将在业务层通过检查器过滤
        //适配基于javax.json的检查器，将fastjson转换为javax.json
        JsonObject jsonObjectx = Json.createReader(new StringReader(jsonObject.toString())).readObject();

        try {
            return literatureService.AddLiterature(jsonObjectx);
        }catch (Exception e){
            logger.info(e.toString());
            return new Response(ResponseMsg.FAIL);
        }

    }

    @DeleteMapping(path = "/delete")
    public Object DeleteLite(@RequestBody Literature literature, BindingResult bindingResult) {
        //不应该这么写，写完那个回来改
//        boolean ControllerState = literatureService.DeleteLiterature(literature);
//
//            if (ControllerState){
//                return new Response(ResponseMsg.SUCCESS);
//            }else{
//                return new Response(ResponseMsg.FAIL);
//            }
        return new Response(ResponseMsg.FAIL);
    }

    //修改文献，更新文献对象所有属性
    @PostMapping(path = "/change")
    public Object modifyLiterature(HttpServletRequest request, @RequestBody JSONObject object) {

//        Literature literature = literatureService.Exchange(object);
//
//        return literatureService.ChangeLiterature(literature);
        return new Response(ResponseMsg.FAIL);
    }


    @PostMapping(path = "/show")
    public Object addchange(HttpServletRequest request) {

        Query query = new Query();
        query.addCriteria(Criteria.where("statue").is("CountryName"));
        List<JSONObject> list = mongoTemplate.find(query, com.alibaba.fastjson.JSONObject.class,"Source");

        for (JSONObject OBJ : list){
            System.out.println(OBJ.toString());
        }

        return new  Response(ResponseMsg.SUCCESS);
    }
}
