package com.fxf.demo.config.Interceptor;

import cn.hutool.core.util.StrUtil;
import com.auth0.jwt.JWT;
import com.auth0.jwt.JWTVerifier;
import com.auth0.jwt.algorithms.Algorithm;
import com.auth0.jwt.exceptions.JWTDecodeException;
import com.auth0.jwt.exceptions.JWTVerificationException;
import com.fxf.demo.common.Constants;
import com.fxf.demo.entity.User;
import com.fxf.demo.exception.ServiceException;
import com.fxf.demo.service.impl.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.method.HandlerMethod;
import org.springframework.web.servlet.HandlerInterceptor;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

public class JWTInterceptor implements HandlerInterceptor {

    @Autowired
    private UserService userService;

    @Override
    public boolean preHandle(HttpServletRequest request, HttpServletResponse response, Object handler) throws Exception {
        String token = request.getHeader("token");
        //如果不是映射到方法直接通过
        if (!(handler instanceof HandlerMethod)){
            return true;
        }
        //执行认证
        if (StrUtil.isBlank(token)){
            throw new ServiceException(Constants.CODE_401, "无token,请重新登录");
        }
        //获取token中的userID
        String userId;
        try{
            userId = JWT.decode(token).getAudience().get(0);
        }catch (JWTDecodeException e){
            throw new ServiceException(Constants.CODE_401,"token验证失败");
        }
        //根据token中携带的userId查询数据库,如果查不出用户则说明用户不存在
        User user = userService.getById(userId);
        if (user == null){
            throw new ServiceException(Constants.CODE_401,"用户不存在,请重新登录");
        }
        //用户密码加签验证 token
        JWTVerifier jwtVerifier = JWT.require(Algorithm.HMAC256(user.getPassword())).build();
        try{
            jwtVerifier.verify(token);//验证token
        }catch (JWTVerificationException e){
            throw new ServiceException(Constants.CODE_401, "token验证失败,请重新登录");
        }

        //如果前面的验证全部通过则放行
        return true;
    }
}
