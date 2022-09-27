//package filter;
//
//import java.io.IOException;
//import java.util.Arrays;
//import java.util.Objects;
//
//import javax.servlet.Filter;
//import javax.servlet.FilterChain;
//import javax.servlet.FilterConfig;
//import javax.servlet.ServletException;
//import javax.servlet.ServletRequest;
//import javax.servlet.ServletResponse;
//import javax.servlet.annotation.WebFilter;
//import javax.servlet.http.HttpServletRequest;
//import javax.servlet.http.HttpSession;
//
//
//@WebFilter(filterName="/LoginFilter", urlPatterns="*")
//public class LoginFilter implements Filter {
//	String[] whiteList = {"/test.jsp" , "/NewLogin.jsp", "/NewSignUp.jsp"};
//    
//	public void doFilter(ServletRequest request, ServletResponse response, FilterChain chain) throws IOException, ServletException {
//		
//		HttpServletRequest req = (HttpServletRequest) request;
//		HttpSession session = req.getSession(false);
////		System.out.println(session);
//		
//		if(!req.getServletPath().equals(whiteList) && Objects.isNull(session)) {
//			req.getRequestDispatcher("/NewLogin.jsp").forward(request, response);
//		}
//		
//		System.out.println(req.getServletPath().equals(whiteList));
//		
//		for(String i : whiteList) {
//			System.out.println(i + "\n");
//		}
//
//		chain.doFilter(request, response);
//	}
//
//
//	public void init(FilterConfig fConfig) throws ServletException {
//	}
//}
