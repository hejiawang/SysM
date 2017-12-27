package com.wang.sysm.kit;

/**
 * @auther HeJiawang
 * @date 2017/12/27
 */
public class ObjectKit {

    public static boolean isBlank( Object o ){
        if( null == o ) return true;
        return false;
    }

    public static boolean isNotBlank( Object o ){
        return !isBlank(o);
    }
}
