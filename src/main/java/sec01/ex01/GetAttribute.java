package sec01.ex01;

import java.io.IOException;
import java.io.PrintWriter;

import javax.servlet.ServletContext;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

@WebServlet("/get")
public class GetAttribute extends HttpServlet {
	
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		System.out.println("get 생성");
		request.setCharacterEncoding("utf-8");
		response.setContentType("text/html; charset=utf-8");
		
		PrintWriter out = response.getWriter();
		ServletContext ctx = getServletContext();
		HttpSession sess = request.getSession();
		
		String ctxMsg = (String) ctx.getAttribute("context"); 
		String sesMsg = (String) sess.getAttribute("session"); 
		String reqMsg = (String) request.getAttribute("request"); 
		
		out.print("context값은 "+ ctxMsg+"<br>");
		out.print("session값은 "+ sesMsg+"<br>");
		out.print("request값은 "+ reqMsg+"<br>");
		
		
	}

}
