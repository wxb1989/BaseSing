package com.cx.sin.service.locate.impl;

import java.util.List;

import javax.persistence.Query;

import org.springframework.stereotype.Service;

import com.cx.sin.bean.locate.District;
import com.cx.sin.dao.base.DaoSupport;
import com.cx.sin.service.locate.DistrictService;

@Service
public class DistrictServiceImpl extends DaoSupport<District> implements
		DistrictService {

	@Override
	public List<District> queryDistrictByCity(String uuid_city) throws Exception {
		
		Query query = em.createQuery("select o from District o where o.uuid_city = :uuid_city and o.deleteFlag = :deleteFlag");
		query.setParameter("uuid_city", uuid_city);
		query.setParameter("deleteFlag", false);
		
		return query.getResultList();
	}

	@Override
	public District queryDistrictByUuid(String uuid) throws Exception {
		Query query = em.createQuery("select o from District o where o.uuid = :uuid and o.deleteFlag = :deleteFlag");
		query.setParameter("uuid", uuid);
		query.setParameter("deleteFlag", false);
		List<District> districtList = query.getResultList();
		
		return districtList.size() == 0 ? null : districtList.get(0);
	}

}
