package com.wang.sysm.model.base;

import com.jfinal.plugin.activerecord.Model;
import com.jfinal.plugin.activerecord.IBean;

/**
 * Generated by JFinal, do not modify this file.
 */
@SuppressWarnings({"serial", "unchecked"})
public abstract class BaseUserInfo<M extends BaseUserInfo<M>> extends Model<M> implements IBean {

	public M setId(java.lang.String id) {
		set("id", id);
		return (M)this;
	}
	
	public java.lang.String getId() {
		return getStr("id");
	}

	public M setUserName(java.lang.String userName) {
		set("userName", userName);
		return (M)this;
	}
	
	public java.lang.String getUserName() {
		return getStr("userName");
	}

	public M setPassWord(java.lang.String passWord) {
		set("passWord", passWord);
		return (M)this;
	}
	
	public java.lang.String getPassWord() {
		return getStr("passWord");
	}

	public M setSalt(java.lang.String salt) {
		set("salt", salt);
		return (M)this;
	}
	
	public java.lang.String getSalt() {
		return getStr("salt");
	}

	public M setRealName(java.lang.String realName) {
		set("realName", realName);
		return (M)this;
	}
	
	public java.lang.String getRealName() {
		return getStr("realName");
	}

	public M setEmail(java.lang.String email) {
		set("email", email);
		return (M)this;
	}
	
	public java.lang.String getEmail() {
		return getStr("email");
	}

	public M setTelephone(java.lang.String telephone) {
		set("telephone", telephone);
		return (M)this;
	}
	
	public java.lang.String getTelephone() {
		return getStr("telephone");
	}

	public M setBirtoday(java.util.Date birtoday) {
		set("birtoday", birtoday);
		return (M)this;
	}
	
	public java.util.Date getBirtoday() {
		return get("birtoday");
	}

	public M setEducational(java.lang.Integer educational) {
		set("educational", educational);
		return (M)this;
	}
	
	public java.lang.Integer getEducational() {
		return getInt("educational");
	}

	public M setRemark(java.lang.String remark) {
		set("remark", remark);
		return (M)this;
	}
	
	public java.lang.String getRemark() {
		return getStr("remark");
	}

	public M setCreateTime(java.util.Date createTime) {
		set("createTime", createTime);
		return (M)this;
	}
	
	public java.util.Date getCreateTime() {
		return get("createTime");
	}

	public M setUpdateTime(java.util.Date updateTime) {
		set("updateTime", updateTime);
		return (M)this;
	}
	
	public java.util.Date getUpdateTime() {
		return get("updateTime");
	}

}
