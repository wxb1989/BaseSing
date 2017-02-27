package com.cx.sin.service.privilege;

import java.util.List;

import com.cx.sin.bean.privilege.SystemPrivilege;
import com.cx.sin.dao.base.DAO;

/**
 * 权限接口
 * @author XuXu
 *
 */
public interface SystemPrivilegeService extends DAO<SystemPrivilege> {
	/**
	 * 批量保存权限
	 * @param privileges 权限
	 */
	public void saves(List<SystemPrivilege> privileges);
	
	public List findType(String type);
}
