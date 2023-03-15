package com.fxf.demo.entity;

import com.baomidou.mybatisplus.annotation.IdType;
import com.baomidou.mybatisplus.annotation.TableField;
import com.baomidou.mybatisplus.annotation.TableId;
import com.baomidou.mybatisplus.annotation.TableName;
import io.swagger.models.auth.In;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.Date;

@AllArgsConstructor
@NoArgsConstructor
@Data
@TableName(value = "1bishe.sys_order")
public class Order {
    @TableId(type = IdType.AUTO,value = "orderId")
    private Integer orderId;
    @TableField(value = "liveInfoId")
    private Integer liveInfoId;
    @TableField(value = "roomId")
    private Integer roomId;
    @TableField(value = "roomType")
    private Integer roomType;
    @TableField(value = "orderDate")
    private Date orderDate;
    @TableField(value = "liveDays")
    private Integer liveDays;
    @TableField(value = "spend")
    private Integer spend;
    @TableField(value = "consume")
    private Integer consume;
    @TableField(value = "totalSpend")
    private Integer totalSpend;
    @TableField(value = "eName")
    private String eName;
    @TableField(value = "note")
    private String note;

    public Order(int liveId, int roomId, int roomType, int liveDays, int spend,
                 String eName, Date orderDate,int consume,int totalSpend) {
        this.liveInfoId = liveId;
        this.roomId = roomId;
        this.roomType = roomType;
        this.liveDays = liveDays;
        this.spend = spend;
        this.eName = eName;
        this.orderDate = orderDate;
        this.consume = consume;
        this.totalSpend = totalSpend;
    }


}
