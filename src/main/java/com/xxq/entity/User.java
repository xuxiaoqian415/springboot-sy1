package com.xxq.entity;

import lombok.Data;
import org.apache.ibatis.type.Alias;

import java.util.Date;

@Data
@Alias("User")
public class User {

    private Integer id;

    private String loginName;

    private String realName;

    private String password;

    private String tel;

    private String email;

    /**
     * 0管理员 1普通用户
     */
    private Integer type;

    private Date lastLoginTime;
}
