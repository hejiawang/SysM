package com.wang.sysm.interceptor;

import com.jfinal.aop.Interceptor;
import com.jfinal.aop.Invocation;

/**
 * @auther HeJiawang
 * @date 2017/12/15
 */
public class TestInterceptor implements Interceptor {

    @Override
    public void intercept(Invocation invocation) {
        System.out.println("Before method invoking");
        invocation.invoke();
        System.out.println("After method invoking");
    }
}
