package com.xuebling.newpoetryspread.common.Transformation;

import com.alibaba.fastjson.JSONArray;
import com.alibaba.fastjson.JSONObject;
import com.xuebling.newpoetryspread.pojo.enums.GetJsonObject;
import com.xuebling.newpoetryspread.pojo.literaturelib.Literature;
import com.xuebling.newpoetryspread.pojo.literaturelib.literature.Authors;
import com.xuebling.newpoetryspread.pojo.literaturelib.literature.BaseType;

import javax.validation.constraints.NotNull;
import java.util.ArrayList;

/**
 * @Classname JsonObj2Lite
 * @Description 将fastjson转换为literature对象
 * @Date 2019/3/7 13:35
 * @Created by CQ
 */
public class JsonObj2Lite {
    private Literature literature;
    private JSONObject jsonObject;

    public JsonObj2Lite(){}

    public JsonObj2Lite(JSONObject jsonObject) {
        this.jsonObject = jsonObject;

        String mediaType = jsonObject.getString(GetJsonObject.MEDIATYPE.getKeymessage());

        JSONObject studyjsonObject = jsonObject.getJSONObject(GetJsonObject.STUDYTYPE.getKeymessage());
        BaseType studyType = new BaseType(studyjsonObject.getString("category"),jsonObject.getString("type"));

        JSONObject docjsonObject = jsonObject.getJSONObject(GetJsonObject.DOCTYPE.getKeymessage());
        BaseType docType =  new BaseType(studyjsonObject.getString("category"),jsonObject.getString("type"));

        boolean isTranslated = jsonObject.getBoolean(GetJsonObject.ISTRANSLATED.getKeymessage());

        String title = jsonObject.getString(GetJsonObject.TITLE.getKeymessage());

        JSONArray authorsjsonarry = jsonObject.getJSONArray(GetJsonObject.AUTHORS.getKeymessage());
        ArrayList<Authors> authors = new ArrayList<>();
        for (int i = 0;i<authorsjsonarry.size();i++){
            String name = authorsjsonarry.getJSONObject(i).getString("name");
            Integer integer = authorsjsonarry.getJSONObject(i).getIntValue("index");
            authors.add(new Authors(name,integer));
        }

        String language = jsonObject.getString(GetJsonObject.LANGUAGE.getKeymessage());
        String state = jsonObject.getString(GetJsonObject.STATE.getKeymessage());
        String type = jsonObject.getString(GetJsonObject.TYPE.getKeymessage());
        String wholetext = jsonObject.getString(GetJsonObject.WHOLETEXT.getKeymessage());

        this.literature = new Literature(mediaType,studyType,docType,isTranslated,title,authors,language,state,type,wholetext);



    }

    public Literature getLiterature() {
        return literature;
    }

    public void setLiterature(Literature literature) {
        this.literature = literature;
    }
}
