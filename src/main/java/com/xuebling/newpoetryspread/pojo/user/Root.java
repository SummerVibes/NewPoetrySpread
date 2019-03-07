package com.xuebling.newpoetryspread.pojo.user;

import org.springframework.data.mongodb.core.mapping.Document;

import javax.validation.constraints.NotNull;

@Document(collection="User")
public class Root extends Admin{

}
