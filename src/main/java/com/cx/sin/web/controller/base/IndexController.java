package com.cx.sin.web.controller.base;

import java.util.Set;

import javax.servlet.http.HttpServletRequest;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;

import com.cx.sin.bean.privilege.PrivilegeGroup;
import com.cx.sin.bean.privilege.SystemPrivilege;
import com.cx.sin.bean.privilege.SystemPrivilegePK;
import com.cx.sin.bean.user.SysUser;
import com.cx.sin.service.user.SysUserService;

@Controller
@RequestMapping("/back/control")
public class IndexController {
	
	@Autowired
	private SysUserService sysUserService;

	/**
	 * 登入成功首页
	 * @return
	 */
	@RequestMapping("/main")
	public String main(HttpServletRequest request, Model model) {
		
		StringBuilder sbr = new StringBuilder();
		
		//获取权限
		SysUser sysUser = sysUserService.getSysUser(String.valueOf(request.getSession().getAttribute("loginUsername")));
		Set<PrivilegeGroup> groups = sysUser.getGroups();
		for (PrivilegeGroup group : groups) {
			Set<SystemPrivilege> privileges = group.getPrivileges();
			for (SystemPrivilege privilege : privileges) {
				SystemPrivilegePK pk = privilege.getId();
				sbr.append(pk.getModule() + "_" + pk.getPrivilege() + ",");
			}
		}

		model.addAttribute("privileges", sbr.toString());
		
		return "backIndex";
	}
	
	/**
	 * 载入首页内容
	 * @return
	 */
	@RequestMapping("/index")
	public String index() {
		return "index";
	}
	
}