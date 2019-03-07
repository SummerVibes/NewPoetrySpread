package com.xuebling.newpoetryspread.pojo.literaturelib.literature;

/**
 * @Classname Authors
 * @Description TODO
 * @Date 2019/3/7 15:13
 * @Created by CQ
 */
public class Authors {
    private String name;
    private Integer index;

    public Authors(String name, Integer index) {
        this.name = name;
        this.index = index;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public Integer getIndex() {
        return index;
    }

    public void setIndex(Integer index) {
        this.index = index;
    }
}
