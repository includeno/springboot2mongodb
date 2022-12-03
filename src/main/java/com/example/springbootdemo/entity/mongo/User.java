package com.example.springbootdemo.entity.mongo;

import lombok.Data;
import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.mapping.Document;

import java.util.Date;

@Data
@Document("user")
public class User {
    @Id
    private String id;//mongodb string类型主键

    private String username;
    private String password;
    private Long age;
    private Date createTime;
    private Date updateTime;
}
