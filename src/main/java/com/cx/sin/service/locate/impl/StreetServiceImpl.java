package com.cx.sin.service.locate.impl;

import java.util.List;

import javax.persistence.Query;

import org.springframework.stereotype.Service;

import com.cx.sin.bean.locate.Street;
import com.cx.sin.dao.base.DaoSupport;
import com.cx.sin.service.locate.StreetService;

@Service
public class StreetServiceImpl extends DaoSupport<Street> implements
		StreetService {

	@Override
	public List<Street> queryStreetByDistrict(String uuid_district)
			throws Exception {
		
		Query query = em.createQuery("select o from Street o where o.uuid_district = :uuid_district and o.deleteFlag = :deleteFlag");
		query.setParameter("uuid_district", uuid_district);
		query.setParameter("deleteFlag", false);
		
		return query.getResultList();
	}

	@Override
	public Street queryStreetByUuid(String uuid) throws Exception {
		Query query = em.createQuery("select o from Street o where o.uuid = :uuid and o.deleteFlag = :deleteFlag");
		query.setParameter("uuid", uuid);
		query.setParameter("deleteFlag", false);
		List<Street> streetList = query.getResultList();
		
		return streetList.size() == 0 ? null : streetList.get(0);
	}

}
