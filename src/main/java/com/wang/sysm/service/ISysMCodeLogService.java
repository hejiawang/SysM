package com.wang.sysm.service;

import com.jfinal.plugin.activerecord.Page;
import com.wang.sysm.model.CodeLog;

import java.util.List;

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

    /**
     * 删除
     * @param codeLog codeLog
     * @return true
     */
    Boolean delete(CodeLog codeLog);

    /**
     * 修改
     * @param codeLog codeLog
     * @return true
     */
    Boolean modify(CodeLog codeLog);

    /**
     * 获取所有日志记录
     * @return CodeLog
     */
    List<CodeLog> listAll();
}
