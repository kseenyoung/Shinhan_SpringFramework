package com.shinhan.myapp;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.servlet.ModelAndView;

import com.shinhan.myapp.model.DeptService;

@Controller  //��û���� ������return �Ǵ� �ٸ���û���� ���û 
public class SampleController {
 
	@Autowired
	DeptService dService;
	
	
	
	
	//1.��û�ּҿ� �������� ��ġ�� ������ �������̸��� setting�����ʾƵ��� 
	@GetMapping("/jsptest/test2.do")
	public void f4(Model model) {
		 model.addAttribute("dept", dService.selectByIdService(90));
	}
	
	//2.ModelAndView ���� ( Model + View)
	@RequestMapping(value = "/test3.do", method = RequestMethod.GET)
	public ModelAndView f3() {
		ModelAndView mv = new ModelAndView();
		mv.addObject("dept", dService.selectByIdService(80));
		mv.addObject("dept2", dService.selectByIdService(90));
		mv.setViewName("jsptest/test2");
		return mv;
	}
	
	//3.View�̸��� return 
	@RequestMapping(value = "/test2.do", method = RequestMethod.GET)
	public String f2(Model model) {
		model.addAttribute("dept", dService.selectByIdService(60));
		return "jsptest/test2";
	}
	
	
	@RequestMapping("/test1.do")
	public String f1(Model dataStore) {
		dataStore.addAttribute("myname", "jin");
		dataStore.addAttribute("score", 99);
		return "jsptest/test1";
	}
}
