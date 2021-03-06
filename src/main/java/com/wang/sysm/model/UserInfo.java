package com.wang.sysm.model;

import com.jfinal.kit.Kv;
import com.jfinal.kit.StrKit;
import com.jfinal.plugin.activerecord.Page;
import com.jfinal.plugin.activerecord.SqlPara;
import com.wang.sysm.model.base.BaseUserInfo;

/**
 * Generated by JFinal.
 */
@SuppressWarnings("serial")
public class UserInfo extends BaseUserInfo<UserInfo> {
	public static final UserInfo dao = new UserInfo().dao();

	public Page<UserInfo> paginate( int pageNumber, int pageSize, String name ){
		Kv contentKv = StrKit.notBlank(name) ? Kv.by("userName like ", name).set("realName like ", name) : Kv.by("1 like ", "1");
		SqlPara sqlPara = getSqlPara("userInfo.paginate", Kv.by("content", contentKv));
		return dao.paginate(pageNumber, pageSize, "select *",  sqlPara.getSql(), sqlPara.getPara());
	}
}
