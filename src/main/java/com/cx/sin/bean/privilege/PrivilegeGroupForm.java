package com.cx.sin.bean.privilege;

import com.cx.sin.bean.base.BaseForm;

public class PrivilegeGroupForm extends BaseForm{
	/**
	 * 
	 */
	private static final long serialVersionUID = -4542742312973105716L;
	private String groupid;
	private String name;
	private String type;
	private Integer accountid;
	
	private String[] privileges;
	public String getGroupid() {
		return groupid;
	}
	public void setGroupid(String groupid) {
		this.groupid = groupid;
	}
	public String getName() {
		return name;
	}
	public void setName(String name) {
		this.name = name;
	}
	public String getType() {
		return type;
	}
	public void setType(String type) {
		this.type = type;
	}
	public Integer getAccountid() {
		return accountid;
	}
	public void setAccountid(Integer accountid) {
		this.accountid = accountid;
	}
	public String[] getPrivileges() {
		return privileges;
	}
	public void setPrivileges(String[] privileges) {
		this.privileges = privileges;
	}
}
