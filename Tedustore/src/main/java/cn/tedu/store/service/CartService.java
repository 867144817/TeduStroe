package cn.tedu.store.service;

import java.util.List;

import javax.annotation.Resource;
import javax.management.RuntimeErrorException;

import org.springframework.stereotype.Service;

import cn.tedu.store.bean.Cart;
import cn.tedu.store.mapper.CartMapper;
import cn.tedu.store.vo.CartVo;
@Service
public class CartService implements ICartService{
	@Resource
	private CartMapper cartMapper;
	public void addCart(Cart cart) {
		cartMapper.insertCart(cart);
	}

	public List<CartVo> selectCartByUid(Integer uid) {
		
		return cartMapper.selectCartByUid(uid);
	}

	public Integer deleteByBatch(Integer[] ids) {
		Integer count = cartMapper.deleteByBatch(ids);
		if(count==0) {
			throw new RuntimeException("删除失败");
		}
		return count;
	}

	public void deleteById(Integer id) {
		cartMapper.deleteById(id);
	}

	public void updateCart(Integer pnum, Integer id) {
		cartMapper.updateCart(pnum, id);
	}

}
