package com.fxf.demo.exception;

/*
* 自定义异常
* */

import lombok.Data;

@Data
public class ServiceException extends RuntimeException{
    private String code;

    public ServiceException(String code, String msg){
        super(msg);
        this.code = code;
    }
}
