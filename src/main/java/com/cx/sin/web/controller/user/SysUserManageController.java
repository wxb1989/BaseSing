package com.cx.sin.web.controller.user;

import javax.servlet.http.HttpServletRequest;

import org.apache.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;

import com.cx.sin.bean.privilege.PrivilegeGroup;
import com.cx.sin.bean.user.SysUser;
import com.cx.sin.bean.user.SysUserForm;
import com.cx.sin.service.privilege.PrivilegeGroupService;
import com.cx.sin.service.user.SysUserService;
import com.cx.sin.web.controller.privilege.Permisstion;

/**
 * 系统用户管理
 * @author XuXu
 *
 */
@Controller
@RequestMapping("/back/control/sysUserManage")
public class SysUserManageController {
	
	private static final Logger LOGGER = Logger.getLogger(SysUserManageController.class);
	
	@Autowired
	private SysUserService sysUserService;
	@Autowired
	private PrivilegeGroupService groupService;
	
	/**
	 * 用户列表
	 * @return
	 */
	@Permisstion(module="sysUserManage", privilege="list")
	@RequestMapping("/list")
	public String list(SysUserForm form, Model model, HttpServletRequest request) {
		String currentpage = form.getCurrentpage();
		if (currentpage != null && !currentpage.isEmpty()) {
			form.setPage(Integer.parseInt(currentpage));
		}
		
		try {
			model.addAttribute("pageView", sysUserService.getSysUsers(form));
			model.addAttribute("formData", form);
		}
		catch (Exception e) {
			e.printStackTrace();
		}
		return "sysUser/list";
	}
	
	
	/**
	 * 添加用户页面
	 * @return
	 */
	@Permisstion(module="sysUserManage", privilege="add")
	@RequestMapping("/addUI")
	public String addUI(SysUserForm form, Model model, HttpServletRequest request) {
		
		String query = this.query(form);
		
		model.addAttribute("query", query);
		
		return "sysUser/add";
	}
	
	/**
	 * 添加用户
	 * @return
	 */
	@Permisstion(module="sysUserManage", privilege="add")
	@RequestMapping("/add")
	public String add(SysUserForm form, Model model, HttpServletRequest request) {
		
		String message = "";
		String urladdress = "back/control/sysUserManage/list";
		
		try {
			int result = sysUserService.addSysUser(form);
			
			if (result == 0) {
				message = "用户名已存在";
			}
			else if (result == 1) {
				message = "添加用户成功";
			}
		}
		catch (Exception e) {
			e.printStackTrace();
			message = "系统异常";
		}
		
		model.addAttribute("message", message);
		model.addAttribute("urladdress", urladdress);
		model.addAttribute("query", form.getQuery());
		
		return "commons/message";
	}
	
	/**
	 * 修改用户页面
	 * @return
	 */
	@Permisstion(module="sysUserManage", privilege="update")
	@RequestMapping("/updateUI/{id}")
	public String updateUI(SysUserForm form, @PathVariable int id, Model model) {
		SysUser sysUser = sysUserService.find(id);
		
		String query = this.query(form);
		
		model.addAttribute("query", query);
		model.addAttribute("sysUser", sysUser);
		
		return "sysUser/update";
	}
	
	/**
	 * 修改用户
	 * @return
	 */
	@Permisstion(module="sysUserManage", privilege="update")
	@RequestMapping("/update/{id}")
	public String update(@PathVariable int id, SysUserForm form, Model model) {
		
		String message = "";
		String urladdress = "back/control/sysUserManage/list";
		
		try {
			int result = sysUserService.updateSysUser(form, id);
			if (result == 1) {
				message = "修改用户成功";
			}
			else {
				message = "修改用户失败";
			}
		}
		catch(Exception e) {
			e.printStackTrace();
			message = "系统异常";
		}
		
		model.addAttribute("message", message);
		model.addAttribute("urladdress", urladdress);
		model.addAttribute("query", form.getQuery());
		
		return "commons/message";
	}
	
	/**
	 * 删除用户
	 * @return
	 */
	@Permisstion(module="sysUserManage", privilege="delete")
	@RequestMapping("/delete/{ids}")
	public String delete(SysUserForm form, @PathVariable String ids, Model model) {
		
		String message = "";
		String urladdress = "back/control/sysUserManage/list";
		String query = this.query(form);
		
		try {
			int result = sysUserService.deleteSysUsers(ids);
			if (result == 1) {
				message = "删除用户成功";;
			}
			else {
				message = "删除用户失败";
			}
		}
		catch (Exception e) {
			e.printStackTrace();
			message = "系统异常";
		}
		
		model.addAttribute("message", message);
		model.addAttribute("urladdress", urladdress);
		model.addAttribute("query", query);
		
		return "commons/message";
	}
	
	/**
	 * 重置密码
	 * @return
	 */
	@Permisstion(module="sysUserManage", privilege="reset")
	@RequestMapping("/reset/{id}")
	public String reset(SysUserForm form, @PathVariable int id, Model model) {
		
		String message = "";
		String urladdress = "back/control/sysUserManage/list";
		String query = this.query(form);
		
		try {
			int result = sysUserService.resetPassword(id);
			if (result == 1) {
				message = "密码重置成功";
			}
			else {
				message = "密码重置失败";
			}
		}
		catch(Exception e) {
			e.printStackTrace();
			message = "系统异常";
		}
		
		model.addAttribute("message", message);
		model.addAttribute("urladdress", urladdress);
		model.addAttribute("query", query);
		
		return "commons/message";
	}
	
	/**
	 * 用户密码修改
	 * @return
	 */
	@Permisstion(module="updatePassword", privilege="list")
	@RequestMapping("/updatePasswordUI")
	public String updatePasswordUI() {
		return "sysUser/updatePassword";
	}
	
	/**
	 * 用户密码修改
	 * @return
	 */
	@Permisstion(module="updatePassword", privilege="update")
	@RequestMapping("/updatePassword")
	public String updatePassword(SysUserForm form, HttpServletRequest request, Model model) {
		
		String message = "";
		String urladdress = "back/control/sysUserManage/updatePasswordUI";
		
		String oldPassword = request.getParameter("oldPassword");
		String username = (String)request.getSession().getAttribute("loginUsername");
		
		try {
			int result = sysUserService.updatePassword(username, oldPassword, form.getPassword());
			if (result == 1) {
				message = "密码修改成功";
			}
			else if (result == 0) {
				message = "原密码错误";
			}
		}
		catch(Exception e) {
			e.printStackTrace();
			message = "系统异常";
		}
		
		model.addAttribute("message", message);
		model.addAttribute("urladdress", urladdress);
		
		return "commons/message";
	}
	
	/**
	 * 设置用户权限页面
	 * @return
	 */
	@Permisstion(module="sysUserManage", privilege="setPri")
	@RequestMapping("/setPrivilegeUI/{id}")
	public String setPrivilegeUI(SysUserForm form, @PathVariable int id, Model model) {
		SysUser sysUser = sysUserService.find(id);
		
		String query = this.query(form);
		
		model.addAttribute("query", query);
		model.addAttribute("groups", groupService.findSystemType("system"));
		model.addAttribute("usergroups", sysUser.getGroups());
		model.addAttribute("sysUser", sysUser);
		
		return "sysUser/privilege";
	}
	
	/**
	 * 设置用户权限
	 * @return
	 */
	@Permisstion(module="sysUserManage", privilege="setPri")
	@RequestMapping("/setPrivilege/{id}")
	public String setPrivilege(@PathVariable int id, SysUserForm form, Model model) {
		
		String message = "";
		String urladdress = "back/control/sysUserManage/list";
		
		try {
			SysUser sysUser = sysUserService.find(id);
			sysUser.getGroups().clear();
			
			if (form.getGroupids() != null && form.getGroupids().length != 0) {
				for(String groupid : form.getGroupids()){ 
					PrivilegeGroup pg=groupService.find(groupid);
					sysUser.addPrivilegeGroup(pg);
				}
			}
			sysUserService.update(sysUser);
			
			message = "设置用户权限成功";
		}
		catch (Exception e) {
			e.printStackTrace();
			message = "系统异常";
		}
		
		model.addAttribute("message", message);
		model.addAttribute("urladdress", urladdress);
		model.addAttribute("query", form.getQuery());
		
		return "commons/message";
	}
	
	/**
	 * 查询参数传递辅助方法
	 * @return
	 */
	private String query(SysUserForm form) {
		StringBuffer query = new StringBuffer();
		query.append("currentpage::" + form.getPage() + ",");
		if (form.getUsername() != null && !"".equals(form.getUsername())) {
			query.append("username::" + form.getUsername() + ",");
		}
		if (form.getRealname() != null && !"".equals(form.getRealname())) {
			query.append("realname::" + form.getRealname() + ",");
		}
		return query.toString();
	}
	
}
