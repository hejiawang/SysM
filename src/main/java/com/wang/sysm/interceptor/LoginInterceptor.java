package com.wang.sysm.interceptor;

import com.jfinal.aop.Interceptor;
import com.jfinal.aop.Invocation;
import com.jfinal.core.Controller;
import com.wang.sysm.model.UserInfo;
import com.wang.sysm.kit.ObjectKit;
import org.apache.shiro.SecurityUtils;
import org.apache.shiro.subject.Subject;

/**
 * 登陆拦截器
 * @auther HeJiawang
 * @date 2017/12/26
 */
public class LoginInterceptor implements Interceptor {

    @Override
    public void intercept(Invocation invocation) {
        if (SecurityUtils.getSubject().isAuthenticated()) {
            invocation.invoke();
        } else {
            invocation.getController().redirect("/login");
        }
    }
}
