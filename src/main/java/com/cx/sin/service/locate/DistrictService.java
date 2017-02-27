package com.cx.sin.service.locate;

import java.util.List;

import com.cx.sin.bean.locate.District;
import com.cx.sin.dao.base.DAO;

/**
 * 区
 * @author XuXu
 *
 */
public interface DistrictService extends DAO<District> {
	
	/**
	 * 获取市下面的区
	 * @param uuid_city 市uuid
	 * @return
	 */
	public List<District> queryDistrictByCity(String uuid_city) throws Exception;
	
	/**
	 * 查询区
	 * @param uuid
	 * @return
	 * @throws Exception
	 */
	public District queryDistrictByUuid(String uuid) throws Exception;
	
}
