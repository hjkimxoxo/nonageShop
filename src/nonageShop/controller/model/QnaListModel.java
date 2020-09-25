package nonageShop.controller.model;

import java.io.IOException;
import java.util.ArrayList;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import nonageShop.controller.Command;
import nonageShop.dto.Member;
import nonageShop.dto.Qna;
import nonageShop.service.QnaService;

public class QnaListModel implements Command {
	private QnaService service = new QnaService();

	@Override
	public String process(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		
		HttpSession session = request.getSession();
		Member loginUser = (Member) session.getAttribute("loginUser");
		
		if(loginUser == null) {
			return "login.do";
		}else {
			ArrayList<Qna> list = service.listQna();
			request.setAttribute("qnaList", list);
		} 

		return "qna/qnaList.jsp";
	}

}
