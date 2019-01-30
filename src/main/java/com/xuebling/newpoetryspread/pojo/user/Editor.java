package com.xuebling.newpoetryspread.pojo.user;

import org.bson.types.ObjectId;
import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.index.Indexed;
import org.springframework.data.mongodb.core.mapping.Document;

import javax.validation.constraints.*;

@Document(collection="User")
public class Editor {
    @Id
    @Indexed
    private ObjectId id = new ObjectId();//id
    @NotBlank(message = "用户名不能为空")
    private String account;
    @NotBlank(message = "密码不能为空")
    private String token;
    @NotBlank(message = "用户昵称不能为空")
    private String nickname;//昵称
    private String avatar = "";//头像名称
    @Max(value = 1, message = "lock为1锁定，lock为0解锁")
    @Min(value = 0, message = "lock为1锁定，lock为0解锁")
    private Integer lock;//锁定
    @Max(value = 3, message = "level最低为1，level最高为3")
    @Min(value = 1, message = "level最低为1，level最高为3")
    private Integer level = 0;//用户等级

    public ObjectId getId() {
        return id;
    }

    public void setId(ObjectId id) {
        this.id = id;
    }

    public String getAccount() {
        return account;
    }

    public void setAccount(String account) {
        this.account = account;
    }

    public String getToken() {
        return token;
    }

    public void setToken(String token) {
        this.token = token;
    }

    public String getNickname() {
        return nickname;
    }

    public void setNickname(String nickname) {
        this.nickname = nickname;
    }

    public String getAvatar() {
        return avatar;
    }

    public void setAvatar(String avatar) {
        this.avatar = avatar;
    }

    public Integer getLock() {
        return lock;
    }

    public void setLock(Integer lock) {
        this.lock = lock;
    }

    public Integer getLevel() {
        return level;
    }

    public void setLevel(Integer level) {
        this.level = level;
    }
}
