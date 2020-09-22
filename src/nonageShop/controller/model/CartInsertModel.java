package nonageShop.controller.model;

import java.io.IOException;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import nonageShop.controller.Command;
import nonageShop.dto.Cart;
import nonageShop.dto.Member;
import nonageShop.dto.Product;
import nonageShop.service.CartService;

public class CartInsertModel implements Command {
	CartService service = new CartService();
	
	@Override
	public String process(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		
			HttpSession session = request.getSession();
			Member loginUser = (Member) session.getAttribute("loginUser");
			
			
			if(loginUser == null) {
				System.out.println("null");
				return "login.do";
			}else {
				System.out.println("오나ㅏㅏㅏㅏ");
				Cart cart = new Cart();
				Member member = new Member();
				Product product = new Product();
				
				cart.setMember(member);
				cart.setProduct(product);
					
				member.setId(loginUser.getId());
				product.setNo(Integer.parseInt(request.getParameter("no")));
				cart.setQuantity(Integer.parseInt(request.getParameter("quantity")));
				
				
				int res = service.insertCart(cart);
				System.out.println(res);
				
				return "cartList.do";
			
			}
			
		}

}
