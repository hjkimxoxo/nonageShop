package nonageShop.controller.model;

import java.io.IOException;
import java.util.ArrayList;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import nonageShop.controller.Command;
import nonageShop.dto.Member;
import nonageShop.dto.Order;
import nonageShop.dto.OrderDetail;
import nonageShop.service.OrderService;

public class OrderListModel implements Command {
	private OrderService service = new OrderService();
	
	@Override
	public String process(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		
		
		HttpSession session = request.getSession();
		Member loginUser = (Member) session.getAttribute("loginUser");
		
		if (loginUser == null) {
			return "login.do";
		}else {

			ArrayList<Integer> noList = service.selectNoOrdering(loginUser.getId());
			ArrayList<Order> orderList = new ArrayList<Order>();

			for(int orderNo:noList) {
				orderList.add(service.listByOrderById(loginUser.getId(), "1", orderNo));
			}
			
			//System.out.println(orderList);
			
			ArrayList<OrderDetail> detailList = service.listOrderDetailById(loginUser.getId());
			
			
			
			request.setAttribute("title", "진행중인 주문 내역");
			request.setAttribute("orderList", orderList);
			request.setAttribute("detail", detailList);
			request.setAttribute("size", detailList.size());
			
		}
		return "mypage/orderList.jsp";
	}

}
