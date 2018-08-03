package cn.tedu.store.mapper;

import java.util.List;

import org.apache.ibatis.annotations.Param;

import cn.tedu.store.bean.Cart;
import cn.tedu.store.vo.CartVo;

public interface CartMapper {
	void insertCart(Cart cart);
	List<CartVo> selectCartByUid(Integer uid);
	Integer deleteByBatch(Integer[] ids);
	void deleteById(Integer id);
	void updateCart(@Param("num")Integer num,@Param("id")Integer id);
	List<CartVo> selectBalance(Integer[] ids);
}
