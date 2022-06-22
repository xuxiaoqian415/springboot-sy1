package com.xxq.dto;

import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import lombok.Data;
import org.apache.ibatis.type.Alias;

@Data
@ApiModel("搜索dto类")
@Alias("SearchDto")
public class SearchDto {

    @ApiModelProperty("id")
    private Integer id;

    @ApiModelProperty("设备所属用户id")
    private Integer userId;
}
