package com.wang.sysm.service.impl;

import com.jfinal.kit.StrKit;
import com.jfinal.plugin.activerecord.Page;
import com.wang.sysm.model.CodeLog;
import com.wang.sysm.service.ISysMCodeLogService;

/**
 * @auther HeJiawang
 * @date 2017/12/22
 */
public class SysMCodeLogServiceImpl implements ISysMCodeLogService {

    private static final CodeLog dao = new CodeLog().dao();

    @Override
    public Page<CodeLog> findAllByPaginate(int pageNumber, int pageSize, String content) {
        String sqlFrom = "from sysm_code_log ";
        if(StrKit.notBlank(content)){
            sqlFrom += "where content like '%" + content + "%' ";
        }
        sqlFrom += "ORDER BY date DESC";

        return dao.paginate(pageNumber, pageSize, "select *", sqlFrom);
    }
}
