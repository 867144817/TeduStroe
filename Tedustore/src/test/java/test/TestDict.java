package test;

import java.util.List;

import org.junit.Before;
import org.junit.Test;
import org.springframework.context.support.ClassPathXmlApplicationContext;

import cn.tedu.store.bean.Cart;
import cn.tedu.store.bean.Goods;
import cn.tedu.store.bean.GoodsCategory;
import cn.tedu.store.mapper.CartMapper;
import cn.tedu.store.mapper.GoodsCategoryMapper;
import cn.tedu.store.mapper.GoodsMapper;
import cn.tedu.store.service.IAddressService;
import cn.tedu.store.vo.CartVo;

public class TestDict {
	ClassPathXmlApplicationContext ctx;
	@Before
	public void init() {
		ctx = new ClassPathXmlApplicationContext("application-dao.xml","application-service.xml");
	}
	@Test
	public void province() {
//		DictMapper dm = ctx.getBean("dictMapper",DictMapper.class);
////		System.out.println(dm);
//		System.out.println(dm.selectProvinceNameByCode("110000"));
//		System.out.println(dm.selectCityNameByCode("110200"));
		IAddressService dm = ctx.getBean("addressService",IAddressService.class);
//		Address address = new Address();
//		address.setId(5);
//		address.setRecvDistrict("天王盖地虎");
//		dm.updateModifyAddress(address);
		dm.updateDefault(8, 7);
	}
	@Test
	public void select() {
		GoodsCategoryMapper gm = ctx.getBean("goodsCategoryMapper",GoodsCategoryMapper.class);
		List<GoodsCategory> list = gm.selectCategoryByParentId(161, 0, 3);
		for (GoodsCategory cc : list) {
//			System.out.println(cc);
			List<GoodsCategory> list2 = gm.selectCategoryByParentId(cc.getId(), 0, 6);
			System.out.println(list2);
			
		}
//		System.out.println(list);
	}
	@Test
	public void select2() {
		GoodsMapper gm = ctx.getBean("goodsMapper",GoodsMapper.class);
		List<Goods> list = gm.selectGoodsByCategoryId(163, 0, 3);
		for (Goods goods : list) {
			System.out.println(goods);
		}
//		System.out.println(list);
	}
	
	@Test
	public void insert() {
		CartMapper gm = ctx.getBean("cartMapper",CartMapper.class);
		Cart cart = new Cart();
		cart.setUid(7);
		cart.setGoodsId("10000009");
		cart.setNum(1);
		gm.insertCart(cart);
		System.out.println(cart);
		
	}
	@Test
	public void chaxun() {
		CartMapper gm = ctx.getBean("cartMapper",CartMapper.class);
	
		List<CartVo> cartList = gm.selectCartByUid(7);
		System.out.println(cartList);
	}
	@Test
	public void shanchu() {
		CartMapper gm = ctx.getBean("cartMapper",CartMapper.class);
		
		gm.deleteByBatch(new Integer[] {7,8});
		List<CartVo> cartList = gm.selectCartByUid(7);
		System.out.println(cartList);
	}
}
