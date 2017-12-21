package com.wang.sysm;

import com.jfinal.core.JFinal;

/**
 * @auther HeJiawang
 * @date 2017/12/19
 */
public class AppMain {

    public static void main(String[] args) {
        JFinal.start("src/main/webapp", 8080, "/");
    }
}
