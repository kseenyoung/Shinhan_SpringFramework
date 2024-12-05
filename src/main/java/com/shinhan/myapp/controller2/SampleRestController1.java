package com.shinhan.myapp.controller2;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.MediaType;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.shinhan.myapp.emp.EmpDTO;
import com.shinhan.myapp.emp.EmpService;

// Spring 3
//@Controller
//@ResponseBody

// Spring 4
@RestController
@RequestMapping("/rest")
public class SampleRestController1 {
	@Autowired
	EmpService empService;

	// 1. 들어온 data 있음, return data는 단순 문자
	@GetMapping(value = "test1.do", produces = "text/plain;charset=UTF-8")
	public String test1() {
		return "rest방식 연습1";
	}

	// 2. 들어온 data 없음, return data
	// {"emplist":[{},{},{}]}
	@GetMapping(value = "emplist.do", produces = "application/json;charset=UTF-8")
	public List<EmpDTO> test2() {
		return empService.selectAllService();
	}

	// 3. URI를 통해 들어온 data 있음, return data
	@GetMapping(value = "empdetail.do/{empid}", produces = MediaType.APPLICATION_JSON_VALUE)
	public EmpDTO test3(@PathVariable int empid) {
		return empService.selectByIdService(empid);
	}

	// 4. 모든 직원 조회, return은 Map<직원번호, 직원DTO>
	// {100:{}, 101:{}, 102:{}}
	@GetMapping(value = "empmap.do", produces = MediaType.APPLICATION_JSON_VALUE)
	public void test4() {
		Map<Integer, EmpDTO> map = new HashMap<>();
		List<EmpDTO> emplist = empService.selectAllService();
		emplist.forEach(emp -> {
			map.put(emp.getEmployee_id(), emp);
		});
	}

	// 5. 입력(Post), 들어오는 data가 있음, 요청문서의 body로 온다. (주의 : @RequestParam 아님!!)
	@PostMapping(value = "empinsert.do", consumes = MediaType.APPLICATION_JSON_VALUE, produces = MediaType.APPLICATION_JSON_VALUE)
	public String test5(@RequestBody EmpDTO emp) {
		int result = empService.insertService(emp);
		return result > 0 ? "insert success" : "insert fail";
	}

	@PutMapping(value = "empupdate.do", consumes = MediaType.APPLICATION_JSON_VALUE)
	public String update(@RequestBody EmpDTO emp) {
		int result = empService.updateService(emp);
		return result > 0 ? "update success" : "update fail";
	}

	@DeleteMapping(value = "empdelete.do/{empid}")
	public String delete(@PathVariable int empid) {
		int result = empService.deleteService(empid);
		return result > 0 ? "delete success" : "delete fail";
	}

}
