package com.cx.sin.service.user.impl;

import java.util.ArrayList;
import java.util.LinkedHashMap;
import java.util.List;

import javax.persistence.Query;

import org.springframework.stereotype.Service;

import com.cx.sin.bean.base.PageView;
import com.cx.sin.bean.base.QueryResult;
import com.cx.sin.bean.user.SysUser;
import com.cx.sin.bean.user.SysUserForm;
import com.cx.sin.dao.base.DaoSupport;
import com.cx.sin.service.user.SysUserService;
import com.cx.sin.utils.base.CommonUtil;

@Service
public class SysUserServiceImpl extends DaoSupport<SysUser> implements
		SysUserService {

	@Override
	public int verifySysUser(String username, String password) {
		int result = 0;
		password = CommonUtil.generateMD5(password);
		
		if (username == "" || username == null || password == "" || password == null) {
			result = 0;
		}
		else {
			String jpql = "select o from SysUser o where o.username = :username and o.password = :password and o.deleteFlag = :deleteFlag";
			Query query = em.createQuery(jpql);
			query.setParameter("username", username);
			query.setParameter("password", password);
			query.setParameter("deleteFlag", false);
			List<SysUser> sysUsers = query.getResultList();
			result = sysUsers.size();
		}
		
		return result;
	}
	
	@Override
	public int verifyUsername(String username) {
		int result = 0;
		
		String jpql = "select o from SysUser o where o.username = :username and o.deleteFlag = :deleteFlag";
		Query query = em.createQuery(jpql);
		query.setParameter("username", username);
		query.setParameter("deleteFlag", false);
		List<SysUser> sysUsers = query.getResultList();
		result = sysUsers.size();
		
		return result;
	}
	
	@Override
	public SysUser getSysUser(String username) {
		String jpql = "select o from SysUser o where o.username = :username";
		Query query = em.createQuery(jpql);
		query.setParameter("username", username);
		List<SysUser> sysUsers = query.getResultList();
		if (sysUsers.size() > 0) {return sysUsers.get(0);}
		return null;
	}

	@Override
	public int addSysUser(SysUserForm form) throws Exception {
		
		String username = form.getUsername();
		String password = CommonUtil.generateMD5("123456");
		String realname = form.getRealname();
		
		if (this.verifyUsername(username) == 0) {
			SysUser sysUser = new SysUser();
			sysUser.setUsername(username);
			sysUser.setPassword(password);
			sysUser.setRealname(realname);
			
			em.persist(sysUser);
			
			return 1;
		}
		
		return 0;
	}

	@Override
	public PageView<SysUser> getSysUsers(SysUserForm form) throws Exception{
		String username = form.getUsername();
		String realname = form.getRealname();
		
		StringBuilder jpql = new StringBuilder("o.deleteFlag = false");
		List<Object> params = new ArrayList<Object>();
		
		if (username != null && !username.isEmpty()) {
			jpql.append(" and o.username like ?").append(params.size() + 1);
			params.add('%' + username + '%');
		}
		if (realname != null && !realname.isEmpty()) {
			jpql.append(" and o.realname like ?").append(params.size() + 1);
			params.add('%' + realname + '%');
		}
		
		LinkedHashMap<String, String> order = new LinkedHashMap<String, String>();
		
		order.put("id", "desc");
		
		PageView<SysUser> pageView = new PageView<SysUser>(form.getCount(), form.getPage());
		QueryResult<SysUser> result = this.getScrollData(pageView.getFirstResult(), pageView.getMaxresult(), jpql.toString(), params.toArray(), order);
		pageView.setQueryResult(result);
		
		return pageView;
	}

	@Override
	public int updateSysUser(SysUserForm form, int id) throws Exception {
		
		String realname = form.getRealname();
		SysUser sysUser = em.getReference(SysUser.class, id);
		sysUser.setRealname(realname);
		
		return 1;
	}

	@Override
	public int deleteSysUsers(String ids) throws Exception {
		List<Integer> idsList = new ArrayList<Integer>();
		String[] idsStringArray = ids.split(",");
		for(int i=0; i < idsStringArray.length; i++) {
			idsList.add(Integer.parseInt(idsStringArray[i]));
		}
		
		for (int id : idsList) {
			SysUser sysUser = em.getReference(SysUser.class, id);
			//sysUser.setDeleteFlag(true);
		}
		return 1;
	}

	@Override
	public int resetPassword(int id) throws Exception {
		
		SysUser sysUser = em.getReference(SysUser.class, id);
		sysUser.setPassword(CommonUtil.generateMD5("123456"));
		
		return 1;
	}

	@Override
	public int updatePassword(String username, String oldPassword, String newPassword)
			throws Exception {
		String jpql = "select o from SysUser o where o.username = :username";
		Query query = em.createQuery(jpql);
		query.setParameter("username", username);
		SysUser sysUser = (SysUser)query.getResultList().get(0);
		
		if (sysUser.getPassword().equals(CommonUtil.generateMD5(oldPassword))) {
			sysUser.setPassword(CommonUtil.generateMD5(newPassword));
			return 1;
		}
		
		return 0;
	}

}
