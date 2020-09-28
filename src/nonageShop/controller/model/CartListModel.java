package nonageShop.controller.model;

import java.io.IOException;
import java.util.ArrayList;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import nonageShop.controller.Command;
import nonageShop.dto.Cart;
import nonageShop.dto.Member;
import nonageShop.service.CartService;

public class CartListModel implements Command{
	CartService service = new CartService();
	
	@Override
	public String process(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		
			HttpSession session = request.getSession();
			Member loginUser = (Member) session.getAttribute("loginUser");
			
			if(loginUser == null) {
				return "login.do";
			}else {
				ArrayList<Cart> cartList = service.cartListById(loginUser.getId());
				int totalPrice = 0;
				for(Cart cart: cartList) {
					totalPrice += cart.getProduct().getSalePrice() * cart.getQuantity();
				}
				
				
				request.setAttribute("cartList", cartList);
				request.setAttribute("totalPrice", totalPrice);
				
			}
			return "mypage/cartList.jsp";
		
		
	}

}
