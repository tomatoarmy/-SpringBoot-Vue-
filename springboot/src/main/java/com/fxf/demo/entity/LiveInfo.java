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
@TableName(value = "1bishe.sys_liveInfo")
public class LiveInfo {
    @TableId(type = IdType.AUTO,value = "liveId")
    private int liveId;
    @TableField(value = "roomId")
    private int roomId;
    @TableField(value = "customerName")
    private String customerName;
    @TableField(value = "customerId")
    private int customerId;
    @TableField(value = "customerGender")
    private int customerGender;
    @TableField(value = "customerPhone")
    private int customerPhone;
    @TableField(value = "customerPop")
    private int customerPop;
    @TableField(value = "liveDays")
    private int liveDays;
    @TableField(value = "checkInDate")
    private Date checkInDate;
    @TableField(value = "leaveDate")
    private Date leaveDate;
    @TableField(value = "currentDeposit")
    private int currentDeposit;
    @TableField(value = "depositLeft")
    private Integer depositLeft;

}
