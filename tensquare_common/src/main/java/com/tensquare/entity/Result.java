package com.tensquare.entity;

import lombok.Data;

/**
 * 2 * @Author: Cai
 * 3 * @Date: 2020/7/21 10:58
 * 4
 */
@Data
public class Result<T> {
    private boolean flag;//是否成功
    private Integer code;// 返回码
    private String message;//返回信息
    private T data;// 返回数据

    public Result(boolean flag, Integer code, String message, T data) {
            super();
            this.flag = flag;
            this.code = code;
            this.message = message;
            this.data = data;
    }
    public Result(boolean flag, Integer code, String message) {

        super();
        this.flag = flag;
        this.code = code;
        this.message = message;
    }
    public Result() {}

    public static<T> Result<T> ok(T data){
        return new Result<T>(true,StatusCode.OK,"操作成功",data);
    }
    public static<T> Result<T> ok(String message){
        return new Result<T>(true,StatusCode.OK,message,null);
    }
    public static<T> Result<T> ok(String message,T data){
        return new Result<T>(true,StatusCode.OK,message,data);
    }
    public static<T> Result<T> fail(String message){
        return new Result<T>(false,StatusCode.ERROR,message);
    }

}