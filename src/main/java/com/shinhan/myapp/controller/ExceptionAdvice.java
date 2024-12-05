package com.shinhan.myapp.controller;

import javax.servlet.http.HttpServletRequest;

import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.servlet.NoHandlerFoundException;

import lombok.extern.slf4j.Slf4j;

/*
 * 예외 발생 시 전역적으로 처리하는 controller
 * 500 : 서버 에러
 * 404 : 존재하지 않는 페이지
 * */

@ControllerAdvice
@Slf4j
public class ExceptionAdvice {
	
	@ExceptionHandler(Exception.class)
	public String f1(Exception ex, Model model) {
		log.info("error class : " + ex.getClass().getName());
//		log.info("error messge : " + ex.getStackTrace());
		ex.printStackTrace();
		
		model.addAttribute("message", "콩");
		return "error/error500";
	}
	
	@ExceptionHandler(NoHandlerFoundException.class)
	public String f2(HttpServletRequest request) {
		log.info("request address : " + request.getRequestURI());
		return "error/error404";
	}
}
