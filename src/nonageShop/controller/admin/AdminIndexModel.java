package nonageShop.controller.admin;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import nonageShop.controller.Command;

public class AdminIndexModel implements Command {

	@Override
	public String process(HttpServletRequest request, HttpServletResponse response) {
			
		return "admin/main.jsp";
	}

}
