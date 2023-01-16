package sec03.ex01;

import java.io.IOException;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;


@WebServlet("/url/test")
public class UrlTest extends HttpServlet {


	
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
	
		// http://127.0.0.1:8080/pro10/url/test?num=123&name=abc
		
		//요청 url
		StringBuffer url= request.getRequestURL();
		System.out.println("getReqeustURL은 "+ url);//전체 풀주소
		
		//서버 주소 제외한 주소
		String uri = request.getRequestURI();
		System.out.println("getRequestURI는 "+ uri);// /pro10/url/test
		
		//어플리케이션(Context)경로
		String ctxPath = request.getContextPath();
		System.out.println("getContextPath는 "+ctxPath); // /pro10
		
		//서블릿 경로
		String srvPath = request.getServletPath();
		System.out.println("getServletPath는 "+ srvPath);// /url/test
		
		//요청 파라메타(QueryString)
		String qs = request.getQueryString();
		System.out.println("getQueryString은 "+ qs);// num=123&name=abc
		
		
		
		
		
	}

}
