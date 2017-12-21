package com.wang.sysm.config.routes;

import com.jfinal.config.Routes;
import com.wang.sysm.controller.TestController;

/**
 * @auther HeJiawang
 * @date 2017/12/19
 */
public class TestRoute extends Routes {

    @Override
    public void config() {
        add("/test", TestController.class);
    }
}
