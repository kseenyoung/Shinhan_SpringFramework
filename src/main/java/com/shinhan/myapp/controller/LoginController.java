package com.shinhan.myapp.controller;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;

import com.shinhan.myapp.model.MemberService;
import com.shinhan.myapp.vo.MemberDTO;

@Controller
@RequestMapping("/auth")
public class LoginController {

	@Autowired
	MemberService mService;
	
	Logger logger = LoggerFactory.getLogger(LoginController.class);
 
	@GetMapping("/login.do")
	public void loginPage() {
		 
	}
	
	@GetMapping("/main.do")
	public void main() {
		
	}
	
	@GetMapping("/logout.do")
	public String logout(HttpSession session) {
		session.invalidate();
		return "redirect:login.do";
	}
	
	@PostMapping("/login.do")
	public String loginPost(String userid, String userpass, 
			HttpServletRequest request,
			HttpSession session) {
	   logger.info( request.getRemoteAddr() + "���� ��û��" );	
		
	   MemberDTO member = mService.loginService(userid, userpass);
	   if(member==null) {
		   logger.info("���̵� �������� ����");
	   }else if(member.getMember_id().equals("-1")) {
		   logger.info("��й�ȣ ����");
	   }else {
		   logger.info(member.toString());
		   //�세션에 저장하고 업무 시작
		   session.setAttribute("loginMember", member);
		   return "redirect:/auth/main.do";
	   }
	   //�ٽ÷α��� 
	   return "redirect:/auth/login.do";
		   
	}
}













