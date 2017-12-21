package com.wang.sysm.config.routes;

import com.jfinal.config.Routes;
import com.wang.sysm.controller.IndexController;

/**
 * @auther HeJiawang
 * @date 2017/12/19
 */
public class IndexRoute extends Routes {

    @Override
    public void config() {
        add("/", IndexController.class);
    }
}
