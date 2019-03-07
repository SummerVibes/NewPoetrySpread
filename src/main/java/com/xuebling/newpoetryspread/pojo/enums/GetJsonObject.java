package com.xuebling.newpoetryspread.pojo.enums;

import javax.xml.transform.Result;

public enum GetJsonObject {


    MEDIATYPE("mediaType"),
    STUDYTYPE("studyType"),
    DOCTYPE("docType"),
    ISTRANSLATED("isTranslated"),
    TITLE("title"),
    AUTHORS("authors"),
    LANGUAGE("language"),
    STATE("state"),
    TYPE("type"),
    WHOLETEXT("wholeText")
//    PUBPLACE("pubPlace"),
//    CREATIVEPLACE("creativePlace"),
//    FILE("file"),
//    ACCESSORIESS("accessories"),
//    KEYWORDS("keywords"),
//    EDITORS("editors"),
//    DTPUBLIC("dtPublic"),
//    CREATIVETIME("creativetime"),

    ;
    private String keymessage;

    GetJsonObject(String keymessage){
        this.keymessage = keymessage;
    }

    public String getKeymessage() {
        return keymessage;
    }
}
