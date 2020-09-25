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
import nonageShop.service.OrderService;

public class OrderDaoTest {
	private static OrderDaoImpl dao;
	private static CartDaoImpl cDao;
	private static OrderService service = new OrderService();
	
	@BeforeClass
	public static void setUpBeforeClass() throws Exception {
		dao = OrderDaoImpl.getInstance();
		cDao = CartDaoImpl.getInstance();
	}	
	


	@Test
	public void testListByOrderById() {
		System.out.println("listByOrderById");
		Order list = dao.listByOrderById("one", "1", 1);
		Assert.assertNotNull(list);
		System.out.println(list);
	}

	//@Test
	public void testSelectNoOrdering() {
		System.out.println("NoOrdering");
		ArrayList<Integer> list = dao.selectNoOrdering("one");
		Assert.assertNotNull(list);
		list.stream().forEach(System.out::println);
	}

}
