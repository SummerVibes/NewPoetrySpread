package com.xuebling.newpoetryspread.checkers.implement;

import javax.json.JsonObject;

public class ListChecker extends Checker {  //同类对象列表检查器
    private int _min;
    private int _max;

    public ListChecker(CheckerFactory factory, JsonObject def) throws Exception {
        super(factory, def);
        String[] sizes = def.getString("size").split("\\.\\.");
        _min = Integer.parseInt(sizes[0]);
        if (sizes[1].toLowerCase().equals("n")) {
            _max = -1;
        } else {
            _max = Integer.parseInt(sizes[1]);
        }
    }

    public Boolean check(JsonObject obj)
    {
        Boolean res = super.check(obj);
        if(res) {
            int mySize = obj.getJsonArray(getKey()).size();
            if (_max >= _min) {
                res = mySize >= _min && mySize <= _max;
            } else {
                res = mySize >= _min;
            }
        }
        return res;
    }

    public String getType()
    {
        return "ListChecker";
    }
}

