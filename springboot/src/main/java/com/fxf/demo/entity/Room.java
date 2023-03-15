package com.fxf.demo.entity;

import com.baomidou.mybatisplus.annotation.IdType;
import com.baomidou.mybatisplus.annotation.TableField;
import com.baomidou.mybatisplus.annotation.TableId;
import com.baomidou.mybatisplus.annotation.TableName;
import io.swagger.models.auth.In;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
@AllArgsConstructor
@TableName(value = "1bishe.sys_room")
public class Room {
    @TableId(type = IdType.AUTO, value = "roomId")
    private Integer roomId;
    @TableField(value = "roomName")
    private String roomName;
    @TableField(value = "roomCondition")
    private Integer roomCondition;
    @TableField(value = "location")
    private String location;
    @TableField(value = "roomPrice")
    private Integer roomPrice;
    @TableField(value = "roomType")
    private Integer roomType;
    @TableField(value = "liveId")
    private Integer liveId;
}
