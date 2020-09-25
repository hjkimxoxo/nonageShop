package nonageShop.controller.model;

import java.io.IOException;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import nonageShop.controller.Command;
import nonageShop.dto.Member;
import nonageShop.dto.Order;
import nonageShop.dto.OrderDetail;
import nonageShop.service.OrderService;

public class OrderDetailModel implements Command{
	private OrderService service = new OrderService();

	@Override
	public String process(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		
		HttpSession session = request.getSession();
		Member loginUser = (Member) session.getAttribute("loginUser");
		
		if (loginUser == null) {
			return "login.do";
		}else {
			int OrderNo = Integer.parseInt(request.getParameter("no"));
			System.out.println(OrderNo);
			Order orderList = service.listByOrderById(loginUser.getId(), "1", OrderNo);
			//System.out.println(orderList);
			//여까진됨
			int totalPrice = 0;
			for(OrderDetail detail: orderList.getDetails()) {
				totalPrice += detail.getCart().getProduct().getSalePrice() * detail.getCart().getQuantity();
			}
			
			
			request.setAttribute("orderDetail", orderList.getDetails().get(0));
			request.setAttribute("orderList", orderList);
			request.setAttribute("totalPrice", totalPrice);
		}

		return "mypage/orderDetail.jsp";
	}

}
