package com.cx.sin.bean.locate;

import javax.persistence.Entity;
import javax.persistence.Table;

import com.cx.sin.bean.base.BaseModel;

/**
 * 区
 * @author XuXu
 *
 */
@Entity
@Table(name = "t_district")
public class District extends BaseModel {

	private static final long serialVersionUID = 8874915802139918635L;
	
	private String uuid;
	
	private String name;
	
	private String uuid_city;

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

	public String getUuid_city() {
		return uuid_city;
	}

	public void setUuid_city(String uuid_city) {
		this.uuid_city = uuid_city;
	}

}
