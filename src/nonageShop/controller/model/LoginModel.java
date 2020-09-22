package nonageShop.controller.model;

import java.io.IOException;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import nonageShop.controller.Command;
import nonageShop.dto.Member;
import nonageShop.service.MemberService;

public class LoginModel implements Command{
	private MemberService service = new MemberService();
	@Override
	public String process(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		if (request.getMethod().equalsIgnoreCase("GET")) {
			 System.out.println("get");
			 return "member/login.jsp";
		}else {
			System.out.println("post");
			HttpSession session = request.getSession();
			String id = request.getParameter("id");
			String pwd = request.getParameter("pwd");
			Member m = service.getMember(id);
			
			if(m!=null) {
				if(m.getPwd().equals(pwd)) {
					session.removeAttribute("id");
					session.setAttribute("loginUser", m);
					response.sendRedirect("index.do");
				}
			}else {
				return "member/login_fail.jsp";
			}
		}
		return null;
		
	}
	
}
