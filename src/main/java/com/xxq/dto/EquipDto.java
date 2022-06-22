package com.xxq.dto;

import com.alibaba.fastjson.annotation.JSONField;
import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import lombok.Data;
import org.springframework.format.annotation.DateTimeFormat;

import java.util.Date;

@Data
@ApiModel("设备信息dto类")
public class EquipDto {

    @ApiModelProperty("设备ID")
    private Integer id;

    @ApiModelProperty("设备名称")
    private String name;

    @ApiModelProperty("设备描述")
    private String description;

    @ApiModelProperty("设备编号")
    private String code;

    @ApiModelProperty("添加时间")
    @DateTimeFormat(pattern = "yyyy-MM-dd HH:mm:ss")
    @JSONField(format="yyyy-MM-dd HH:mm:ss")
    private Date addTime;

    @ApiModelProperty("价格")
    private Float price;

    @ApiModelProperty("存放地点")
    private String place;

    /**
     * userId为0时表示设备未分配
     */
    @ApiModelProperty("所属用户ID")
    private Integer userId;

    @ApiModelProperty("所属用户姓名")
    private String userName;

}
