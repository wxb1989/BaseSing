package com.cx.sin.service.locate;

import java.util.List;

import com.cx.sin.bean.locate.Street;
import com.cx.sin.dao.base.DAO;

/**
 * 街道
 * @author XuXu
 *
 */
public interface StreetService extends DAO<Street> {
	
	/**
	 * 根据区获取街道
	 * @param uuid_district 区uuid
	 * @return
	 * @throws Exception
	 */
	public List<Street> queryStreetByDistrict(String uuid_district) throws Exception;
	
	/**
	 * 查询街道
	 * @param uuid
	 * @return
	 * @throws Exception
	 */
	public Street queryStreetByUuid(String uuid) throws Exception;
	
}
