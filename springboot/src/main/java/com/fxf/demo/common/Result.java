package com.fxf.demo.common;


import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;


/*
* 接口统一返回的包装类
* */
@Data
@AllArgsConstructor
@NoArgsConstructor
public class Result {



    private String code;
    private String msg;
    private Object data;



    //请求成功
    public static Result success(){
        return new Result(Constants.CODE_200, "", null);
    }

    //请求成功并返回数据
    public static Result success(Object data){
        return new Result(Constants.CODE_200,"",data);
    }

    //请求失败
    public static  Result error(String code, String msg){
        return new Result(code,msg,null);
    }

    //请求失败,服务器内部错误
    public static Result error(){
        return new Result(Constants.CODE_500,"系统错误",null);
    }
}
