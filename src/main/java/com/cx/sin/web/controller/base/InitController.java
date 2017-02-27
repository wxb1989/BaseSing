package com.cx.sin.web.controller.base;

import java.util.ArrayList;
import java.util.List;

import javax.servlet.http.HttpServletRequest;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;

import com.cx.sin.bean.privilege.PrivilegeGroup;
import com.cx.sin.bean.privilege.SystemPrivilege;
import com.cx.sin.bean.user.SysUser;
import com.cx.sin.service.privilege.PrivilegeGroupService;
import com.cx.sin.service.privilege.SystemPrivilegeService;
import com.cx.sin.service.user.SysUserService;
import com.cx.sin.utils.base.CommonUtil;

/**
 * 系统初始化
 * @author XuXu
 *
 */
@Controller
public class InitController {
	
	@Autowired
	private SysUserService sysUserService;
	@Autowired
	private SystemPrivilegeService privilegeService;
	@Autowired
	private PrivilegeGroupService groupService;

	@RequestMapping(value = "/init/{password}")
	public String init(@PathVariable String password, Model model, HttpServletRequest request) {
		
		String message = "";
		
		if (CommonUtil.generateMD5(password).equals("e10adc3949ba59abbe56e057f20f883e")) {
			try {
				
				initPrivileges();
				
				initPrivilegeGroup();
				
				initPrivilegeGpSystem();
				
				initSysUser();
				
				message = "初始化成功";
			}
			catch (Exception e) {
				e.printStackTrace();
				message = "初始化失败";
			}
		}
		else {
			message = "初始化密码错误";
		}
		
		request.getSession().removeAttribute("isLogin");
		request.getSession().removeAttribute("loginUsername");
		request.getSession().removeAttribute("sysUser");
		model.addAttribute("message", message);
		model.addAttribute("urladdress", "sysUser/loginUI");
		
		return "commons/message";
	}
	
	/**
	 * 初始化系统管理员
	 */
	private void initSysUser() throws Exception{
		String username = "admin";
		String password = CommonUtil.generateMD5("123456");
		String realname = "系统管理员";
		
		if(sysUserService.getCount()==0){
			SysUser sysUser = new SysUser();
			sysUser.setUsername(username);
			sysUser.setPassword(password);
			sysUser.setRealname(realname);
			sysUser.getGroups().addAll(groupService.findSystemType("system"));
			sysUserService.save(sysUser);
		}
		
	}
	
	private void initPrivileges() throws Exception{ 
		List<SystemPrivilege> privileges = this.getSystemPrivilegeSystemList();
		if(privilegeService.getCount()==0){
			privilegeService.saves(privileges);
		}else if(privilegeService.getCount()!=privileges.size()){
			for(int i=0;i<privileges.size();i++){
				privilegeService.update(privileges.get(i));
			}
		}
	}
	
	private void initPrivilegeGpSystem() throws Exception {
		List<SystemPrivilege> privileges = this.getSystemPrivilegeSystemList();
		List<PrivilegeGroup> groupList=groupService.findSystemType("system");
		if(groupList.size()>0){
			PrivilegeGroup group = null;
			for (int i = 0; i < groupList.size(); i++) {
				group = groupList.get(i);
				for (int j = 0; j < privileges.size(); j++) {
					SystemPrivilege systemPrivilege = privileges.get(j);
					if (privilegeService.find(systemPrivilege.getId()) == null) {
						group.addSystemPrivilege(systemPrivilege);
					}
				}
			}
			groupService.update(group);
		}

	}
	
	private void initPrivilegeGroup() throws Exception {
		if(groupService.getCount()==0){
			PrivilegeGroup group = new PrivilegeGroup();
			group.setName("系统管理组"); 
			group.setType("system");
			group.getPrivileges().addAll(privilegeService.findType("system"));
			groupService.save(group);
		}
	}
	
	private List<SystemPrivilege> getSystemPrivilegeSystemList() throws Exception {
		List<SystemPrivilege> privileges = new ArrayList<SystemPrivilege>();
		//------------------------系统权限------------------------------------------- 
		
		//系统管理 - 用户管理
		privileges.add(new SystemPrivilege("sysUserManage", "list", "查看用户","system"));
		privileges.add(new SystemPrivilege("sysUserManage", "add", "添加人员","system"));
		privileges.add(new SystemPrivilege("sysUserManage", "update", "修改人员","system"));
		privileges.add(new SystemPrivilege("sysUserManage", "delete", "删除人员","system"));
		privileges.add(new SystemPrivilege("sysUserManage", "reset", "密码重置","system"));
		privileges.add(new SystemPrivilege("sysUserManage", "setPri", "设置权限","system"));
		//系统管理 - 权限组管理
		privileges.add(new SystemPrivilege("privilegegroup", "list", "查看权限组","system"));
		privileges.add(new SystemPrivilege("privilegegroup", "add", "添加权限组","system"));
		privileges.add(new SystemPrivilege("privilegegroup", "update", "修改权限组","system"));
		privileges.add(new SystemPrivilege("privilegegroup", "delete", "删除权限组","system"));
		//系统管理 - 个人密码修改
		privileges.add(new SystemPrivilege("updatePassword", "list", "查看密码修改页面","system"));
		privileges.add(new SystemPrivilege("updatePassword", "update", "修改密码","system"));
		
		//数据维护 - 期刊管理
		privileges.add(new SystemPrivilege("periodical", "list", "查看期刊","system"));
		privileges.add(new SystemPrivilege("periodical", "add", "添加期刊","system"));
		privileges.add(new SystemPrivilege("periodical", "update", "修改期刊","system"));
		privileges.add(new SystemPrivilege("periodical", "delete", "删除期刊","system"));
		
		return privileges;
	}
	
}