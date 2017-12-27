package com.wang.sysm.config;

import com.jfinal.config.*;
import com.jfinal.json.MixedJsonFactory;
import com.jfinal.kit.LogKit;
import com.jfinal.kit.PathKit;
import com.jfinal.kit.PropKit;
import com.jfinal.log.Log;
import com.jfinal.log.Log4jLogFactory;
import com.jfinal.log.LogManager;
import com.jfinal.plugin.activerecord.ActiveRecordPlugin;
import com.jfinal.plugin.druid.DruidPlugin;
import com.jfinal.render.FreeMarkerRender;
import com.jfinal.render.ViewType;
import com.jfinal.template.Engine;
import com.wang.sysm.controller.IndexController;
import com.wang.sysm.controller.SysMCodeLogController;
import com.wang.sysm.controller.TestController;
import com.wang.sysm.interceptor.LoginInterceptor;
import com.wang.sysm.model._MappingKit;
import freemarker.template.Configuration;
import freemarker.template.TemplateModelException;

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
        LogManager.me().setDefaultLogFactory(new Log4jLogFactory());

        constants.setDevMode(PropKit.getBoolean("devMode", false));
        constants.setViewType(ViewType.FREE_MARKER);
        this.setFMSharedVariable();

        constants.setJsonFactory(new MixedJsonFactory());
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
        arp.setShowSql(PropKit.getBoolean("devMode", false));
        _MappingKit.mapping(arp);
        plugins.add(arp);
    }

    /**
     * 配置全局拦截器
     * @param interceptors
     */
    @Override
    public void configInterceptor(Interceptors interceptors) {
        //interceptors.addGlobalActionInterceptor(new TestInterceptor());
        interceptors.addGlobalActionInterceptor(new LoginInterceptor());
    }

    /**
     * 配置Handler处理器
     * @param handlers
     */
    @Override
    public void configHandler(Handlers handlers) {

    }

    private void setFMSharedVariable() {
        try {
            Configuration configuration = FreeMarkerRender.getConfiguration();
            configuration.setSharedVariable("webUrl", PropKit.get("webUrl"));
            configuration.setSharedVariable("libUrl", PropKit.get("webUrl") + PropKit.get("libUrl"));
            configuration.setSharedVariable("jsUrl", PropKit.get("webUrl") + PropKit.get("jsUrl"));
            configuration.setSharedVariable("cssUrl", PropKit.get("webUrl") + PropKit.get("cssUrl"));
            configuration.setSharedVariable("imgUrl", PropKit.get("webUrl") + PropKit.get("imgUrl"));
        } catch (TemplateModelException e) {
            LogKit.error("FreeMarker SharedVariable Error", e);
        }
    }
}
