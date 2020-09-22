package nonageShop.service;

import java.util.ArrayList;

import nonageShop.dao.impl.CartDaoImpl;
import nonageShop.dto.Cart;

public class CartService {
	private CartDaoImpl dao = CartDaoImpl.getInstance();
	
	public int insertCart(Cart cart) {
		return dao.insertCart(cart);
	}
	
	public int deleteCart(int no) {
		return dao.deleteCart(no);
	}
	
	public ArrayList<Cart> cartListById(String userId){
		return dao.cartListById(userId);
	}
	
	public ArrayList<Cart> cartList(){
		return dao.cartList();
		
	}
}
