package com.cx.sin.bean.privilege;

import java.io.Serializable;
import java.util.HashSet;
import java.util.Set;

import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.EmbeddedId;
import javax.persistence.Entity;
import javax.persistence.ManyToMany;
import javax.persistence.Table;
/**
 * 权限
 */
@Entity
@Table(name = "t_system_privilege")
public class SystemPrivilege  implements Serializable {
	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	/* 权限id */
	private SystemPrivilegePK id;
	/* 权限名称 */
	private String name;
	/*权限类别*/
	private String type;
	
	private Boolean visible = false;
	
	private Integer indexNumber;
	/* 权限所在权限组 */
	private Set<PrivilegeGroup> groups = new HashSet<PrivilegeGroup>();
	
	@ManyToMany(mappedBy="privileges",cascade=CascadeType.REFRESH)
	public Set<PrivilegeGroup> getGroups() {
		return groups;
	}

	public void setGroups(Set<PrivilegeGroup> groups) {
		this.groups = groups;
	}

	public SystemPrivilege(SystemPrivilegePK id) {
		this.id = id;
	}

	@Column(nullable=false)
	public Boolean getVisible() {
		return visible;
	}

	public void setVisible(Boolean visible) {
		this.visible = visible;
	}

	public SystemPrivilege(){}
	
	public SystemPrivilege(String module, String privilege, String name, String type) {
		this.id = new SystemPrivilegePK(module, privilege);
		this.name = name;
		this.type = type;
	}
	
	public SystemPrivilege(String module, String privilege, String name, String type,Integer indexNumber) {
		this.id = new SystemPrivilegePK(module, privilege);
		this.name = name;
		this.type = type;
		this.indexNumber=indexNumber;
	}


	@Column(length=10,nullable=false)
	public String getType() {
		return type;
	}

	public void setType(String type) {
		this.type = type;
	}
	

	@EmbeddedId
	public SystemPrivilegePK getId() {
		return id;
	}
	public void setId(SystemPrivilegePK id) {
		this.id = id;
	}
	@Column(length=100,nullable=false)
	public String getName() {
		return name;
	}
	public void setName(String name) {
		this.name = name;
	}
	@Override
	public int hashCode() {
		final int prime = 31;
		int result = 1;
		result = prime * result + ((id == null) ? 0 : id.hashCode());
		return result;
	}
	@Override
	public boolean equals(Object obj) {
		if (this == obj)
			return true;
		if (obj == null)
			return false;
		if (getClass() != obj.getClass())
			return false;
		final SystemPrivilege other = (SystemPrivilege) obj;
		if (id == null) {
			if (other.id != null)
				return false;
		} else if (!id.equals(other.id))
			return false;
		return true;
	}

	public Integer getIndexNumber() {
		return indexNumber;
	}

	public void setIndexNumber(Integer indexNumber) {
		this.indexNumber = indexNumber;
	}
	
}
