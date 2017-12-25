package com.wang.sysm.service.impl;

import com.jfinal.kit.StrKit;
import com.jfinal.plugin.activerecord.Page;
import com.wang.sysm.model.CodeLog;
import com.wang.sysm.service.ISysMCodeLogService;
import com.wang.sysm.utils.UUIDUtil;

import java.util.List;

/**
 * @auther HeJiawang
 * @date 2017/12/22
 */
public class SysMCodeLogServiceImpl implements ISysMCodeLogService {

    private static final CodeLog dao = new CodeLog().dao();

    @Override
    public Page<CodeLog> findAllByPaginate(int pageNumber, int pageSize, String content) {
        return dao.paginate(pageNumber, pageSize, content);
    }

    @Override
    public Boolean save(CodeLog codeLog) {
        codeLog.setId(UUIDUtil.render());
        return codeLog.save();
    }

    @Override
    public Boolean delete(CodeLog codeLog) {
        return codeLog.delete();
    }

    @Override
    public Boolean modify(CodeLog codeLog) {
        return codeLog.update();
    }

    @Override
    public List<CodeLog> listAll() {
        return dao.findAll();
    }
}
