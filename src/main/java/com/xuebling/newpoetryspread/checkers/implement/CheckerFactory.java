package com.xuebling.newpoetryspread.checkers.implement;


import com.xuebling.newpoetryspread.checkers.interfaces.IChecker;


import javax.json.JsonObject;
import javax.json.JsonValue;
import java.lang.reflect.Constructor;
import java.util.HashMap;
import java.util.Map;

public class CheckerFactory {
    private Map<String, String> _classes;
    private Map<String, IChecker> _checkers;
    private JsonObject _contents;

    //创建检查器
    public IChecker CreateChecker(JsonObject def) throws Exception {

        String newdef = def.getString("checker").replace("\"","");
        String classname =  _classes.get(newdef).replace("\"","");
        Class<?> myClass = Class.forName(classname);
        Constructor<?> myConstructor = myClass.getConstructor(this.getClass(), JsonObject.class);
        return  (IChecker) myConstructor.newInstance(this, def);

    }

    public CheckerFactory(JsonObject defs) throws Exception {
        JsonValue ov;
        //获取检查器类，每个检查器类的名称必须唯一，且其值应是类的全名（包括.class）
        String cName;
        _classes = new HashMap<String, String>();
        JsonObject dcs = defs.getJsonObject("checkers");
        for(String ckey : dcs.keySet()) {
            ckey = ckey.replace("\"","");
            ov = dcs.get(ckey);
            if (ov.getValueType()==JsonValue.ValueType.STRING) {
                cName = ov.toString();
                //将 " 替代为空，若无 " ,则没有作用
                cName = cName.replace("\"","");
                if(!cName.endsWith(".class"))
                    throw new CheckerException("the class name must contains the name of package and ends with .class");
                _classes.put(ckey, cName.substring(0, cName.indexOf(".class")));
            }
        }

        //构建检查器
        _checkers = new HashMap<String, IChecker>();
        for (String ckey : dcs.keySet()) {
            ckey = ckey.replace("\"","");
            ov = dcs.get(ckey);
            if (ov.getValueType()==JsonValue.ValueType.OBJECT) {
                if(ov.asJsonObject().isEmpty()) throw new CheckerException("the definition of checker is empty.");
                _checkers.put(ckey, CreateChecker(ov.asJsonObject()));
            }
        }
        //获取内容模型定义
        _contents = defs.getJsonObject("contents");

    }

    public IChecker getChecker(String name) throws Exception {
        return (IChecker)(_checkers.containsKey(name) ? _checkers.get(name) : null);
    }

    public JsonValue getContent(String cName) {
        return _contents.get(cName);
    }
}




