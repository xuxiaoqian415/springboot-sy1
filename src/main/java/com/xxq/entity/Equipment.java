package com.xxq.entity;

import lombok.Data;
import org.apache.ibatis.type.Alias;

import java.util.Date;

@Data
@Alias("Equipment")
public class Equipment {

    private Integer id;

    private String name;

    private String description;

    private String code;

    private Date addTime;

    private Float price;

    private String place;

    private Integer userId;

}
