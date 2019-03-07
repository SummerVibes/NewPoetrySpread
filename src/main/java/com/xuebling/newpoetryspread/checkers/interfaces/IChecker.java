package com.xuebling.newpoetryspread.checkers.interfaces;

import javax.json.JsonObject;

public interface IChecker {
    public String  getKey();
    public Boolean check(JsonObject obj);
    public String getName();
}
