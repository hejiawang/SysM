package com.wang.sysm.controller;

import com.jfinal.core.Controller;
import com.wang.sysm.config.plugins.service.ServiceProvider;
import com.wang.sysm.model.CodeLog;
import com.wang.sysm.service.ISysMCodeLogService;
import com.wang.sysm.service.impl.SysMCodeLogServiceImpl;
import com.wang.sysm.utils.http.HttpControllerResult;
import com.wang.sysm.utils.http.LayUIPageResult;

import java.util.List;

/**
 * 系统开发日志
 * @auther HeJiawang
 * @date 2017/12/22
 */
public class SysMCodeLogController extends Controller {

    public static final ISysMCodeLogService service = ServiceProvider.getService(ISysMCodeLogService.class);

    public void index(){
        renderFreeMarker("codeLog.html");
    }

    public void listAll(){
        renderJson(new HttpControllerResult<List<CodeLog>>(service.listAll()));
    }

    public void list(){
        int pageNumber = getParaToInt("page", 1);
        int pageSize = getParaToInt("limit", 10);
        String content = getPara("content", "");

        renderJson(new LayUIPageResult<CodeLog>(service.findAllByPaginate(pageNumber, pageSize, content)));
    }

    public void raise(){
        CodeLog codeLog = getBean(CodeLog.class);
        renderJson(new HttpControllerResult<Boolean>(service.save(codeLog)));
    }

    public void delete(){
        CodeLog codeLog = getBean(CodeLog.class);
        renderJson(new HttpControllerResult<Boolean>(service.delete(codeLog)));
    }

    public void modify(){
        CodeLog codeLog = getBean(CodeLog.class);
        renderJson(new HttpControllerResult<Boolean>(service.modify(codeLog)));
    }
}
