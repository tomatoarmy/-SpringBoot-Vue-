package com.fxf.demo.entity;

import com.baomidou.mybatisplus.annotation.IdType;
import com.baomidou.mybatisplus.annotation.TableField;
import com.baomidou.mybatisplus.annotation.TableId;
import com.baomidou.mybatisplus.annotation.TableName;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
@TableName(value = "1bishe.sys_role")
public class Role {
    @TableId(type = IdType.AUTO, value = "id")
    private Integer id;
    @TableField(value = "name")
    private String name;
    @TableField(value = "description")
    private String description;
}
