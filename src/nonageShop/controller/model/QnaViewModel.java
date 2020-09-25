package nonageShop.controller.model;

import java.io.IOException;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import nonageShop.controller.Command;
import nonageShop.dto.Member;
import nonageShop.dto.Qna;
import nonageShop.service.QnaService;

public class QnaViewModel implements Command {
	private QnaService service = new QnaService();

	@Override
	public String process(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		
		request.setCharacterEncoding("UTF-8"); 
		HttpSession session = request.getSession();
		Member loginUser = (Member) session.getAttribute("loginUser");
		
		if(loginUser == null) {
			return "login.do";
		}else {
			int no = Integer.parseInt(request.getParameter("no"));
			System.out.println(no);
			Qna qna = service.getQnaByNo(no);
			System.out.println(qna);
			request.setAttribute("qna", qna);
		}
		return "qna/qnaView.jsp";
	}

}
