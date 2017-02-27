package com.cx.sin.bean.locate;

import javax.persistence.Entity;
import javax.persistence.Table;

import com.cx.sin.bean.base.BaseModel;

/**
 * 省
 * @author XuXu
 *
 */
@Entity
@Table(name = "t_province")
public class Province extends BaseModel{
	
	private static final long serialVersionUID = -6816461906120621656L;

	private String uuid;
	
	private String name;

	public String getUuid() {
		return uuid;
	}

	public void setUuid(String uuid) {
		this.uuid = uuid;
	}

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}
	
	
	
}
