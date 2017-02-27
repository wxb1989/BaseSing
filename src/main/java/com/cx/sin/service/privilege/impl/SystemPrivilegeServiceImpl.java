package com.cx.sin.service.privilege.impl;

import java.util.List;

import javax.persistence.Query;

import org.springframework.stereotype.Service;

import com.cx.sin.bean.privilege.SystemPrivilege;
import com.cx.sin.dao.base.DaoSupport;
import com.cx.sin.service.privilege.SystemPrivilegeService;
//系统权限设置
@Service
public class SystemPrivilegeServiceImpl extends DaoSupport<SystemPrivilege>	implements SystemPrivilegeService {
	
	public void saves(List<SystemPrivilege> privileges){
		for(SystemPrivilege entity: privileges){
			this.em.merge(entity);
			this.em.flush();
			//this.save(entity);
		}
	}

	public List findType(String type) {
		// TODO Auto-generated method stub
		Query query = em.createQuery("select o from SystemPrivilege o where o.type = ?1 order by indexNumber asc");
		query.setParameter(1, type);
		return query.getResultList();
	}
}
