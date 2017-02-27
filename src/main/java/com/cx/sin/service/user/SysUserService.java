package com.cx.sin.service.user;

import com.cx.sin.bean.base.PageView;
import com.cx.sin.bean.user.SysUser;
import com.cx.sin.bean.user.SysUserForm;
import com.cx.sin.dao.base.DAO;

/**
 * 系统用户接口
 * @author XuXu
 *
 */
public interface SysUserService extends DAO<SysUser> {
	
	/**
	 * 验证登入（0：失败， 1：成功）
	 * @param username
	 * @param password
	 * @return
	 */
	public int verifySysUser(String username, String password);
	
	/**
	 * 验证用户名是否存在（0：不存在，其他则存在）
	 * @param username
	 * @return
	 */
	public int verifyUsername(String username);
	
	/**
	 * 添加用户（0：用户名存在，1：成功）
	 * @return
	 */
	public int addSysUser(SysUserForm form) throws Exception;
	
	/**
	 * 根据用户名获取用户
	 * @param username
	 * @return
	 */
	public SysUser getSysUser(String username);
	
	/**
	 * 获取用户列表
	 * @return
	 */
	public PageView<SysUser> getSysUsers(SysUserForm form) throws Exception;
	
	/**
	 * 修改用户（1：成功）
	 * @return
	 */
	public int updateSysUser(SysUserForm form, int id) throws Exception;
	
	/**
	 * 删除用户（1：成功）
	 * @param ids
	 * @return
	 */
	public int deleteSysUsers(String ids) throws Exception;
	
	/**
	 * 重置密码（1：成功）
	 * @return
	 * @throws Exception
	 */
	public int resetPassword(int id) throws Exception;
	
	/**
	 * 修改密码（0：原密码错误，1：成功）
	 * @param oldPassword
	 * @param newPassword
	 * @return
	 * @throws Exception
	 */
	public int updatePassword(String username, String oldPassword, String newPassword) throws Exception;
	
}
