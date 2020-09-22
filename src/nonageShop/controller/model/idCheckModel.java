package nonageShop.controller.model;

import java.io.IOException;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import nonageShop.controller.Command;
import nonageShop.service.MemberService;

public class idCheckModel implements Command {
	private MemberService service = new MemberService();
	@Override
	public String process(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		String id = request.getParameter("id").trim();
		int message = service.confirmId(id);
		
		request.setAttribute("message", message);
		request.setAttribute("id", id);
		
		return "member/idcheck.jsp";
	}
}
