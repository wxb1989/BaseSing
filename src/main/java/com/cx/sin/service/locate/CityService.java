package com.cx.sin.service.locate;

import java.util.List;

import com.cx.sin.bean.locate.City;
import com.cx.sin.dao.base.DAO;

/**
 * 市
 * @author XuXu
 *
 */
public interface CityService extends DAO<City> {
	
	/**
	 * 获取省下面的市
	 * @param uuid_province 省uuid
	 * @return
	 */
	public List<City> queryCityByProvince(String uuid_province) throws Exception;
	
	/**
	 * 查询市
	 * @param uuid
	 * @return
	 * @throws Exception
	 */
	public City queryCityByUuid(String uuid) throws Exception;
	
	/**
	 * 获取所有开通线上讲座申请的城市
	 * @return
	 * @throws Exception
	 */
	public List<City> queryCityByHasLecture() throws Exception;
	
}
