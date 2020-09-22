package nonageShop.controller.model;

import java.io.IOException;
import java.util.ArrayList;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import nonageShop.controller.Command;
import nonageShop.dto.Product;
import nonageShop.service.ProductService;

public class ProductKindModel implements Command {
	private ProductService service = new ProductService();
	
	@Override
	public String process(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		
		request.setCharacterEncoding("UTF-8");
		System.out.println("오나");
		String kind = request.getParameter("kind").trim();
		ArrayList<Product> list = service.listKindProduct(kind);
		request.setAttribute("productKindList", list);
		
		return "product/productKind.jsp";
	}

}
