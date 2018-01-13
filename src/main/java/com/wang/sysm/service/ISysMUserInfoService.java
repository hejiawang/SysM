package com.wang.sysm.service;

import com.jfinal.plugin.activerecord.Page;
import com.wang.sysm.model.UserInfo;

/**
 * @auther HeJiawang
 * @date 2018/1/8
 */
public interface ISysMUserInfoService {

    /**
     * 分页获取用户信息
     * @param pageNumber 当前页的页号
     * @param pageSize 每页数据条数
     * @param name 用户登录名获真实姓名
     * @return
     */
    Page<UserInfo> findAllByPaginate(int pageNumber, int pageSize, String name);

    /**
     * 保存用户信息
     * @param userInfo 用户信息
     * @return 是否保存成功
     */
    Boolean save(UserInfo userInfo);

    /**
     * 删除用户信息
     * @param userInfo 用户信息
     * @return 是否删除成功
     */
    Boolean delete(UserInfo userInfo);

    /**
     * 修改用户信息
     * @param userInfo 用户信息
     * @return 是否修改成功
     */
    Boolean modify(UserInfo userInfo);
}
