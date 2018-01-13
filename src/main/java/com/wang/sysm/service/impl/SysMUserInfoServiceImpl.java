package com.wang.sysm.service.impl;

import com.jfinal.plugin.activerecord.Page;
import com.wang.sysm.kit.UUIDKit;
import com.wang.sysm.model.UserInfo;
import com.wang.sysm.service.ISysMUserInfoService;
import com.wang.sysm.shiro.helper.PasswordHelper;

import java.util.Date;

/**
 * @auther HeJiawang
 * @date 2018/1/8
 */
public class SysMUserInfoServiceImpl implements ISysMUserInfoService {

    private static final UserInfo dao = new UserInfo().dao();

    @Override
    public Page<UserInfo> findAllByPaginate(int pageNumber, int pageSize, String name) {
        return dao.paginate(pageNumber, pageSize, name);
    }

    @Override
    public Boolean save(UserInfo userInfo) {
        PasswordHelper.encryptPassword(userInfo);
        userInfo.setId(UUIDKit.render());
        userInfo.setCreateTime( new Date()).setUpdateTime(new Date());
        return userInfo.save();
    }
}
