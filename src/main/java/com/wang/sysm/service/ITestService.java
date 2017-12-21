package com.wang.sysm.service;


import com.wang.sysm.model.Test;

import java.util.List;

/**
 * @auther HeJiawang
 * @date 2017/12/19
 */
public interface ITestService {

    void save(Test test);

    void deleteById(String id);

    void modifyById(Test test);

    Test findById(String id);

    List<Test> findAll();

}
