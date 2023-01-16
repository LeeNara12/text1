package sec03.ex01;

import java.io.IOException;
import java.text.SimpleDateFormat;
import java.util.Date;

import javax.servlet.Filter;
import javax.servlet.FilterChain;
import javax.servlet.FilterConfig;
import javax.servlet.ServletException;
import javax.servlet.ServletRequest;
import javax.servlet.ServletResponse;
import javax.servlet.annotation.WebFilter;
import javax.servlet.http.HttpFilter;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;


@WebFilter(filterName = "EncoderFilter", urlPatterns = { "/*" })
public class EncoderFiter extends HttpFilter implements Filter {
    
    public EncoderFiter() {
        super();
        
    }


	public void destroy() {
		
	}

	
	public void doFilter(ServletRequest request, ServletResponse response, FilterChain chain) throws IOException, ServletException {
		// TODO Auto-generated method stub
		// place your code here
		System.out.println("dofilter 실행");
		
		//반복해야 하는 작업을 한번에
		request.setCharacterEncoding("utf-8");
		response.setContentType("text/html; charset=utf-8;");
		
		//권한 점검
//		String isLogon = (String)((HttpServletRequest) request).getSession().getAttribute("isLogon");
//		System.out.println("세션의 isLogon : "+isLogon);
		//자세하게
		// request.getSession(); getSession은 부모인 ServletRequest에 없음
		HttpSession session =null;
		if(request instanceof HttpServletRequest) {
			HttpServletRequest req = (HttpServletRequest) request;
			
			StringBuffer sb = req.getRequestURL();
			String url = sb.toString();
			System.out.println("url은 "+url);
			
			if(url.indexOf("/login")!=-1 //-1은 없다, 그외는 있다
					||url.indexOf("/url/test")!=-1) {
				//통과
				
				//개발자 편의를 위한
				long start = System.currentTimeMillis();
				
				
				SimpleDateFormat sdf =  new SimpleDateFormat("yyyy년 MM월 dd일 hh시 mm분 ss.SSS초");
				String now = sdf.format(new Date()); //바로 담을 수 있음
				System.out.println(now);
				//서블릿 동작
				chain.doFilter(request, response);
				long end = System.currentTimeMillis();
				System.out.println("dofilter 끝");
				
				System.out.println("걸린시간[ms] :"+(end-start));
				
				
			}else {
				//세션 점검
				session = req.getSession();
				
				//key값 isLogon이 없으면 null반환
				String loginInfo = (String) session.getAttribute("isLogon");
				if("OK".equals(loginInfo)) {
					chain.doFilter(request, response);
				}else {
//					response.getWriter().println("<div></div>")
					HttpServletResponse resp = (HttpServletResponse)response;
					resp.sendRedirect("/pro10/login.html");
				}
			}
			
			
		
			
			
		}
		
		

		

	}

	
	public void init(FilterConfig fConfig) throws ServletException {
		
	}

}
