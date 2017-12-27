package com.wang.sysm.config.plugins.service;

import com.jfinal.kit.LogKit;
import com.jfinal.kit.PropKit;
import com.wang.sysm.kit.ClassKit;

import java.util.HashMap;
import java.util.Map;
import java.util.Set;

/**
 * @auther HeJiawang
 * @date 2017/12/27
 */
public class ServiceProvider {

    private static final Map<Class, Object> serviceMap = new HashMap<Class, Object>();

    public static boolean init(){
        try {
            Set<Class<?>> classSet = ClassKit.getClassSet(PropKit.get("servicePackage"));
            for( Class tclass : classSet ){
                if( tclass.isInterface() ){
                    String[] str = tclass.getName().split("\\.");
                    String implNameStr = str[str.length - 1] + "Impl";
                    implNameStr = implNameStr.substring(1, implNameStr.length());
                    serviceMap.put(tclass, Class.forName(PropKit.get("serviceImplPackage") + implNameStr).newInstance());
                }
            }
        } catch (Exception e){
            LogKit.error("ServiceProvider init Error",e);
            return false;
        }

        return true;
    }

    public static boolean clear(){
        serviceMap.clear();
        return true;
    }

    public static <T> T getService(Class<T> tClass){
        return (T) serviceMap.get(tClass);
    }

}
