package com.xuebling.newpoetryspread.pojo.literaturelib;

import com.xuebling.newpoetryspread.pojo.literaturelib.literature.Authors;
import com.xuebling.newpoetryspread.pojo.literaturelib.literature.BasePlace;
import com.xuebling.newpoetryspread.pojo.literaturelib.literature.BaseTime;
import com.xuebling.newpoetryspread.pojo.literaturelib.literature.BaseType;
import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.index.Indexed;
import org.springframework.data.mongodb.core.mapping.Document;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Null;
import java.io.Serializable;
import java.util.ArrayList;

@Document(collection="#{mongoConfig.getCollectionName()}")
public class Literature implements Serializable {
    @Id
    @Indexed
    private String id;
    @NotNull
    private String mediaType;//媒体类型,txt | picture | audio | video,暂时不用枚举类吧
    @NotNull
    private BaseType studyType;//研究类型
    @NotNull
    private BaseType docType;//文献类型
    @NotNull
    private boolean isTranslated;
    @NotNull
    private String title;
    @NotNull
    private ArrayList<String> authors;
    @NotNull
    private ArrayList<Authors> newauthors;
    @NotNull
    private String language;
    @NotNull
    private  String state;

    private String type;    //新增字段

    private ArrayList<String> editors;//编辑者
    private ArrayList<String> keywords;//关键字
    private String wholeText;//全文



    private String subject;//主题
    private String digest;//摘要
    private String source;//
    private BasePlace publishPlace;
    private BaseTime publishTime;
    private BaseTime createTime;
    private BasePlace createPlace;

    @Null
    private ArrayList<String> files;//等等再收拾,要有一个文件名,一个路径

    public Literature() { }

    public Literature(@NotNull String mediaType, @NotNull BaseType studyType, @NotNull BaseType docType, @NotNull boolean isTranslated, @NotNull String title, @NotNull ArrayList<Authors> authors, @NotNull String language, @NotNull String state, String type, String wholeText) {
        this.mediaType = mediaType;
        this.studyType = studyType;
        this.docType = docType;
        this.isTranslated = isTranslated;
        this.title = title;
        this.newauthors = authors;
        this.language = language;
        this.state = state;
        this.type = type;
        this.wholeText = wholeText;
    }

    public ArrayList<Authors> getNewauthors() {
        return newauthors;
    }

    public void setNewauthors(ArrayList<Authors> newauthors) {
        this.newauthors = newauthors;
    }

    public String getType() {
        return type;
    }

    public void setType(String type) {
        this.type = type;
    }

    public String getState() {
        return state;
    }

    public void setState(String state) {
        this.state = state;
    }

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public String getMediaType() {
        return mediaType;
    }

    public void setMediaType(String mediaType) {
        this.mediaType = mediaType;
    }

    public BaseType getStudyType() {
        return studyType;
    }

    public void setStudyType(BaseType studyType) {
        this.studyType = studyType;
    }

    public BaseType getDocType() {
        return docType;
    }

    public void setDocType(BaseType docType) {
        this.docType = docType;
    }

    public boolean isTranslated() {
        return isTranslated;
    }

    public void setTranslated(boolean translated) {
        isTranslated = translated;
    }

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public ArrayList<String> getAuthors() {
        return authors;
    }

    public void setAuthors(ArrayList<String> authors) {
        this.authors = authors;
    }

    public String getLanguage() {
        return language;
    }

    public void setLanguage(String language) {
        this.language = language;
    }

    public ArrayList<String> getEditors() {
        return editors;
    }

    public void setEditors(ArrayList<String> editors) {
        this.editors = editors;
    }

    public ArrayList<String> getKeywords() {
        return keywords;
    }

    public void setKeywords(ArrayList<String> keywords) {
        this.keywords = keywords;
    }

    public String getWholeText() {
        return wholeText;
    }

    public void setWholeText(String wholeText) {
        this.wholeText = wholeText;
    }

    public String getSubject() {
        return subject;
    }

    public void setSubject(String subject) {
        this.subject = subject;
    }

    public String getDigest() {
        return digest;
    }

    public void setDigest(String digest) {
        this.digest = digest;
    }

    public String getSource() {
        return source;
    }

    public void setSource(String source) {
        this.source = source;
    }

    public BasePlace getPublishPlace() {
        return publishPlace;
    }

    public void setPublishPlace(BasePlace publishPlace) {
        this.publishPlace = publishPlace;
    }

    public BaseTime getPublishTime() {
        return publishTime;
    }

    public void setPublishTime(BaseTime publishTime) {
        this.publishTime = publishTime;
    }

    public BaseTime getCreateTime() {
        return createTime;
    }

    public void setCreateTime(BaseTime createTime) {
        this.createTime = createTime;
    }

    public BasePlace getCreatePlace() {
        return createPlace;
    }

    public void setCreatePlace(BasePlace createPlace) {
        this.createPlace = createPlace;
    }

    public ArrayList<String> getFiles() {
        return files;
    }

    public void setFiles(ArrayList<String> files) {
        this.files = files;
    }
}
