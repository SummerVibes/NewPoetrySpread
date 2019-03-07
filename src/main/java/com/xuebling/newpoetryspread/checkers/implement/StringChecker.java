package com.xuebling.newpoetryspread.checkers.implement;

import javax.json.JsonObject;
import java.util.regex.Pattern;

public class StringChecker extends Checker {  //字符串检查器
    private Pattern pattern;

    public StringChecker(CheckerFactory factory, JsonObject def) throws Exception {
        super(factory, def);
        String f = "";
        if(def.containsKey("format")) {
            f = def.getString("format").trim();
        }
        setPattern("");
    }

    public Boolean check(JsonObject obj)
    {
        return super.check(obj) && ((pattern == null) || pattern.matcher(obj.getString(getKey())).matches());
    }

    public String getPattern()
    {
        return pattern.pattern();
    }

    public void setPattern(String aFormat)
    {
        if (aFormat.trim().equals("")) {
            pattern = null;
        } else {
            pattern = Pattern.compile(aFormat);
        }
    }

    public String getType()
    {
        return "StringChecker";
    }
}
