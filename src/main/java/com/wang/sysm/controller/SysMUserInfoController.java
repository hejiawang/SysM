package com.wang.sysm.controller;

import com.jfinal.core.Controller;
import com.jfinal.kit.HttpKit;
import com.jfinal.kit.JsonKit;
import com.wang.sysm.config.plugins.service.ServiceProvider;
import com.wang.sysm.kit.http.HttpControllerResult;
import com.wang.sysm.kit.http.LayUIPageResult;
import com.wang.sysm.model.UserInfo;
import com.wang.sysm.service.ISysMUserInfoService;

/**
 * @auther HeJiawang
 * @date 2018/1/8
 */
public class SysMUserInfoController extends Controller {

    private static final ISysMUserInfoService service = ServiceProvider.getService(ISysMUserInfoService.class);

    public void index(){
        renderFreeMarker("userInfo.html");
    }

    public void list(){
        int pageNumber = getParaToInt("page", 1);
        int pageSize = getParaToInt("limit", 10);
        String name = getPara("name", "");

        renderJson(new LayUIPageResult<UserInfo>(service.findAllByPaginate(pageNumber, pageSize, name)));
    }

    public void raise(){
        String readData = HttpKit.readData(getRequest());
        UserInfo userInfo = JsonKit.parse(readData, UserInfo.class);

        renderJson(new HttpControllerResult<Boolean>(service.save(userInfo)));
    }
}
