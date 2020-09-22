package nonageShop.dao;

import java.util.ArrayList;

import nonageShop.dto.Cart;
import nonageShop.dto.Order;

public interface OrderDao {
	
	int insertOrder(ArrayList<Cart> cartList, String id);
	
	int insertOrderDetail(Cart cart, int maxNo);
	
	ArrayList<Order> listByOrderById(String id, String result, int no);
	
	ArrayList<Integer> selectNoOrdering(String id);

}
