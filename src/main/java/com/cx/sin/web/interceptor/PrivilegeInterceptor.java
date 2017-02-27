package com.cx.sin.web.interceptor;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.springframework.web.method.HandlerMethod;
import org.springframework.web.servlet.ModelAndView;
import org.springframework.web.servlet.handler.HandlerInterceptorAdapter;

import com.cx.sin.bean.privilege.PrivilegeGroup;
import com.cx.sin.bean.privilege.SystemPrivilege;
import com.cx.sin.bean.privilege.SystemPrivilegePK;
import com.cx.sin.bean.user.SysUser;
import com.cx.sin.web.controller.privilege.Permisstion;

/**
 * 权限拦截器
 * @author XuXu
 * 
 */
public class PrivilegeInterceptor extends HandlerInterceptorAdapter {
	public void afterCompletion(HttpServletRequest arg0, HttpServletResponse arg1, Object arg2, Exception arg3) throws Exception {  
        
    }  
  
    public void postHandle(HttpServletRequest request, HttpServletResponse response, Object arg2, ModelAndView view) throws Exception {  
        String contextPath = request.getContextPath();  
        if (view != null) {  
            request.setAttribute("base", contextPath);  
        }  
    }  
    
    public boolean preHandle(HttpServletRequest request, HttpServletResponse response, Object handler) throws Exception {  
          
        //处理Permission Annotation，实现方法级权限控制  
        HandlerMethod method = (HandlerMethod)handler;
        Permisstion permisstion = method.getMethodAnnotation(Permisstion.class);
        
        //如果为空在表示该方法不需要进行权限验证
        if (permisstion == null) {
            return true;
        }
        else {
        	SystemPrivilege methodPrivilege = new SystemPrivilege(
    				new SystemPrivilegePK(permisstion.module(), permisstion.privilege()));
    		SysUser sysUser = (SysUser)request.getSession().getAttribute("sysUser");
    		for(PrivilegeGroup group : sysUser.getGroups()){
    			if(group.getPrivileges().contains(methodPrivilege)){
    				return true;
    			}
    		}
    		response.sendRedirect(request.getContextPath()+"/privilege.jsp");
    		return false;
        }

    }
}
