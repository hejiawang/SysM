package com.wang.sysm.service.impl;


import com.wang.sysm.model.Test;
import com.wang.sysm.service.ITestService;

import java.util.List;

/**
 * @auther HeJiawang
 * @date 2017/12/19
 */
public class TestServiceImpl implements ITestService {

    private static final Test testDao = new Test().dao();

    @Override
    public void save(Test test) {
        //new Test().set("id", "1").set("name", "1").set("age","1").save();

        //Record test = new Record().set("id", "2").set("name", "2").set("age","2");
        //Db.save("test", test);

        test.save();
    }

    @Override
    public void deleteById(String id) {
        testDao.deleteById(id);
    }

    @Override
    public void modifyById(Test test) {
        test.update();
    }

    @Override
    public Test findById(String id) {
        return testDao.findById(id);
    }

    @Override
    public List<Test> findAll() {
        return testDao.find("select * from test");
    }

    @Override
    public String test() {
        return "asfasdasdfasdf";
    }
}
