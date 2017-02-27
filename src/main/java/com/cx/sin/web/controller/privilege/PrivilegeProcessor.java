package com.cx.sin.web.controller.privilege;
/*package com.cx.tg.web.controller.privilege;

import java.io.IOException;
import java.lang.reflect.Field;
import java.lang.reflect.Method;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.apache.commons.beanutils.ConvertUtils;
import org.apache.struts.action.Action;
import org.apache.struts.action.ActionForm;
import org.apache.struts.action.ActionForward;
import org.apache.struts.action.ActionMapping;
import org.apache.struts.action.ActionServlet;
import org.apache.struts.actions.DispatchAction;
import org.apache.struts.config.ModuleConfig;
import org.codehaus.jackson.map.ObjectMapper;
import org.springframework.web.struts.DelegatingTilesRequestProcessor;

import cn.trading.bean.privilege.PrivilegeGroup;
import cn.trading.bean.privilege.SysUser;
import cn.trading.bean.privilege.SystemPrivilege;
import cn.trading.bean.privilege.SystemPrivilegePK;
import cn.trading.utils.ParameterValues;
import cn.trading.utils.Serialized;
import cn.trading.utils.SiteUrl;
import cn.trading.utils.WebUtil;

*//**
 * 权限控制器
 * @author Administrator
 *
 *//*
public class PrivilegeProcessor extends DelegatingTilesRequestProcessor {

	private ObjectMapper objectMapper = new ObjectMapper();
	
	@Override
	public void init(ActionServlet actionServlet, ModuleConfig moduleConfig)
			throws ServletException {
		super.init(actionServlet, moduleConfig);
	}
	
	@Override
	protected ActionForward processActionPerform(HttpServletRequest request,
			HttpServletResponse response, Action action, ActionForm form,
			ActionMapping mapping) throws IOException, ServletException {
		
		if (form != null) {
			for (Field field : form.getClass().getDeclaredFields()) {
				
				if (field.isAnnotationPresent(Serialized.class)) {
					String s = request.getParameter(field.getName());
					if (s == null || s.isEmpty()) continue;
					Object value = objectMapper.readValue(s, field.getType());
					field.setAccessible(true);
					try {
						field.set(form, value);
					} catch (IllegalArgumentException e) {
						e.printStackTrace();
					} catch (IllegalAccessException e) {
						e.printStackTrace();
					}
				}
				ParameterValues multi = field.getAnnotation(ParameterValues.class);
				if (multi != null) {
					String name = multi.value();
					String[] values = request.getParameterValues(name);
					if (values != null) {
						field.setAccessible(true);
						try {
							field.set(form, ConvertUtils.convert(values, field.getType().getComponentType()));
						} catch (IllegalArgumentException e) {
							e.printStackTrace();
						} catch (IllegalAccessException e) {
							e.printStackTrace();
						}
					}
					
				}
			}
		}
		
		
		
		if(WebUtil.getRequestURI(request).startsWith("/control/")){//只需要把管理台的action进行权限校验
			if(!validate(request, action, mapping)){//如果用户没有权限
				String message = "";
				if (request.getSession().getAttribute("lan") == null) {
					message = "您没有执行该操作的权限";
				}
				else {
					if (request.getSession().getAttribute("lan").toString().equals("zh")) {
						message = "您没有执行该操作的权限";
					}
					else {
						message = "No operating authority";
					}
				}
				
				request.setAttribute("message", message);
				request.setAttribute("urladdress", SiteUrl.readUrl("control.index"));
				return mapping.findForward("message_pri");
			}
		}
		
		return super.processActionPerform(request, response, action, form, mapping);
	}
	*//**
	 * 系统后台权限校验
	 * @return
	 *//*
	private boolean validate(HttpServletRequest request, Action action,
			ActionMapping mapping) {
		Method method = getCurrentMethod(request, action, mapping);
		if(method!=null && method.isAnnotationPresent(Permisstion.class)){
			Permisstion permisstion = method.getAnnotation(Permisstion.class);
			SystemPrivilege methodPrivilege = new SystemPrivilege(
					new SystemPrivilegePK(permisstion.module(), permisstion.privilege()));
			SysUser sysUser = (SysUser)request.getSession().getAttribute("sysuser");
			for(PrivilegeGroup group : sysUser.getGroups()){
				if(group.getPrivileges().contains(methodPrivilege)){
					return true;
				}
			}
			return false;
		}
		return true;
	}
	
	*//**
	 * 得到当前执行的方法
	 * @return
	 *//*
	private Method getCurrentMethod(HttpServletRequest request, Action action,
			ActionMapping mapping) {
		String methodname = "execute";
		if(DispatchAction.class.isAssignableFrom(action.getClass())){
			methodname = request.getParameter(mapping.getParameter());
		}
		try {
			return action.getClass().getMethod(methodname, ActionMapping.class, ActionForm.class, HttpServletRequest.class, HttpServletResponse.class);
		} catch (Exception e) {}
		return null;
	}

}
*/