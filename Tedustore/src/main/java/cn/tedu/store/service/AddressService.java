package cn.tedu.store.service;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;

import javax.annotation.Resource;

import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import cn.tedu.store.bean.Address;
import cn.tedu.store.bean.User;
import cn.tedu.store.mapper.AddressMapper;
import cn.tedu.store.mapper.UserMapper;
import cn.tedu.store.service.ex.AddressNotFoundException;
import cn.tedu.store.service.ex.UserNotFoundException;
//@Transactional表示当前类的所有方法通过事物处理
//@Transactional表示当方法出现运行时异常的时候，自动回滚
@Service
@Transactional
public class AddressService implements IAddressService{
	@Resource
	private AddressMapper addressMapper;
	@Resource
	private UserMapper userMapper;
	@Resource
	private IDictService dictService;
	/*
	*	 保存用户地址
	*/
	public void saveAddress(Address address) {
		User user1 = userMapper.selectUserById(address.getUid());
		if (user1 == null) {
			throw new UserNotFoundException("用户不存在");
		} else {
			if(address.getCreatedUser()==null) {
				address.setCreatedTime(new Date());
				address.setCreatedUser(user1.getUsername());
			}else {
				address.setModifiedTime(new Date());
				address.setModifiedUser(user1.getUsername());
			}
			List<Address> addresslist = selectAddressByUid(address.getUid(),null);
			address.setIsDefault("1");
			for (Address address2 : addresslist) {
				if(address2.getIsDefault().equals("1")) {
					address.setIsDefault("0");
					break;
				}
			}
			addressMapper.insertAddress(address);
		}
		
	}
	/*
	 * 获取用户收获地址
	 */
	public List<Address> selectAddressByUid(Integer uid,String str) {
		List<Address> list = addressMapper.selectAddressByUid(uid);
		List<Address> list2 = new ArrayList<Address>();
		if("1".equals(str)){
			for (Address address : list) {
				String province = dictService.selectProvinceNameByCode(address.getRecvProvinceCode());
				String city = dictService.selectCityNameByCode(address.getRecvCityCode());
				String area = dictService.selectAreaNameByCode(address.getRecvAreaCode());
				address.setRecvProvinceCode(province);
				address.setRecvCityCode(city);
				address.setRecvAreaCode(area);
				list2.add(address);
			}
		}else{
			list2=list;
		}
		
		return list2;
	}
	/*
	 * 修改默认地址
	 */
	public void updateDefault(Integer id, Integer uid) {
		User user1 = userMapper.selectUserById(uid);
		if (user1 == null) {
			
			throw new UserNotFoundException("用户不存在");
		} else {
//			System.out.println("清楚所有默认");
			Integer n1 = addressMapper.updateCancel(uid);
			if(n1==0) {
				throw new RuntimeException("修改失败");
			}
//			System.out.println("设置默认");
			Integer n2 = addressMapper.updateIsDefaultById(id);
			if(n2==0) {
				throw new RuntimeException("设置失败");
			}
		}
	}
	/*
	 * 删除默认地址
	 */
	public void deleteAddress(Integer id) {
		addressMapper.deleteAddress(id);
	}
	/*
	 *	查询指定收获地址
	 */
	public Address selectAddressById(Integer id) {
//		addressMapper.selectAddressByUid(uid)
		return addressMapper.selectAddressById(id);
	}
	/*
	 * 修改收获地址信息
	 */
	public void updateModifyAddress(Address address) {
		Address address2 = selectAddressById(address.getId());
		if(address2==null) {
			throw new AddressNotFoundException("修改失败，地址未找到");
		}else {
			address.setModifiedUser(address.getRecvUsername());
			address.setModifiedTime(new Date());
			addressMapper.updateModifyAddress(address);
		}
	}
	
	

}
