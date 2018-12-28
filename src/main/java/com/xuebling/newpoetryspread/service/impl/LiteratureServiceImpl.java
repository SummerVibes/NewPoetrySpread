package com.xuebling.newpoetryspread.service.impl;

import com.alibaba.fastjson.JSONObject;
import com.xuebling.newpoetryspread.dao.LiteratureRepository;
import com.xuebling.newpoetryspread.pojo.enums.GetJsonObject;
import com.xuebling.newpoetryspread.pojo.enums.ResponseMsg;
import com.xuebling.newpoetryspread.pojo.literaturelib.Literature;
import com.xuebling.newpoetryspread.pojo.literaturelib.literature.BasePlace;
import com.xuebling.newpoetryspread.pojo.literaturelib.literature.BaseTime;
import com.xuebling.newpoetryspread.pojo.literaturelib.literature.BaseType;
import com.xuebling.newpoetryspread.pojo.result.Response;
import com.xuebling.newpoetryspread.pojo.result.ResponseData;
import com.xuebling.newpoetryspread.service.LiteratureService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.Iterator;
import java.util.Set;

@Service
public class LiteratureServiceImpl implements LiteratureService{

    @Autowired
    private LiteratureRepository literatureRepository;

    @Override
    public boolean DeleteLiterature(Literature literature) {
        //不应该这莫写，
        //判断库里是否存在该id文献
        boolean exist = literatureRepository.findById(literature.getId()).isPresent();
        System.out.println(literature.getId());
        if (exist){
            Literature newliterature = literatureRepository.findById(literature.getId()).get();
            newliterature.setState("delete");
            try{
                literatureRepository.save(newliterature);
            }catch (Exception e){
                return false;         //不应该这么写！
            }
            return true;
        }else{
            return false;
        }
    }

    @Override
    public Literature Exchange(JSONObject object) {
        //Literature 与 JsonObject 实现转换
        //效率很低，先测试

        boolean exist = literatureRepository.findById(object.getString(GetJsonObject.ID.getKeymessage())).isPresent();
        if (exist){
            Literature literature = literatureRepository.findById(object.getString(GetJsonObject.ID.getKeymessage())).get();

            literature.setId(object.getString(GetJsonObject.ID.getKeymessage()));    //文献ID
            literature.setMediaType(object.getString(GetJsonObject.MEDTYOE.getKeymessage()));  //媒体类型

            literature.setTranslated(object.getBoolean(GetJsonObject.ISTRANSLATED.getKeymessage()));   //isTranslated
            literature.setTitle(object.getString(GetJsonObject.TITLE.getKeymessage()));   //titile


            //literature.setFiles();   //文件路径
            //literature.setSource();   source指的是？？？
            //literature.setDigest();   digest指的是？？？
            //literature.setSubject();  subject指的是？？？
            literature.setState(object.getString(GetJsonObject.STATE.getKeymessage()));     //文件状态，删除|审核
            literature.setWholeText(object.getString(GetJsonObject.FILE.getKeymessage()));   //文件全文
            literature.setLanguage(object.getString(GetJsonObject.LANGUAGE.getKeymessage()));  //语言

            //测试
            BaseType bt = new BaseType();
            bt.setCategory("类型");
            bt.setType("类型");

            literature.setStudyType(bt);
            literature.setDocType(bt);

            BasePlace bp = new BasePlace();
            bp.setBeforePlace("1");
            bp.setNowPlace("1");

            literature.setCreatePlace(bp);
            literature.setPublishPlace(bp);

            BaseTime btime = new BaseTime();
            btime.setGregorian("2");
            btime.setMinguo("2");
            literature.setCreateTime(btime);
            literature.setPublishTime(btime);
            return literature;
        }else{
            return null;
        }


    }

    @Override
    public Object ChangeLiterature(Literature literature) {
        //本想使用updateFirst只更新部分字段，但是由于要更新的字段很多，而且不知道那个字段要更新，还是用save
        try{
           literatureRepository.save(literature);
           return new Response(ResponseMsg.SUCCESS);
        }catch (Exception e){
            return new Response(ResponseMsg.FAIL);
        }
    }
}
