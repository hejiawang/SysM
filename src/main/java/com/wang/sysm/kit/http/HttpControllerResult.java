package com.wang.sysm.kit.http;

/**
 * controler控制层统一返回结果对象类
 *
 * @auther HeJiawang
 * @date 2017/12/22
 */
public class HttpControllerResult<T> {

    private T result;

    private int code;

    private String message;

    public HttpControllerResult() {}

    public HttpControllerResult(T t) {
        this.code = 0;
        this.result = t;
    }

    public HttpControllerResult<T> error (int code, String message) {
        this.code = code;
        this.message = message;
        return this;
    }

    public T getResult() {
        return result;
    }

    public HttpControllerResult<T> setResult(T result) {
        this.result = result;
        return this;
    }

    public int getCode() {
        return code;
    }

    public HttpControllerResult<T> setCode(int code) {
        this.code = code;
        return this;
    }

    public String getMessage() {
        return message;
    }

    public HttpControllerResult<T> setMessage(String message) {
        this.message = message;
        return this;
    }

}
