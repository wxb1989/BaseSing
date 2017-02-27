package com.cx.sin.service.locate.impl;

import java.util.List;

import javax.persistence.Query;

import org.springframework.stereotype.Service;

import com.cx.sin.bean.locate.City;
import com.cx.sin.dao.base.DaoSupport;
import com.cx.sin.service.locate.CityService;

@Service
public class CityServiceImpl extends DaoSupport<City> implements CityService {

	@Override
	public List<City> queryCityByProvince(String uuid_province) throws Exception{
		
		Query query = em.createQuery("select o from City o where o.uuid_province = :uuid_province and o.deleteFlag = :deleteFlag");
		query.setParameter("uuid_province", uuid_province);
		query.setParameter("deleteFlag", false);
		
		return query.getResultList();
	}

	@Override
	public City queryCityByUuid(String uuid) throws Exception {
		Query query = em.createQuery("select o from City o where o.uuid = :uuid and o.deleteFlag = :deleteFlag");
		query.setParameter("uuid", uuid);
		query.setParameter("deleteFlag", false);
		List<City> cityList = query.getResultList();
		
		return cityList.size() == 0 ? null : cityList.get(0);
	}

	@Override
	public List<City> queryCityByHasLecture() throws Exception {
		Query query = em.createQuery("select o from City o where o.hasLecture = :hasLecture and o.deleteFlag = :deleteFlag");
		query.setParameter("hasLecture", 1);
		query.setParameter("deleteFlag", false);
		
		return query.getResultList();
	}

}
