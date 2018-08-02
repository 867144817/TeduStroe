package cn.tedu.store.mapper;

import java.util.List;

import org.apache.ibatis.annotations.Param;

import cn.tedu.store.bean.Area;
import cn.tedu.store.bean.City;
import cn.tedu.store.bean.Province;

public interface DictMapper {
	/**
	 * 查询省信息
	 * @return
	 */
	List<Province> selectProvince();
	/**
	 * 查询城市信息
	 * @param cityCode
	 * @return
	 */
	List<Area> selectArea(@Param("cityCode") String cityCode);
	/**
	 * 查询地区信息
	 * @param provinceCode
	 * @return
	 */
	List<City> selectCity(@Param("provinceCode") String provinceCode);
	/**
	 * 根据省id查询省名称
	 * @param provinceCode
	 * @return
	 */
	String selectProvinceNameByCode(String provinceCode);
	/**
	 * 根据市id查询市名称
	 * @param cityCode
	 * @return
	 */
	String selectCityNameByCode(String cityCode);
	/**
	 * 根据区id查询区名称
	 * @param areaCode
	 * @return
	 */
	String selectAreaNameByCode(String areaCode);
	
}
