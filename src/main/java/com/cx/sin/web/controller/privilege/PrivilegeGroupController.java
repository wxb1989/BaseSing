package com.cx.sin.web.controller.privilege;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.LinkedHashMap;
import java.util.List;

import javax.servlet.http.HttpServletRequest;

import org.apache.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;

import com.cx.sin.bean.base.PageView;
import com.cx.sin.bean.privilege.PrivilegeGroup;
import com.cx.sin.bean.privilege.PrivilegeGroupForm;
import com.cx.sin.bean.privilege.SystemPrivilege;
import com.cx.sin.bean.privilege.SystemPrivilegePK;
import com.cx.sin.service.privilege.PrivilegeGroupService;
import com.cx.sin.service.privilege.SystemPrivilegeService;

/**
 * 权限组
 * @author XuXu
 *
 */
@Controller
@RequestMapping("/back/control/privilegegroup")
public class PrivilegeGroupController {
	
	private static final Logger LOGGER = Logger.getLogger(PrivilegeGroupController.class);
	
	@Autowired
	private PrivilegeGroupService groupService;
	
	@Autowired
	private SystemPrivilegeService privilegeService;
	
	/**
	 * 权限组列表
	 * @param form
	 * @param model
	 * @param request
	 * @return
	 */
	@Permisstion(module="privilegegroup", privilege="list")
	@RequestMapping("/list")
	public String list(PrivilegeGroupForm form, Model model, HttpServletRequest request) {
		
		String currentpage = form.getCurrentpage();
		if (currentpage != null && !currentpage.isEmpty()) {
			form.setPage(Integer.parseInt(currentpage));
		}
		
		PageView<PrivilegeGroup> pageView = new PageView<PrivilegeGroup>(form.getCount(), form.getPage());
		
		StringBuffer jpql = new StringBuffer("");
		List<Object> params = new ArrayList<Object>();
	
	    jpql.append(" o.type = ?").append((params.size()+1));
	    params.add("system");
	    if (form.getName() != null && !form.getName().isEmpty()) {
	    	jpql.append(" and o.name like ?").append((params.size()+1));
		    params.add("%" + form.getName() + "%");
	    }
	    
	    LinkedHashMap<String, String> order = new LinkedHashMap<String, String>();
		order.put("groupid", "desc");
		
		pageView.setQueryResult(groupService.getScrollData(pageView.getFirstResult(), pageView.getMaxresult(), jpql.toString(), params.toArray(), order));
		
		model.addAttribute("pageView", pageView);
		model.addAttribute("formData", form);

		return "privilege/list";
	}
	
	/**
	 * 添加权限组页面
	 * @return
	 */
	@Permisstion(module="privilegegroup", privilege="add")
	@RequestMapping("/addUI")
	public String addUI(PrivilegeGroupForm form, Model model) {
		
		String query = this.query(form);
		
		model.addAttribute("query", query);
		model.addAttribute("privileges", privilegeService.findType("system"));
		
		return "privilege/add";
	}
	
	/**
	 * 添加权限组
	 * @return
	 */
	@Permisstion(module="privilegegroup", privilege="add")
	@RequestMapping("/add")
	public String add(PrivilegeGroupForm form, Model model) {
		
		String message = "";
		String urladdress = "";
		
		String[] privileges = form.getPrivileges();
		
		PrivilegeGroup group = new PrivilegeGroup();
		group.setName(form.getName());
		group.setType("system");
		
		if (privileges != null) {
			List<SystemPrivilegePK> ids = new ArrayList<SystemPrivilegePK>();
			for (String privilege : privileges) {
				String[] pk = privilege.split("-");
				SystemPrivilegePK spk = new SystemPrivilegePK(pk[0], pk[1]);
				ids.add(spk);
			}
			for(SystemPrivilegePK id : ids){
				group.addSystemPrivilege(new SystemPrivilege(id));
			}
		}
		
		groupService.save(group);
		message = "添加权限组成功";
		urladdress = "back/control/privilegegroup/list";
		
		model.addAttribute("message", message);
		model.addAttribute("urladdress", urladdress);
		model.addAttribute("query", form.getQuery());
		
		return "commons/message";
	}
	
	/**
	 * 修改权限组页面
	 * @return
	 */
	@Permisstion(module="privilegegroup", privilege="update")
	@RequestMapping("/updateUI/{groupid}")
	public String updateUI(PrivilegeGroupForm form, @PathVariable String groupid, Model model) {
		PrivilegeGroup group = groupService.find(groupid);
		
		String query = this.query(form);
		
		model.addAttribute("privileges", privilegeService.getScrollData().getResultlist());
		model.addAttribute("selectprivileges", group.getPrivileges());
		model.addAttribute("groupName", group.getName());
		model.addAttribute("groupid", group.getGroupid());
		model.addAttribute("query", query);
		
		return "privilege/update";
	}
	
	/**
	 * 修改权限组
	 * @return
	 */
	@Permisstion(module="privilegegroup", privilege="update")
	@RequestMapping("/update/{groupid}")
	public String update(@PathVariable String groupid, PrivilegeGroupForm form, Model model) {
		
		String message = "";
		String urladdress = "";
		
		String[] privileges = form.getPrivileges();
		List<SystemPrivilegePK> ids = new ArrayList<SystemPrivilegePK>();
		
		if (privileges != null) {
			for (String privilege : privileges) {
				String[] pk = privilege.split("-");
				SystemPrivilegePK spk = new SystemPrivilegePK(pk[0], pk[1]);
				ids.add(spk);
			}
		}
		
		PrivilegeGroup group = groupService.find(groupid);
		group.setName(form.getName());
		group.getPrivileges().clear();
		for(SystemPrivilegePK sid : ids){
			group.addSystemPrivilege(new SystemPrivilege(sid));
		}
		groupService.update(group);
		
		message = "修改权限组成功";
		urladdress = "back/control/privilegegroup/list";
		
		model.addAttribute("message", message);
		model.addAttribute("urladdress", urladdress);
		model.addAttribute("query", form.getQuery());
		
		return "commons/message";
	}
	
	/**
	 * 删除权限组
	 * @return
	 */
	@Permisstion(module="privilegegroup", privilege="delete")
	@RequestMapping("/delete/{groupid}")
	public String delete(PrivilegeGroupForm form, @PathVariable String groupid, Model model) {
		
		String message = "";
		String urladdress = "";
		String query = this.query(form);
		
		String[] gids = groupid.split(",");
		for (String gid : gids) {
			groupService.delete((Serializable)gid);
		}
		
		message = "删除权限组成功";
		urladdress = "back/control/privilegegroup/list";
		
		model.addAttribute("message", message);
		model.addAttribute("urladdress", urladdress);
		model.addAttribute("query", query);
		
		return "commons/message";
	}
	
	/**
	 * 查询参数传递辅助方法
	 * @return
	 */
	private String query(PrivilegeGroupForm form) {
		StringBuffer query = new StringBuffer();
		query.append("currentpage::" + form.getPage() + ",");
		if (form.getName() != null && !"".equals(form.getName())) {
			query.append("name::" + form.getName() + ",");
		}
		return query.toString();
	}
	
}
