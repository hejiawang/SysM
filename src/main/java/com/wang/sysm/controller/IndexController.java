package com.wang.sysm.controller;

import com.jfinal.core.Controller;
import com.jfinal.render.FreeMarkerRender;
import freemarker.template.TemplateModelException;

/**
 * @auther HeJiawang
 * @date 2017/12/20
 */
public class IndexController extends Controller {

    public void index(){
        renderFreeMarker("index.html");
    }
}
