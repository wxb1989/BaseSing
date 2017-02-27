package com.cx.sin.bean.privilege;

import com.cx.sin.bean.base.BaseForm;

public class SystemPrivilegeForm extends BaseForm{
	/**
	 * 
	 */
	private static final long serialVersionUID = 2441874883344058561L;
	private String name;
	private String type;
	private Boolean visible = false;
	private Integer indexNumber;
	
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
	public Boolean getVisible() {
		return visible;
	}
	public void setVisible(Boolean visible) {
		this.visible = visible;
	}
	public Integer getIndexNumber() {
		return indexNumber;
	}
	public void setIndexNumber(Integer indexNumber) {
		this.indexNumber = indexNumber;
	}
}
