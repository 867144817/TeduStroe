package cn.tedu.store.mapper;

import java.util.List;

import cn.tedu.store.bean.Address;

public interface AddressMapper {
	/**
	 * 插入adress信息
	 * @param address
	 */
	void insertAddress(Address address);
	/**
	 * 查询登陆人的收货地址
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
	 *  修改所有用户默认值为0 
	 * @param uid
	 */
	Integer updateCancel(Integer uid);
	/**
	 * 修改用户默认地址为指定的
	 * @param id
	 */
	Integer updateIsDefaultById(Integer id);
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
