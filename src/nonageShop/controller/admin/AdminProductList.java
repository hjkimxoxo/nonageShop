package nonageShop.controller.admin;

import java.io.IOException;
import java.util.ArrayList;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import nonageShop.controller.Command;
import nonageShop.dto.Product;
import nonageShop.service.ProductService;

public class AdminProductList implements Command{
	private ProductService service = new ProductService();
	@Override
	public String process(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		
		String key = request.getParameter("key");
		key = (key==null) ? "" : key;
		
		String tpage = request.getParameter("tpage");
		tpage = (tpage == null || tpage.equals("1")) ? "1" : tpage;

		request.setAttribute("key", key);
		request.setAttribute("tpage", tpage);
		
		System.out.println("key: " + key + "tpage: " + tpage);
		ArrayList<Product> productList = service.listProduct(Integer.parseInt(tpage), key);

		String paging = service.pageNumber(Integer.parseInt(tpage), key);

		request.setAttribute("productList", productList);
		int n = productList.size();
		request.setAttribute("productListSize", n);
		request.setAttribute("paging", paging);

			
		return "admin/product/productList.jsp";
	}

}
