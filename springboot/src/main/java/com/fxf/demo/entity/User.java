package com.fxf.demo.entity;

import com.baomidou.mybatisplus.annotation.IdType;
import com.baomidou.mybatisplus.annotation.TableField;
import com.baomidou.mybatisplus.annotation.TableId;
import com.baomidou.mybatisplus.annotation.TableName;
import com.fasterxml.jackson.annotation.JsonIgnore;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.springframework.web.servlet.view.InternalResourceViewResolver;

@Data
@AllArgsConstructor
@NoArgsConstructor
@TableName(value = "1bishe.sys_user")
public class User {
    @TableId(type = IdType.AUTO)
    private Integer id;
    private String username;
    @JsonIgnore//忽略某个字段，不展示给前端
    private String password;
    @TableField(value = "nickname")//用于注解字段在表中对应的名称
    private String nickname;
    private String email;
    private String phone;
    private String address;


}
