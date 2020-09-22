package nonageShop.controller.model;

import java.io.IOException;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import nonageShop.controller.Command;
import nonageShop.dto.Member;
import nonageShop.service.MemberService;

public class JoinModel implements Command {
	private MemberService service = new MemberService();
	@Override
	public String process(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		request.setCharacterEncoding("UTF-8"); 
		
		if (request.getMethod().equalsIgnoreCase("GET")) {
			 System.out.println("get");
			 return "member/join.jsp";
		 }else {
			 System.out.println("post");
			 
			 HttpSession session = request.getSession();
			 Member m = new Member();
			 m.setId(request.getParameter("id"));
			 m.setPwd(request.getParameter("pwd"));
			 m.setName(request.getParameter("name"));
			 m.setEmail(request.getParameter("email"));
			 m.setZipNum(request.getParameter("zipNum"));
			 m.setAddress(request.getParameter("addr1")+request.getParameter("addr2"));
			 m.setPhone(request.getParameter("phone"));
			 
			 session.setAttribute("id", request.getParameter("id"));
			 int res = service.insertMember(m);
			 System.out.println(res);
			 response.sendRedirect("login.do");
		 }
		return null;
	}

}
