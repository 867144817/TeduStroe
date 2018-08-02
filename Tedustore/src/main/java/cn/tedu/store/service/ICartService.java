package cn.tedu.store.service;

import java.util.List;

import org.apache.ibatis.annotations.Param;

import cn.tedu.store.bean.Cart;
import cn.tedu.store.vo.CartVo;

public interface ICartService {
	void addCart(Cart cart);
	List<CartVo> selectCartByUid(Integer uid);
	Integer deleteByBatch(Integer[] ids);
	void deleteById(Integer id);
	void updateCart(Integer pnum,Integer id);
}
