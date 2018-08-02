package cn.tedu.store.service;

import java.util.List;

import cn.tedu.store.bean.Area;
import cn.tedu.store.bean.City;
import cn.tedu.store.bean.Province;

public interface IDictService {
	/**
	 * 查询省信息
	 * @return
	 */
	List<Province> getProvince();
	/**
	 * 查询城市信息
	 * @param cityCode
	 * @return
	 */
	List<Area> getArea(String cityCode);
	/**
	 * 查询地区信息
	 * @param provinceCode
	 * @return
	 */
	List<City> getCity(String provinceCode);
	
}
