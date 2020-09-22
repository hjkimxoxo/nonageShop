package nonageShop.dao;

import static org.junit.Assert.fail;

import java.util.ArrayList;
import java.util.Date;

import org.junit.Assert;
import org.junit.BeforeClass;
import org.junit.Test;

import nonageShop.dao.impl.CartDaoImpl;
import nonageShop.dao.impl.OrderDaoImpl;
import nonageShop.dto.Cart;
import nonageShop.dto.Member;
import nonageShop.dto.Order;
import nonageShop.dto.OrderDetail;
import nonageShop.dto.Product;

public class OrderDaoTest {
	private static OrderDaoImpl dao;
	private static CartDaoImpl cDao;
	
	@BeforeClass
	public static void setUpBeforeClass() throws Exception {
		dao = OrderDaoImpl.getInstance();
		cDao = CartDaoImpl.getInstance();
	}	
	//@Test
	public void testInsertOrder() {
		System.out.println("insert");
		
		ArrayList<Cart> cartList = cDao.cartList();
		Assert.assertNotNull(cartList);
		cartList.stream().forEach(System.out::println);
		
		int res = dao.insertOrder(cartList, "one");
		Assert.assertNotNull(res);
		System.out.println(res);
		
		
	}

	//@Test
	public void testInsertOrderDetail() {
		System.out.println("insertOrderDetail");
		Cart cart = new Cart(63);
		int res = dao.insertOrderDetail(cart, 5);
		Assert.assertNotNull(res);
		System.out.println(res);
	}

	@Test
	public void testListByOrderById() {
		ArrayList<Order> list = dao.listByOrderById("one", "1", 1);
		Assert.assertNotNull(list);
		list.stream().forEach(System.out::println);
	}

	@Test
	public void testSelectNoOrdering() {
		fail("Not yet implemented");
	}

}
