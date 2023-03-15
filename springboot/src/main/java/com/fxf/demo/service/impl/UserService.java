package com.fxf.demo.service.impl;


import cn.hutool.core.bean.BeanUtil;
import cn.hutool.log.Log;
import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.fxf.demo.common.Constants;
import com.fxf.demo.controller.dto.UserDTO;
import com.fxf.demo.entity.User;
import com.fxf.demo.exception.ServiceException;
import com.fxf.demo.mapper.UserMapper;
import com.fxf.demo.service.IUserService;
import com.fxf.demo.utils.TokenUtils;
import org.apache.tomcat.util.bcel.Const;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class UserService extends ServiceImpl<UserMapper, User> implements IUserService {

    private static final Log LOG = Log.get();

    public boolean saveUser(User user){
        /*if (user.getId() == null){
            return save(user);
        }else {
            return updateById(user);
        }*/
        return saveOrUpdate(user);
    }


    public UserDTO login(UserDTO userDTO) {
        User one = getUserInfo(userDTO);
        if (one != null){
            BeanUtil.copyProperties(one,userDTO,true);
            //设置Token
            String token = TokenUtils.genToken(one.getId().toString(), one.getPassword());
            userDTO.setToken(token);
            return userDTO;
        }else {
            throw new ServiceException(Constants.CODE_600,"用户名或密码错误");
        }
    }



    public User register(UserDTO userDTO) {
        User one = getUserInfo(userDTO);
        if(one == null){
            one = new User();
            BeanUtil.copyProperties(userDTO,one,true);
            save(one);
        }else{
            throw new ServiceException(Constants.CODE_600, "用户已存在");
        }
        return one;
    }

    private User getUserInfo(UserDTO userDTO){
        QueryWrapper<User> queryWrapper = new QueryWrapper<>();
        queryWrapper.eq("username",userDTO.getUsername());
        queryWrapper.eq("password",userDTO.getPassword());
        User one;
        try{
            one = getOne(queryWrapper);
        }catch (Exception e){
            LOG.error(e);
            throw new ServiceException(Constants.CODE_500,"系统错误");
        }
        return one;
    }



   /* public int save(User user){
        if (user.getId() == null){//如果user没有id则视为新增
            return userMapper.insert(user);
        }else{//如果有id视为更新
            return userMapper.update(user);
        }

    }*/

}
