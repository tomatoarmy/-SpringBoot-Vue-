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
@TableName(value = "sys_weekCount")
public class WeekCount {
    @TableId(type = IdType.AUTO,value = "weekId")
    private Integer weekId;
    @TableField(value = "monday")
    private Integer monday;
    @TableField(value = "tuesday")
    private Integer tuesday;
    @TableField(value = "wednesday")
    private Integer wednesday;
    @TableField(value = "thursday")
    private Integer thursday;
    @TableField(value = "friday")
    private Integer friday;
    @TableField(value = "saturday")
    private Integer saturday;
    @TableField(value = "sunday")
    private Integer sunday;
    @TableField(value = "income")
    private Integer income;
    @TableField(value = "createDate")
    private Date createDate;
    @TableField(value = "orderNum")
    private Integer orderNum;
}
