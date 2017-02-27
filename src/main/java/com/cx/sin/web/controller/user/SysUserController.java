package com.cx.sin.web.controller.user;

import javax.servlet.http.HttpServletRequest;

import org.apache.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;

import com.cx.sin.bean.user.SysUserForm;
import com.cx.sin.service.user.SysUserService;

/**
 * 系统用户登入
 * @author XuXu
 *
 */
@Controller
@RequestMapping("/sysUser")
public class SysUserController {
	
	private static final Logger LOGGER = Logger.getLogger(SysUserController.class);
	
	@Autowired
	private SysUserService sysUserService;
	
	/**
	 * 登入页面
	 * @return
	 */
	@RequestMapping("/loginUI")
	public String loginUI(HttpServletRequest request) {
		if (request.getSession().getAttribute("isLogin") != null) {
			return "backIndex";
		}
		return "loginUI";
	}
	
	/**
	 * 登入验证
	 * @param form
	 * @param model
	 * @param request
	 * @return
	 */
	@RequestMapping("/verify")
	public String verify(SysUserForm form, Model model, HttpServletRequest request) {
		String path = "";
		String message = "";
		
		int result = sysUserService.verifySysUser(form.getUsername(), form.getPassword());
		if (result == 0) {
			message = "用户名或密码错误";
			path = "loginUI";
			model.addAttribute("message", message);
		}
		else if (result == 1) {
			path = "redirect:/back/control/main";
			request.getSession().setAttribute("isLogin", "true");
			request.getSession().setAttribute("loginUsername", form.getUsername());
			request.getSession().setAttribute("sysUser", sysUserService.getSysUser(form.getUsername()));
		}
		
		return path;
	}
	
	/**
	 * 退出
	 * @return
	 */
	@RequestMapping("/exit")
	public String exit(HttpServletRequest request) {
		request.getSession().removeAttribute("isLogin");
		request.getSession().removeAttribute("loginUsername");
		request.getSession().removeAttribute("sysUser");
		return "redirect:/sysUser/loginUI";
	}
	
	
}
