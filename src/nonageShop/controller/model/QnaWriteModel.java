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

public class QnaWriteModel implements Command{
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
			
			Qna qna = new Qna();
			qna.setId(loginUser.getId());
			qna.setSubject(request.getParameter("subject"));
			qna.setContent(request.getParameter("content"));
			
			service.insertQna(qna);
			response.sendRedirect("qnaList.do");
		}
		return null;
		
		
	}

}
