package nonageShop.controller.admin;

import java.io.IOException;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import nonageShop.controller.Command;
import nonageShop.service.WorkerService;

public class AdminLoginModel implements Command {
	private WorkerService service = new WorkerService();

	@Override
	public String process(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		String id = request.getParameter("id").trim();
		String pwd = request.getParameter("pwd").trim();
		
		int result = service.adminCheck(id, pwd);
		String msg = "";
		
		if(result == 1) { //성공
			HttpSession session = request.getSession();
			session.setAttribute("id", id);
			return "admin/product/productList.jsp";
		}else if(result == 0) {
			request.setAttribute("message", "비밀번호를 확인하세요.");
		}else if(result == -1) {
			request.setAttribute("message", "아이디를 확인하세요.");
		}
		
		return "admin/main.jsp";
	}

}
