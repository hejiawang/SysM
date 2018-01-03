package com.wang.sysm.controller;

import com.jfinal.aop.Clear;
import com.jfinal.core.Controller;
import com.jfinal.kit.HttpKit;
import com.jfinal.kit.JsonKit;
import com.jfinal.kit.LogKit;
import com.jfinal.kit.PropKit;
import com.wang.sysm.model.UserInfo;
import com.wang.sysm.kit.http.HttpControllerResult;
import org.apache.shiro.SecurityUtils;
import org.apache.shiro.authc.*;
import org.apache.shiro.subject.Subject;

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

    public void logout(){
        removeSessionAttr("userInfo");

        SecurityUtils.getSubject().logout();
        redirect("/login");
    }

    @Clear
    public void login(){
        renderFreeMarker("login.html");
    }

    @Clear
    public void loginDo(){
        String readData = HttpKit.readData(getRequest());
        UserInfo userInfo = JsonKit.parse(readData, UserInfo.class);

        HttpControllerResult<Boolean> result = new HttpControllerResult<Boolean>();

        Subject subject = SecurityUtils.getSubject();
        UsernamePasswordToken token = new UsernamePasswordToken(userInfo.getUserName(), userInfo.getPassWord());
        try {
            subject.login(token);

            getSession().setAttribute("userInfo", userInfo);
            result.setResult(true);
        } catch (ExcessiveAttemptsException e) {
            result.setResult(false).setMessage("登陆错误次数超过" + PropKit.get("ehcache.password.count") + "次！请稍后登陆" );//错误次数过多
        } catch ( UnknownAccountException e){
            result.setResult(false).setMessage("用户名或密码错误");//用户名错误
        } catch ( IncorrectCredentialsException e){
            result.setResult(false).setMessage("用户名或密码错误");//密码错误
        }

        renderJson(result);
    }
}
