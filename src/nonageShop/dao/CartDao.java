package nonageShop.dao;

import java.util.ArrayList;

import nonageShop.dto.Cart;

public interface CartDao {
	
	int insertCart(Cart cart);
	
	int deleteCart(int no);
	
	ArrayList<Cart> cartListById(String userId);
	
	ArrayList<Cart> cartList();

	int updateCartResult(Cart cart);


}
