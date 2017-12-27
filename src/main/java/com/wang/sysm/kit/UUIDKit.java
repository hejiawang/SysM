package com.wang.sysm.kit;

import java.util.UUID;

/**
 * @Author HeJiawang
 * @Date 2017/12/23 21:17
 */
public class UUIDKit {

    public static String render(){
        return UUID.randomUUID().toString().replace("-", "");
    }
}
