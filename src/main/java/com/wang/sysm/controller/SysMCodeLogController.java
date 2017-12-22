package com.wang.sysm.controller;

import com.jfinal.core.Controller;
import com.wang.sysm.model.CodeLog;
import com.wang.sysm.service.ISysMCodeLogService;
import com.wang.sysm.service.impl.SysMCodeLogServiceImpl;
import com.wang.sysm.utils.http.LayUIPageResult;

/**
 * 系统开发日志
 * @auther HeJiawang
 * @date 2017/12/22
 */
public class SysMCodeLogController extends Controller {

    public static final ISysMCodeLogService service = new SysMCodeLogServiceImpl();

    public void index(){
        render("codeLog.jsp");
    }

    public void list(){
        int pageNumber = getParaToInt("page");
        int pageSize = getParaToInt("limit");
        String content = getPara("content", "");

        renderJson(new LayUIPageResult<CodeLog>(service.findAllByPaginate(pageNumber, pageSize, content)));
    }

    public void raisePage(){
        render("codeLogRaise.jsp");
    }
}
