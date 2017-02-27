package com.cx.sin.service.privilege;

import java.util.List;

import com.cx.sin.bean.privilege.PrivilegeGroup;
import com.cx.sin.dao.base.DAO;

/**
 * 权限组接口
 * @author XuXu
 *
 */
public interface PrivilegeGroupService extends DAO<PrivilegeGroup> {
	
	public List findType(String type,Integer accountid);
	
	public List findSystemType(String type);
	
}
