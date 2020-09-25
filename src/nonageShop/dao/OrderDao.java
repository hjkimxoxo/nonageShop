package nonageShop.dao;

import java.util.ArrayList;

import nonageShop.dto.Cart;
import nonageShop.dto.Order;
import nonageShop.dto.OrderDetail;

public interface OrderDao {
	
	Order listByOrderById(String id, String result, int no);
	
	ArrayList<Integer> selectNoOrdering(String id);
	
	ArrayList<OrderDetail> listOrderDetailById(String id);

}
