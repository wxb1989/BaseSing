package com.cx.sin.web.controller.locate;

import java.util.List;

import org.apache.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;

import com.cx.sin.bean.locate.City;
import com.cx.sin.bean.locate.District;
import com.cx.sin.bean.locate.Street;
import com.cx.sin.service.locate.CityService;
import com.cx.sin.service.locate.DistrictService;
import com.cx.sin.service.locate.ProvinceService;
import com.cx.sin.service.locate.StreetService;

/**
 * 省、市、区、街道 Controller
 * @author XuXu
 *
 */
@Controller
@RequestMapping("/locate")
public class LocateController {
	
	private static final Logger LOGGER = Logger.getLogger(LocateController.class);
	
	@Autowired
	private ProvinceService provinceService;
	@Autowired
	private CityService cityService;
	@Autowired
	private DistrictService districtService;
	@Autowired
	private StreetService streetService;
	
	
	/**
	 * 根据省获取市
	 * @param uuid_province
	 * @return
	 */
	@RequestMapping("/getcity")
	@ResponseBody
	public List<City> getCityJsonByProvince(@RequestParam String uuid_province) {
		List<City> json_city = null;
		
		try {
			json_city = cityService.queryCityByProvince(uuid_province);
		}
		catch (Exception e) {
			e.printStackTrace();
		}
		
		return json_city;
	}
	
	/**
	 * 根据市获取区
	 * @param uuid_city
	 * @return
	 */
	@RequestMapping("/getdistrict")
	@ResponseBody
	public List<District> getDistrictJsonByCity(@RequestParam String uuid_city) {
		List<District> json_district = null;
		
		try {
			json_district = districtService.queryDistrictByCity(uuid_city);
		}
		catch (Exception e) {
			e.printStackTrace();
		}
		
		return json_district;
	}
	
	/**
	 * 根据区获取街道
	 * @param uuid_district
	 * @return
	 */
	@RequestMapping("/getstreet")
	@ResponseBody
	public List<Street> getStreetJsonByDistrict(@RequestParam String uuid_district) {
		List<Street> json_street = null;
		
		try {
			json_street = streetService.queryStreetByDistrict(uuid_district);
		}
		catch (Exception e) {
			e.printStackTrace();
		}
		
		return json_street;
	}
	
	

}
