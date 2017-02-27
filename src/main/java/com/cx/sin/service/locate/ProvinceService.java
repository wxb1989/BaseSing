package com.cx.sin.service.locate;

import java.util.List;

import com.cx.sin.bean.locate.Province;
import com.cx.sin.dao.base.DAO;

/**
 * 省
 * @author XuXu
 *
 */
public interface ProvinceService extends DAO<Province> {
	
	/**
	 * 查询所有省
	 * @return
	 */
	public List<Province> queryProvinces() throws Exception;
	
	/**
	 * 查询省
	 * @param uuid
	 * @return
	 * @throws Exception
	 */
	public Province queryProvinceByUuid(String uuid) throws Exception;
	
}
