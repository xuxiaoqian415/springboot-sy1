package com.xxq.dto;

import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import lombok.Data;

@Data
@ApiModel("当前登录用户session类")
public class UserDto {

    @ApiModelProperty("用户ID")
    private Integer id;

    @ApiModelProperty("登录名")
    private String loginName;

    @ApiModelProperty("真实姓名")
    private String realName;

    @ApiModelProperty("电话")
    private String tel;

    @ApiModelProperty("邮箱")
    private String email;

    @ApiModelProperty("用户类型 0管理员 1普通用户")
    private Integer type;
}
