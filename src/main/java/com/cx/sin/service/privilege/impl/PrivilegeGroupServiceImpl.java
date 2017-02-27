package com.cx.sin.service.privilege.impl;

import java.io.Serializable;
import java.util.List;
import java.util.UUID;

import javax.persistence.Query;

import org.springframework.stereotype.Service;

import com.cx.sin.bean.privilege.PrivilegeGroup;
import com.cx.sin.bean.user.SysUser;
import com.cx.sin.dao.base.DaoSupport;
import com.cx.sin.service.privilege.PrivilegeGroupService;
//权限组管理
@Service
public class PrivilegeGroupServiceImpl extends DaoSupport<PrivilegeGroup> implements PrivilegeGroupService {

	@Override
	public void save(Object entity) {
		PrivilegeGroup group = (PrivilegeGroup)entity;
		group.setGroupid(UUID.randomUUID().toString());
		super.save(entity);
	}

	@Override
	public void delete(Serializable... entityids) {
		if(entityids!=null){
			for(Serializable id : entityids){
				PrivilegeGroup group = this.find(id);
				for(SysUser sysUser : group.getSysUser()){
					sysUser.getGroups().remove(group); 
				}
				em.remove(group);
			}
		}
	}

	public List findType(String type,Integer accountid) {
		String hql="";
		if(accountid==null){
			hql="select o from PrivilegeGroup o where o.type = ?1 and o.accountid is null";
		}else{
			hql="select o from PrivilegeGroup o where o.type = ?1 and o.accountid = ?2";
		}
		Query query = em.createQuery(hql);
		query.setParameter(1, type);
		if(accountid!=null){
			query.setParameter(2, accountid);
		}
		
		return query.getResultList();
	}
	
	public List findSystemType(String type) {
		Query query = em.createQuery("select o from PrivilegeGroup o where o.type = ?1");
		query.setParameter(1, type);
		return query.getResultList();
	}
	
}
