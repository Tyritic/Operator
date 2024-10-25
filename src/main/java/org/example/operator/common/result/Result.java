package org.example.operator.common.result;

import lombok.Data;

@Data
public class Result<T> {
    private Integer status;//状态码 1成功 0失败
    private String msg;//描述
    private T data;//数据

    //返回成功,无数据
    public static <T> Result<T> success(String msg) {
        Result<T> result = new Result<T>();
        result.status = 1;
        result.msg = msg;
        result.data = null;
        return result;
    }

    //返回成功,有数据
    public static <T> Result<T> success(String msg,T object) {
        Result<T> result = new Result<T>();
        result.status = 1;
        result.msg = msg;
        result.data = object;
        return result;
    }

    //返回失败
    public static <T> Result<T> error(String msg) {
        Result<T> result = new Result<T>();
        result.status = 0;
        result.msg = msg;
        result.data = null;
        return result;
    }

}
