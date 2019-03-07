package com.xuebling.newpoetryspread.pojo.literaturelib.literature;

public class BaseType {
    //暂不考虑使用枚举类
    private String category;
    private String type;

    public BaseType(String category, String type) {
        this.category = category;
        this.type = type;
    }

    public String getCategory() {
        return category;
    }

    public void setCategory(String category) {
        this.category = category;
    }

    public String getType() {
        return type;
    }

    public void setType(String type) {
        this.type = type;
    }
}
