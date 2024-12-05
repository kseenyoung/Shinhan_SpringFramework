package com.shinhan.myapp.filter;

import java.io.IOException;

import javax.servlet.Filter;
import javax.servlet.FilterChain;
import javax.servlet.FilterConfig;
import javax.servlet.ServletException;
import javax.servlet.ServletRequest;
import javax.servlet.ServletResponse;
import javax.servlet.annotation.WebFilter;
import javax.servlet.http.HttpServletRequest;

import lombok.extern.slf4j.Slf4j;

@Slf4j
@WebFilter("*.do")  // 3.0이후부터 사용 가능
public class LoginCheckFilter implements Filter {

	public void doFilter(ServletRequest request, ServletResponse response, FilterChain chain) throws IOException, ServletException {
		// 요청 수행 하기 전
		HttpServletRequest req = (HttpServletRequest)request;
		
		// get request address
		String contextPath = req.getServletContext().getContextPath();
		String uri = req.getRequestURI();
//		log.info("요청 주소 : " + uri);
//		log.info("contextPath : " + contextPath);
		
		uri = uri.substring(contextPath.length());
		log.info("요청 주소 : " + uri);
		
//		if(!uri.equals("/auth/login.do")) {
//			// 요청 주소가 login이라면 수행하고, 로그인이 아니면 로그인했는지 확인
//			HttpSession session = req.getSession();
//			MemberDTO member = (MemberDTO) session.getAttribute("loginMember");
//			if(member == null) {
//				log.info("로그인 안 한 사용자");
//				HttpServletResponse res = (HttpServletResponse) response;
//				res.sendRedirect(contextPath + "/auth/login.do");
//				return;
//			}			
//		}
		
		chain.doFilter(request, response);
		
		// 요청 수행 한 후
		
	}

	@Override
	public void init(FilterConfig filterConfig) throws ServletException {
		// TODO Auto-generated method stub
		
	}

	@Override
	public void destroy() {
		// TODO Auto-generated method stub
		
	}
}
