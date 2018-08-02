package cn.tedu.store.service;

import java.util.List;

import cn.tedu.store.bean.Address;

public interface IAddressService {
	/**
	 * 保存收货地址
	 * @param address
	 */
	void saveAddress(Address address);
	/**
	 * 遍历以有地址
	 * @param uid
	 * @return
	 */
	List<Address> selectAddressByUid(Integer uid);
	/**
	 * 查询指定收货地址
	 * @param id
	 * @return
	 */
	Address selectAddressById(Integer id);
	/**
	 * 设置默认地址
	 * @param id
	 * @param uid
	 */
	void updateDefault(Integer id,Integer uid);
	/**
	 * 删除收货地址
	 * @param id
	 */
	void deleteAddress(Integer id);
	/**
	 * 修改收获地址
	 * @param address
	 */
	void updateModifyAddress(Address address);
}
