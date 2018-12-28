package com.xuebling.newpoetryspread.pojo.enums;

import javax.xml.transform.Result;

public enum GetJsonObject {

    ID("ID"),
    MEDTYOE("medType"),
    RESTYPE("resType"),
    ISTRANSLATED("isTranslated"),
    TITLE("title"),
    AUTHORS("authors"),
    FILE("file"),
    ACCESSORIESS("accessories"),
    KEYWORDS("keywords"),
    EDITORS("editors"),
    DTPUBLIC("dtPublic"),
    CREATIVETIME("creativetime"),
    LANGUAGE("language"),
    PUBPLACE("pubPlace"),
    CREATIVEPLACE("creativePlace"),
    STATE("state"),
    ;
    private String keymessage;
    GetJsonObject(String keymessage){
        this.keymessage = keymessage;
    }

    public String getKeymessage() {
        return keymessage;
    }
}
