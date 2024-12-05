package com.shinhan.myapp.controller2;

import java.util.Map;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;

//import com.shinhan.myapp.vo.ParamVO;

import lombok.extern.slf4j.Slf4j;

@Controller
@RequestMapping("/friday")
@Slf4j
public class FridayController {

	@RequestMapping(value="/one.do", method = RequestMethod.GET)
	public void f1() {
		
	}
	
	/*
	 * 1. 하나의 input 받기 : String username
	 * 2. VO로 받기 : ParamVO param
	 * 3. map 받기 : @RequestParam Map<String, String> map
	 * */
	
	@GetMapping("/two.do")

	public String f2(
			@RequestParam Map<String, String> map,
//			ParamVO param,
			String username,
			@RequestParam(value="userid", required=false, defaultValue="0") Integer userid2) {
		log.info("userid : " + userid2);
//		log.info("ParamVO : "  + param);
		log.info("map : " + map);
		
		return "redirect:/dept/list.do";  // redirect  (Model에 데이터를 저장해도 해당 페이지에서는 알 수 없다)
//		return "/dept/list.do";  // forward - http://localhost:9999/firstzone/dept/list.do.jsp
	}
	
}
