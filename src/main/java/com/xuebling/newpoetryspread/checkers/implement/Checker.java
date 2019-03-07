package com.xuebling.newpoetryspread.checkers.implement;



import com.xuebling.newpoetryspread.checkers.implement.CheckerFactory;
import com.xuebling.newpoetryspread.checkers.interfaces.IChecker;


import javax.json.JsonObject;
import javax.json.JsonValue;
import java.util.ArrayList;
import java.util.List;

public class Checker implements IChecker {
    private CheckerFactory _myFactory;
    private String _name;
    private String _key;
    private Boolean _necessary;
    private List<IChecker> _childs;

    public Checker(CheckerFactory factory, JsonObject def) throws Exception {
        _childs = new ArrayList<IChecker>();
        _myFactory = factory;
        _name = def.getString("name");
        _key = def.getString("key");
        _necessary = def.getBoolean("necessary");
        if(def.containsKey("childs")) {
            for(JsonValue obj : def.getJsonArray("childs")) {
                _childs.add(_myFactory.CreateChecker(obj.asJsonObject()));
            }
        }
    }

    public Boolean check(JsonObject obj) {
        Boolean res = !_necessary || obj.containsKey(getKey());
        if (obj.containsKey(getKey()) && !getChilds().isEmpty()) {
            JsonValue ov = obj.get(getKey());
            if (ov.getValueType()==JsonValue.ValueType.ARRAY) {
                for (JsonValue v : ov.asJsonArray()) {
                    for(IChecker c : getChilds()) {
                        if(!(res = c.check(v.asJsonObject()))) break;
                    }
                    if(!res) break;
                }
            } else {
                for (IChecker c : _childs) {
                    if(!(res = c.check(ov.asJsonObject()))) break;
                }
            }
        }
        return res;
    }

    public String getName()
    {
        return _name;
    }

    public String getKey()
    {
        return _key;
    }
    public Boolean isNecessary()
    {
        return _necessary;
    }

    public String getType()
    {
        return "Checker";
    }

    public void setFactory(CheckerFactory factory) {
        _myFactory = factory;
    }

    public CheckerFactory getFactory() {
        return _myFactory;
    }

    public List<IChecker> getChilds() {
        return _childs;
    }


}
