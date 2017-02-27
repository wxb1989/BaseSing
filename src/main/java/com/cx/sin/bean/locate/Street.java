package com.cx.sin.bean.locate;

import javax.persistence.Entity;
import javax.persistence.Table;

import com.cx.sin.bean.base.BaseModel;

/**
 * 街道
 * @author XuXu
 *
 */
@Entity
@Table(name = "t_street")
public class Street extends BaseModel {

	private static final long serialVersionUID = -3838829198467107300L;
	
	private String uuid;
	
	private String name;
	
	private String uuid_district;

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

	public String getUuid_district() {
		return uuid_district;
	}

	public void setUuid_district(String uuid_district) {
		this.uuid_district = uuid_district;
	}

}
