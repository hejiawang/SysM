package com.wang.sysm.controller;

import com.jfinal.aop.Clear;
import com.jfinal.core.ActionKey;
import com.jfinal.core.Controller;
import com.jfinal.kit.PropKit;
import com.wang.sysm.model.Test;
import com.wang.sysm.service.ITestService;
import com.wang.sysm.service.impl.TestServiceImpl;

import java.util.List;
import java.util.UUID;

/**
 * @auther HeJiawang
 * @date 2017/12/15
 */
public class TestController extends Controller {

    private static final ITestService testService = new TestServiceImpl();

    @Clear
    public void index(){
        System.out.println(PropKit.get("jdbcUrl"));
        System.out.println(PropKit.get("devMode"));
        renderText(" test jfinal ");
    }

    public void pageIndex(){
        renderFreeMarker("index.html");
    }

    public void pageIndex2(){
        renderFreeMarker("index2.html");
    }

    @ActionKey("actionTest")
    public void actionKeyTest(){
        System.out.println("Action Key Test");
        redirect("pageIndex");
    }

    public void save(){
        Test test = new Test().setId(UUID.randomUUID().toString()).setAge(20).setName("test" + UUID.randomUUID().toString());
        testService.save(test);
        redirect("pageIndex");
    }


    public void deleteById(){
        testService.deleteById("1");
        redirect("pageIndex");
    }

    public void modifyById(){
        Test test = new Test().setId("2").setAge(20).setName("test" + UUID.randomUUID().toString());
        testService.modifyById(test);
        redirect("pageIndex");
    }

    public void findById(){
        Test test = testService.findById("3");
        redirect("pageIndex");
    }

    public void findAll(){
        List<Test> testList = testService.findAll();
        redirect("pageIndex");
    }

}
