package nonageShop.controller.model;

import java.io.IOException;
import java.io.PrintWriter;
import java.util.Arrays;
import java.util.List;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.google.gson.Gson;
import com.google.gson.reflect.TypeToken;

import nonageShop.dto.Kind;
import nonageShop.service.KindService;
 
@WebServlet("/menu.do")
public class MenuModel extends HttpServlet {
	private static final long serialVersionUID = 1L;
	private KindService service;

	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		process(request, response);
	}

	
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		process(request, response);
	}


	private void process(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		
		request.setCharacterEncoding("UTF-8"); 
		if (request.getMethod().equalsIgnoreCase("GET")) {
			 System.out.println("get");
		 }else {
			System.out.println("post");
			Gson gson = new Gson();
			Kind[] menu = {new Kind(1, "Heels"), (new Kind(2, "Boots")), new Kind(3, "Sandals"), new Kind(4, "Sneakers"), new Kind(5, "On sales")};
			
			List<Kind> list = Arrays.asList(menu);
			String result  = gson.toJson(list, new TypeToken<List<Kind>>(){}.getType());
			//System.out.println(result);
			
			response.setContentType("application/json");
			response.setStatus(HttpServletResponse.SC_ACCEPTED);
			
			PrintWriter pw = response.getWriter();
			pw.print(result);
			pw.flush();
		 }
	}
}
