package com.xuebling.newpoetryspread.pojo.user;

import org.springframework.data.mongodb.core.mapping.Document;

@Document(collection="User")
public class Admin extends Editor{
}
