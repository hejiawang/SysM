package com.wang.sysm.config;

import com.wang.sysm.model.Test;
import com.wang.sysm.service.ITestService;
import com.wang.sysm.service.impl.TestServiceImpl;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 * @auther HeJiawang
 * @date 2017/12/27
 */
public class ServiceConfig {

    private static final Map<Class, Object> map = new HashMap<Class, Object>();

    public static void init(){
        map.put(ITestService.class, new TestServiceImpl());
    }

    public static <T> T test(Class<T> tClass){
        return (T)map.get(ITestService.class);
    }


    public static void main(String[] args) {
        ServiceConfig.init();

        TestServiceImpl testService = ServiceConfig.test(TestServiceImpl.class);
        System.out.println(testService.test());
    }
}
