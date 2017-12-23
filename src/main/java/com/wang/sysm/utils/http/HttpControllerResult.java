package com.wang.sysm.utils.http;

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

    public void error (int code, String message) {
        this.code = code;
        this.message = message;
    }

    public T getResult() {
        return result;
    }

    public void setResult(T result) {
        this.result = result;
    }

    public int getCode() {
        return code;
    }

    public void setCode(int code) {
        this.code = code;
    }

    public String getMessage() {
        return message;
    }

    public void setMessage(String message) {
        this.message = message;
    }

}
