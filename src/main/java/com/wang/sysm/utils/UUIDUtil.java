package com.wang.sysm.utils;

import java.util.UUID;

/**
 * @Author HeJiawang
 * @Date 2017/12/23 21:17
 */
public class UUIDUtil {

    public static String build(){
        return UUID.randomUUID().toString().replace("-", "");
    }
}
