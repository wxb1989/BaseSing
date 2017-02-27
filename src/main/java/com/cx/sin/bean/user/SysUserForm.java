package com.cx.sin.bean.user;

import com.cx.sin.bean.base.BaseForm;

public class SysUserForm extends BaseForm{
	/**
	 * 
	 */
	private static final long serialVersionUID = -8869164628622251162L;
	/** 用户名 **/
	private String username;
	/** 密码 **/
	private String password;
	/** 真实姓名 **/
	private String realname;
	/** 所属权限组 **/
	private String[] groupids;
	
	public String getUsername() {
		return username;
	}
	public void setUsername(String username) {
		this.username = username;
	}
	public String getPassword() {
		return password;
	}
	public void setPassword(String password) {
		this.password = password;
	}
	public String getRealname() {
		return realname;
	}
	public void setRealname(String realname) {
		this.realname = realname;
	}
	public String[] getGroupids() {
		return groupids;
	}
	public void setGroupids(String[] groupids) {
		this.groupids = groupids;
	}
}
