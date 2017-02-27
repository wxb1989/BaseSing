package com.cx.sin.service.locate.impl;

import java.util.List;

import javax.persistence.Query;

import org.springframework.stereotype.Service;

import com.cx.sin.bean.locate.Province;
import com.cx.sin.dao.base.DaoSupport;
import com.cx.sin.service.locate.ProvinceService;

@Service
public class ProvinceServiceImpl extends DaoSupport<Province> implements
		ProvinceService {

	@Override
	public List<Province> queryProvinces() throws Exception{
		
		Query query = em.createQuery("select o from Province o where o.deleteFlag = :deleteFlag");
		query.setParameter("deleteFlag", false);
		
		return query.getResultList();
	}

	@Override
	public Province queryProvinceByUuid(String uuid) throws Exception {
		
		Query query = em.createQuery("select o from Province o where o.uuid = :uuid and o.deleteFlag = :deleteFlag");
		query.setParameter("uuid", uuid);
		query.setParameter("deleteFlag", false);
		List<Province> provinceList = query.getResultList();
		
		return provinceList.size() == 0 ? null : provinceList.get(0);
	}

}
