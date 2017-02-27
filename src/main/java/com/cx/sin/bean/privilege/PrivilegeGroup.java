package com.cx.sin.bean.privilege;

import java.io.Serializable;
import java.util.HashSet;
import java.util.Set;

import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.JoinTable;
import javax.persistence.ManyToMany;
import javax.persistence.OrderBy;
import javax.persistence.Table;

import com.cx.sin.bean.user.SysUser;

/**
 * 权限组
 */
@Entity
@Table(name = "t_privilege_group")
public class PrivilegeGroup  implements Serializable {
	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	private String groupid;//uuid
	/* 组名称 */
	private String name;
	/*权限组类别*/
	private String type;
	/* 组下所拥有的权限 */
	private Set<SystemPrivilege> privileges = new HashSet<SystemPrivilege>();
	/* 权限组分配给的员工 */
	private Set<SysUser> sysUser = new HashSet<SysUser>();
	/* 权限组所属会员 */
	private Integer accountid;
	
	public PrivilegeGroup(){}
	
	@Column
	public Integer getAccountid() {
		return accountid;
	}
	
	public void setAccountid(Integer accountid) {
		this.accountid = accountid;
	}


	@Column(length=10,nullable=false)
	public String getType() {
		return type;
	}

	public void setType(String type) {
		this.type = type;
	}

	public PrivilegeGroup(String groupid) {
		this.groupid = groupid;
	}
	@ManyToMany(cascade=CascadeType.REFRESH, mappedBy="groups")
	public Set<SysUser> getSysUser() {
		return sysUser;
	}
	public void setSysUser(Set<SysUser> sysUser) {
		this.sysUser = sysUser;
	}
	@Id @Column(length=36)
	public String getGroupid() {
		return groupid;
	}
	public void setGroupid(String groupid) {
		this.groupid = groupid;
	}
	@Column(length=20,nullable=false)
	public String getName() {
		return name;
	}
	public void setName(String name) {
		this.name = name;
	}
	@ManyToMany(cascade=CascadeType.REFRESH, fetch=FetchType.EAGER)
	@JoinTable(name="gp",
			inverseJoinColumns={@JoinColumn(name="module",referencedColumnName="module"),
			@JoinColumn(name="privilege", referencedColumnName="privilege")},
			joinColumns=@JoinColumn(name="group_id"))
	@OrderBy("id asc")
	public Set<SystemPrivilege> getPrivileges() {
		return privileges;
	}
	public void setPrivileges(Set<SystemPrivilege> privileges) {
		this.privileges = privileges;
	}
	/**
	 * 添加权限
	 * @param privilege 权限
	 */
	public void addSystemPrivilege(SystemPrivilege privilege){
		this.privileges.add(privilege);
	}
	
	@Override
	public int hashCode() {
		final int prime = 31;
		int result = 1;
		result = prime * result + ((groupid == null) ? 0 : groupid.hashCode());
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
		final PrivilegeGroup other = (PrivilegeGroup) obj;
		if (groupid == null) {
			if (other.groupid != null)
				return false;
		} else if (!groupid.equals(other.groupid))
			return false;
		return true;
	}
	
}
