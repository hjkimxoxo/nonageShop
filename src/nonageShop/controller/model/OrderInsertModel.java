package nonageShop.controller.model;

import java.io.IOException;
import java.util.ArrayList;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import nonageShop.controller.Command;
import nonageShop.dto.Cart;
import nonageShop.dto.Member;
import nonageShop.dto.Order;
import nonageShop.dto.OrderDetail;
import nonageShop.service.CartService;
import nonageShop.service.OrderService;

public class OrderInsertModel implements Command {
	private OrderService oService = new OrderService();
	private CartService cService = new CartService();

	@Override
	public String process(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		HttpSession session = request.getSession();
		Member loginUser = (Member) session.getAttribute("loginUser");

		if (loginUser == null) {
			return "login.do";
		} else {
			Order order = getOrder(loginUser);
			System.out.println(order);
			
			int maxNo = oService.addOrderAndDetail(order);
			return "orderList.do?no=" + maxNo;
		}
	}

	private Order getOrder(Member member) {
		ArrayList<OrderDetail> details = new ArrayList<OrderDetail>();
		for (Cart c : cService.cartListById(member.getId())) {
			details.add(new OrderDetail(c));
		}
		Order order = new Order();
		order.setDetails(details);
		order.setMember(member);
		return order;
	}

}
