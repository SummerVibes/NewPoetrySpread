package com.xuebling.newpoetryspread.service.impl;

import com.alibaba.fastjson.JSONObject;
import com.xuebling.newpoetryspread.common.Transformation.JsonObj2Lite;
import com.xuebling.newpoetryspread.common.checkersimpl.LiteratureChecker;
import com.xuebling.newpoetryspread.dao.LiteratureRepository;

import com.xuebling.newpoetryspread.pojo.enums.ResponseMsg;
import com.xuebling.newpoetryspread.pojo.literaturelib.Literature;
import com.xuebling.newpoetryspread.pojo.literaturelib.literature.BasePlace;
import com.xuebling.newpoetryspread.pojo.literaturelib.literature.BaseTime;
import com.xuebling.newpoetryspread.pojo.literaturelib.literature.BaseType;
import com.xuebling.newpoetryspread.pojo.result.Response;
import com.xuebling.newpoetryspread.pojo.result.ResponseData;
import com.xuebling.newpoetryspread.service.LiteratureService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;

import javax.json.JsonObject;
import java.util.Iterator;
import java.util.Set;

@Service
public class LiteratureServiceImpl implements LiteratureService{

    @Autowired
    LiteratureRepository literatureRepository;

    //文献模板位置
    @Value("${templatePath}")
    private String templatePath;

    //文献wholeText存储地址
    @Value("${litesavePath}")
    private String litesavePath;

    private LiteratureChecker literatureChecker;
    @Override
    public Object AddLiterature(JsonObject jsonObject) {
        try {

            literatureChecker = new LiteratureChecker();

            if (literatureChecker.check(jsonObject)){
                //检查成功
                //javax.json被检查成功以后，将其转化为fastjson
                JSONObject exjsonObject = JSONObject.parseObject(jsonObject.toString());
                JsonObj2Lite jsonObj2Lite = new JsonObj2Lite(exjsonObject);
                Literature literature = jsonObj2Lite.getLiterature();


                literatureRepository.save(literature);
                return new Response(ResponseMsg.SUCCESS);

            }else{

                return new Response(ResponseMsg.FAIL);
            }

        }catch (Exception e){
            System.out.println("异常");
            return new Response(ResponseMsg.FAIL);
        }

    }

    @Override
    public Object DeleteLiterature(JsonObject jsonObject) {
        try {

            literatureChecker = new LiteratureChecker();

            if (literatureChecker.check(jsonObject)){
                //检查成功
                //javax.json被检查成功以后，将其转化为fastjson
                JSONObject exjsonObject = JSONObject.parseObject(jsonObject.toString());
                JsonObj2Lite jsonObj2Lite = new JsonObj2Lite(exjsonObject);
                Literature literature = jsonObj2Lite.getLiterature();

                literature.setId(exjsonObject.getString("id"));

//                literature.set .... 更改文献状态为已删除，更新库


                literatureRepository.save(literature);
                return new Response(ResponseMsg.SUCCESS);

            }else{

                return new Response(ResponseMsg.FAIL);
            }

        }catch (Exception e){

            return new Response(ResponseMsg.FAIL);
        }
    }

    @Override
    public Object ChangeLiterature(JsonObject jsonObject) {
        try {

            literatureChecker = new LiteratureChecker();

            if (literatureChecker.check(jsonObject)){
                //检查成功
                //javax.json被检查成功以后，将其转化为fastjson
                JSONObject exjsonObject = JSONObject.parseObject(jsonObject.toString());

                JsonObj2Lite jsonObj2Lite = new JsonObj2Lite(exjsonObject);
                Literature literature = jsonObj2Lite.getLiterature();

                literature.setId(exjsonObject.getString("id"));

//                literature.set .... 更新库


                literatureRepository.save(literature);
                return new Response(ResponseMsg.SUCCESS);

            }else{

                return new Response(ResponseMsg.FAIL);
            }

        }catch (Exception e){

            return new Response(ResponseMsg.FAIL);
        }
    }

    public String saveWholeText(String wholetext){
        //此函数将wholetext 存入硬盘，然后返回wholetext相对地址
        boolean res = true;


        String address = new String();
        return address;
    }
}
