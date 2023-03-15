package com.fxf.demo.service;

import com.baomidou.mybatisplus.extension.service.IService;
import com.fxf.demo.controller.dto.UserDTO;
import com.fxf.demo.entity.User;

public interface IUserService extends IService<User> {
    UserDTO login(UserDTO userDTO);

    User register(UserDTO userDTO);
}
