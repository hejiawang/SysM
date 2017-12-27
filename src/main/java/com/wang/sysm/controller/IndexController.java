package com.wang.sysm.controller;

import com.jfinal.aop.Clear;
import com.jfinal.core.Controller;
import com.jfinal.kit.HttpKit;
import com.jfinal.kit.JsonKit;
import com.jfinal.kit.LogKit;
import com.jfinal.render.FreeMarkerRender;
import com.wang.sysm.model.UserInfo;
import com.wang.sysm.utils.http.HttpControllerResult;
import freemarker.template.TemplateModelException;

/**
 * @auther HeJiawang
 * @date 2017/12/20
 */
public class IndexController extends Controller {

    public void index(){
        renderFreeMarker("index.html");
    }

    public void main(){
        renderFreeMarker("main/main.html");
    }

    @Clear
    public void login(){
        renderFreeMarker("login.html");
    }

    @Clear
    public void loginDo(){
        String readData = HttpKit.readData(getRequest());
        UserInfo userInfo = JsonKit.parse(readData, UserInfo.class);
        LogKit.info("用户" + userInfo.getUserName() + "登陆");

        getSession().setAttribute("userInfo", userInfo);

        renderJson(new HttpControllerResult<Boolean>(true));
    }
}
