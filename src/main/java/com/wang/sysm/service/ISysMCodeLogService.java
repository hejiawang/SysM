package com.wang.sysm.service;

import com.jfinal.plugin.activerecord.Page;
import com.wang.sysm.model.CodeLog;

/**
 * @auther HeJiawang
 * @date 2017/12/22
 */
public interface ISysMCodeLogService {

    /**
     * 分页获取数据
     * @param pageNumber 当前页的页号
     * @param pageSize 每页数据条数
     * @param content 查询内容
     * @return codelog list
     */
    Page<CodeLog> findAllByPaginate(int pageNumber, int pageSize, String content);

    /**
     * 保存CodeLog
     * @param codeLog codeLog
     * @return true
     */
    Boolean save(CodeLog codeLog);
}
