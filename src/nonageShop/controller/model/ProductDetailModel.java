package nonageShop.controller.model;

import java.io.IOException;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import nonageShop.controller.Command;
import nonageShop.dto.Product;
import nonageShop.service.ProductService;

public class ProductDetailModel implements Command {
	private ProductService service = new ProductService();
	@Override
	public String process(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		
		request.setCharacterEncoding("UTF-8");
		int no = Integer.parseInt(request.getParameter("no"));
		Product p = service.getProduct(no);
		request.setAttribute("productVO",p);
		
		return "productDetail.jsp";
	}

}
