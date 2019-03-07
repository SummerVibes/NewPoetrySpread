package com.xuebling.newpoetryspread.common.checkersimpl;

import com.xuebling.newpoetryspread.checkers.implement.CheckerFactory;
import com.xuebling.newpoetryspread.checkers.interfaces.IChecker;
import org.springframework.beans.factory.annotation.Value;

import javax.json.Json;
import javax.json.JsonArray;
import javax.json.JsonObject;
import javax.json.JsonValue;
import java.io.FileInputStream;
import java.util.Set;

/**
 * @Classname literature
 * @Description 实例化检查器
 * @Date 2019/2/20 13:48
 * @Created by CQ
 */

public class LiteratureChecker {
    private CheckerFactory _factory;

    //文献模板位置
    @Value("${templatePath}")
    private String templatePath;


    public LiteratureChecker() throws Exception {//需要给定模型定义文件的全路径
        //这里需要把路径硬编码到代码里，因为我在设置注解的时候想自动实例化checker，发现失败，我就没有继续去查为什么了，但是配置文件里的文献模板位置已经写好，希望后面的人解决
        JsonObject defs = Json.createReader(new FileInputStream("E:/IdeaProject/new/q/NewPoetrySpread/src/main/java/com/xuebling/newpoetryspread/common/checkersimpl/resources/Defines.json")).readObject();
        setFactory(new CheckerFactory(defs));
    }

    public Boolean check(JsonObject lit) throws Exception {
        Boolean res = true;
        IChecker checker;
        JsonValue pv;
        JsonObject model = _factory.getContent(lit.getString("type")).asJsonObject();
        Set<String> properties = model.keySet();
        for (String p : properties) {
            pv = model.get(p);
            if (p.equals("base")) { //基础模型的名称
                String pvStr = pv.toString().replace("\"","");  //解决本地不兼容问题，将多余 " ,替代pv.tostring()
                JsonArray base = _factory.getContent(pvStr).asJsonArray();
                for (JsonValue b : base) {   //检查器名称数组
                    String bStr = b.toString().replace("\"","");   //解决本地不兼容问题，将多余 " ,替代pv.tostring()
                    checker = _factory.getChecker(bStr);
                    res = checker.check(lit);
                    if (!res) break;
                }
            } else {

                switch (pv.getValueType()) {
                    case ARRAY:  //检查器名称数组
                        JsonArray cs = pv.asJsonArray();
                        for (JsonValue c : cs) {
                            //解决本地不兼容问题，将多余 " ,替代pv.tostring()
                            String cStr = c.toString().replace("\"","");
                            checker = _factory.getChecker(cStr);
                            res = checker.check(lit);
                            if (!res) break;
                        }
                        break;
                    case STRING:  //单个检查器名称
                        //解决本地不兼容问题，将多余 " ,替代pv.tostring()
                        String pvStr = pv.toString().replace("\"","");
                        checker = _factory.getChecker(pvStr);
                        res = checker.check(lit);
                        break;
                    default:        //检查器定义
                        checker = _factory.CreateChecker(pv.asJsonObject());
                        res = checker.check(lit);
                }
            }
            if (!res) break;
        }
        return res;
    }

    public void setFactory(CheckerFactory f)
    {
        _factory = f;
    }
}
