package com.wang.sysm.utils.http;

import com.jfinal.plugin.activerecord.Page;

import java.io.Serializable;
import java.util.List;

/**
 * LayUI 分页结果
 *
 * @auther HeJiawang
 * @date 2017/12/22
 */
public class LayUIPageResult<T> implements Serializable {

    private static final long serialVersionUID = -1L;

    private int code = 0;
    private String msg = "";
    private int count;  //总条数
    private List<T> data;

    public LayUIPageResult(Page<T> page){
        this.count = page.getTotalRow();
        this.data = page.getList();
    }

    public LayUIPageResult( List<T> data, int count ){
        this.data = data;
        this.count = count;
    }

    public LayUIPageResult( List<T> data, int count, int code, String msg ){
        this.data = data;
        this.count = count;
        this.code = code;
        this.msg = msg;
    }

    public int getCode() {
        return code;
    }

    public LayUIPageResult setCode(int code) {
        this.code = code;
        return this;
    }

    public String getMsg() {
        return msg;
    }

    public LayUIPageResult setMsg(String msg) {
        this.msg = msg;
        return this;
    }

    public int getCount() {
        return count;
    }

    public LayUIPageResult setCount(int count) {
        this.count = count;
        return this;
    }

    public List<T> getData() {
        return data;
    }

    public LayUIPageResult setData(List<T> data) {
        this.data = data;
        return this;
    }
}
