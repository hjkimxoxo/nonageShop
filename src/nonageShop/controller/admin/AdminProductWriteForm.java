package nonageShop.controller.admin;

import java.io.IOException;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import nonageShop.controller.Command;

public class AdminProductWriteForm implements Command {

	@Override
	public String process(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		String kindList[] = {"Heels", "Boots", "Sandals", "Slipers", "Sneakers", "Sale"};
		request.setAttribute("kindList", kindList);
		return "admin/product/productWrite.jsp";
	}
	

}
