package com.xxq.dto;

import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import lombok.Data;

@Data
@ApiModel("用户注册dto类")
public class RegisterDto {

    @ApiModelProperty("登录名")
    private String loginName;

    @ApiModelProperty("真实姓名")
    private String realName;

    @ApiModelProperty("密码")
    private String password;

    @ApiModelProperty("重复密码")
    private String againPsw;

    @ApiModelProperty("电话")
    private String tel;

    @ApiModelProperty("邮箱")
    private String email;

    @ApiModelProperty("用户id-修改密码时使用")
    private Integer userId;

    @ApiModelProperty("原密码-修改密码时使用")
    private String oldPsw;
}
