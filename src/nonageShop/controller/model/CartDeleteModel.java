package nonageShop.controller.model;

import java.io.IOException;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import nonageShop.controller.Command;
import nonageShop.service.CartService;

public class CartDeleteModel implements Command {
	CartService service = new CartService();

	@Override
	public String process(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		System.out.println("delete오나");
		String[] noArr = request.getParameterValues("no");
		
		for(String no : noArr) {
			//System.out.println("no");
			service.deleteCart(Integer.parseInt(no));
			//System.out.println(res);
		}
		
		response.sendRedirect("cartList.do");
		return null;
	}

}
