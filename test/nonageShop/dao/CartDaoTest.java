package nonageShop.dao;

import java.util.ArrayList;
import java.util.Date;

import org.junit.Assert;
import org.junit.BeforeClass;
import org.junit.Test;


import nonageShop.dao.impl.CartDaoImpl;
import nonageShop.dto.Cart;
import nonageShop.dto.Member;
import nonageShop.dto.Product;

public class CartDaoTest {
	private static CartDaoImpl dao;
	
	@BeforeClass
	public static void setUpBeforeClass() throws Exception {
		dao = CartDaoImpl.getInstance();
	}
	@Test
	public void testInsertCart() {
		System.out.println("insert");
		Date date = new Date();
		Cart c = new Cart(1, new Member("one"), new Product(1), "1", date);
		int res = dao.insertCart(c);
		Assert.assertNotNull(res);
		System.out.println(c);
	}
	
	@Test
	public void testCartListById() {
		System.out.println("listById");
		ArrayList<Cart> list = dao.cartListById("one");
		Assert.assertNotNull(list);
		list.stream().forEach(System.out::println);
	}

	@Test
	public void testDeleteCart() {
		System.out.println("delete");
		int res = dao.deleteCart(8);
		Assert.assertNotNull(res);
		System.out.println("확인");
		ArrayList<Cart> list = dao.cartList();
		Assert.assertNotNull(list);
		list.stream().forEach(System.out::println);
		
	}
	
	@Test
	public void testCartList() {
		System.out.println("AllCartList");
		ArrayList<Cart> list = dao.cartList();
		Assert.assertNotNull(list);
		list.stream().forEach(System.out::println);
	}

	

}
