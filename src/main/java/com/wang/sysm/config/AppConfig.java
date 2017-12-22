package com.wang.sysm.config;

import com.jfinal.config.*;
import com.jfinal.kit.PathKit;
import com.jfinal.kit.PropKit;
import com.jfinal.plugin.activerecord.ActiveRecordPlugin;
import com.jfinal.plugin.druid.DruidPlugin;
import com.jfinal.template.Engine;
import com.wang.sysm.controller.IndexController;
import com.wang.sysm.controller.MainController;
import com.wang.sysm.controller.SysMCodeLogController;
import com.wang.sysm.controller.TestController;
import com.wang.sysm.interceptor.TestInterceptor;
import com.wang.sysm.model._MappingKit;

/**
 * @auther HeJiawang
 * @date 2017/12/19
 */
public class AppConfig extends JFinalConfig {

    /**
     * 配置常量值
     * @param constants
     */
    @Override
    public void configConstant(Constants constants) {
        PropKit.use("common_config.properties");

        constants.setDevMode(PropKit.getBoolean("devMode", false));
        //constants.setViewType(ViewType.FREE_MARKER);
    }

    /**
     * 配置JFinal访问路由
     * @param routes
     */
    @Override
    public void configRoute(Routes routes) {
        routes.setBaseViewPath("/view");

        routes.add("/", IndexController.class);
        routes.add("/test", TestController.class, "/test");
        routes.add("/main", MainController.class, "/main");
        routes.add("/codeLog", SysMCodeLogController.class, "/codeLog");
    }

    /**
     * 配置Template Engine
     * @param engine
     */
    @Override
    public void configEngine(Engine engine) {

    }

    /**
     * 配置Plugins
     * @param plugins
     */
    @Override
    public void configPlugin(Plugins plugins) {
        DruidPlugin druidPlugin = new DruidPlugin(PropKit.get("jdbcUrl"), PropKit.get("user"), PropKit.get("password").trim());
        plugins.add(druidPlugin);

        ActiveRecordPlugin arp = new ActiveRecordPlugin(druidPlugin);
        arp.setBaseSqlTemplatePath(PathKit.getRootClassPath());
        arp.addSqlTemplate("test.sql");
        _MappingKit.mapping(arp);
        plugins.add(arp);
    }

    /**
     * 配置全局拦截器
     * @param interceptors
     */
    @Override
    public void configInterceptor(Interceptors interceptors) {
        interceptors.addGlobalActionInterceptor(new TestInterceptor());
    }

    /**
     * 配置Handler处理器
     * @param handlers
     */
    @Override
    public void configHandler(Handlers handlers) {

    }
}
