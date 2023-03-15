package com.fxf.demo.entity;

import com.baomidou.mybatisplus.annotation.IdType;
import com.baomidou.mybatisplus.annotation.TableField;
import com.baomidou.mybatisplus.annotation.TableId;
import com.baomidou.mybatisplus.annotation.TableName;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.Date;

@Data
@NoArgsConstructor
@AllArgsConstructor
@TableName(value = "sys_dayCount")
public class DayCount {
    @TableId(type = IdType.AUTO,value = "dayId")
    private Integer dayId;
    @TableField(value = "firstS")
    private Integer firstS;
    @TableField(value = "secondS")
    private Integer secondS;
    @TableField(value = "thirdS")
    private Integer thirdS;
    @TableField(value = "fourthS")
    private Integer fourthS;
    @TableField(value = "income")
    private Integer income;
    @TableField(value = "createDate")
    private Date createDate;
    @TableField(value = "orderNum")
    private Integer orderNum;


}
