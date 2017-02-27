package com.cx.sin.bean.locate;

import javax.persistence.Entity;
import javax.persistence.Table;

import com.cx.sin.bean.base.BaseModel;

/**
 * 市
 * @author XuXu
 *
 */
@Entity
@Table(name = "t_city")
public class City extends BaseModel {

	private static final long serialVersionUID = 282460320853083463L;
	
	private String uuid;
	
	private String name;
	
	private String uuid_province;
	
	/**
	 * 此城市是否开通线上讲座申请（0：无， 1：有）
	 */
	private int hasLecture;

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

	public String getUuid_province() {
		return uuid_province;
	}

	public void setUuid_province(String uuid_province) {
		this.uuid_province = uuid_province;
	}

	public int getHasLecture() {
		return hasLecture;
	}

	public void setHasLecture(int hasLecture) {
		this.hasLecture = hasLecture;
	}

}
