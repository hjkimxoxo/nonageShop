package nonageShop.controller.model;

import java.io.IOException;
import java.util.ArrayList;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import nonageShop.controller.Command;
import nonageShop.dto.Address;
import nonageShop.service.AddressService;

public class FindAddressModel implements Command{
	private AddressService service = new AddressService();

	@Override
	public String process(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		request.setCharacterEncoding("UTF-8"); 
		//if (request.getMethod().equalsIgnoreCase("GET")) {
			 //System.out.println("get");
			 //response.sendRedirect("member/findZipNum.jsp");
		//}else {
			//System.out.println("post");
		
			String dong = request.getParameter("dong");
			if(dong!=null && dong.trim().equals("")==false) {
				ArrayList<Address> list = service.selectAddressByDong(dong.trim());
				request.setAttribute("addressList", list);
			}
			return "member/findZipNum.jsp";
		//}
		//return null;
	}
}



