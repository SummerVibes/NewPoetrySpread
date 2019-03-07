package com.xuebling.newpoetryspread.checkers.implement;

import javax.json.JsonArray;
import javax.json.JsonObject;
import javax.json.JsonValue;

public class OrderedListChecker extends ListChecker {  //有序的同类对象列表检查器
    private String _orderby;
    public OrderedListChecker(CheckerFactory factory, JsonObject def) throws Exception {
        super(factory, def);
        _orderby = def.getString("orderby");
    }

    public Boolean check(JsonObject obj)
    {
        Boolean res = super.check(obj);
        if (res) {
            int i;
            JsonArray items = obj.getJsonArray(getKey());
            int[] idxs = new int[items.size()];
            for(int k : idxs) k = 0;
            for(JsonValue v : items) {
                i = v.asJsonObject().getInt(_orderby) - 1;
                if((i >= idxs.length) || (idxs[i] != 0)) {
                    res = false;
                    break;
                }
                idxs[i] = i;
            }
        }
        return res;
    }

    public String getType()
    {
        return "OrderedListChecker";
    }

    public String getOrderby() {
        return _orderby;
    }


}
