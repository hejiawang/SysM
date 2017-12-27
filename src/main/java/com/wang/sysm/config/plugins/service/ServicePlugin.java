package com.wang.sysm.config.plugins.service;

import com.jfinal.plugin.IPlugin;

/**
 * @auther HeJiawang
 * @date 2017/12/27
 */
public class ServicePlugin implements IPlugin {

    @Override
    public boolean start() {
        return ServiceProvider.init();
    }

    @Override
    public boolean stop() {
        return ServiceProvider.clear();
    }
}
