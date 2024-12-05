package com.shinhan.myapp.controller;

import java.util.Arrays;
import java.util.List;
import java.util.Map;

import javax.servlet.http.HttpServletRequest;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;
import org.springframework.web.servlet.support.RequestContextUtils;

import com.shinhan.myapp.emp.EmpDTO;
import com.shinhan.myapp.emp.EmpService;
import com.shinhan.myapp.emp.JobDTO;
import com.shinhan.myapp.model.DeptService;
import com.shinhan.myapp.model.transaction.AccountService;
import com.shinhan.myapp.vo.DeptDTO;

import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;

@Controller
@RequestMapping("/emp")
@RequiredArgsConstructor // final 변수에 DI
@Slf4j
public class EmpController {

//	@Autowired
//	EmpService empService;
//	
//	@Autowired
//	DeptService deptService;

	final EmpService empService;
	final DeptService deptService;
	final AccountService accountService;
	
	@ResponseBody // 응답 문서 만들어 data를 보낼 때 사용함 == response.getWriter.append()
	@GetMapping(value="transaction.do", produces = "text/plain;chatset=utf-8")
	public String transfer() {
		accountService.transferService();
		
		return "이체 서비스 완료 (하지만 장담하지 못하는)";
	}
	
	@GetMapping("/listByArray.do")
	public String listByArray(
			@RequestParam(value = "deptArr[]") Integer[] arr,  // array일 때는 @RequestParam 필수
			Model model
			) {
		log.info(Arrays.toString(arr));  // [10, 20, 30]
		model.addAttribute("empData", empService.selectByArray(Arrays.asList(arr)));
		
		return "emp/empListTable";
	}
	
	@GetMapping("listBySalary.do")
	public String listBySalary(double salary, Model model) {
		log.info("salary : " + salary);
		model.addAttribute("empData", empService.selectBySalary(salary));

		return "emp/empListTable";
	}
	
	@GetMapping("listByJob.do")
	public String listByJob(String job_id, Model model) {
		log.info("job_id : " + job_id);
		model.addAttribute("empData", empService.selectByJob(job_id));

		return "emp/empListTable";
	}
	
	@GetMapping("listByDept.do")
	public String listByDept(int department_id, Model model) {
		log.info("department_id : " + department_id);
		model.addAttribute("empData", empService.selectByDept(department_id));

		return "emp/empListTable";
	}
	
	@GetMapping("listByJobJoin.do")
	public String listByJobJoin(String job_id, Model model) {
		log.info("job_id : " + job_id);
		model.addAttribute("empData", empService.selectByJobJoin(job_id));

		return "emp/empListTable2";
	}
	
	@GetMapping("listByJobJoin2.do")
	public String listByJobJoin2(String job_id, Model model) {
		log.info("job_id : " + job_id);
		log.info("listByJobJoin2 건수: " + empService.selectByJobJoin2(job_id).size());
		model.addAttribute("empData", empService.selectByJobJoin2(job_id));

		return "emp/empListTable2";
	}

	@GetMapping("/list.do")
	public String f1(Model model, HttpServletRequest request) {

		Map<String, ?> map = RequestContextUtils.getInputFlashMap(request);
		if(map!=null) {
			model.addAttribute("resultMessage", map.get("resultMessage"));
			log.info("받은 message : " +  map.get("resultMessage"));
		}
		
		List<DeptDTO> departmentlist = deptService.selectAllService();
		List<JobDTO> joblist = empService.selectAllJobService();

		model.addAttribute("departmentlist", departmentlist);
		model.addAttribute("joblist", joblist);

		return "emp/empList";
	}

	@GetMapping("/list2.do")
//	public String selectCondition(int department_id, String job_id, int salary, Date hire_date, String chkDate) {
	public String selectCondition(Model model, @RequestParam Map<String, Object> map) {

		log.info(map.toString());
		String chkDate = (String) map.get("chkDate");
		if (chkDate.equals("true"))
			map.put("hire_date", "1900-01-01");

		String departmentId = (String) map.get("department_id");
		if (departmentId == null) {
			map.put("department_id", "-1");
		}
		List<EmpDTO> emplist = empService.selectByCondition(map);
		log.info("emplist : " + emplist);
		model.addAttribute("empData", emplist);

		return "emp/empListTable";
	}

	@GetMapping("/insert.do")
	public String insertGet(Model model) {
		
		List<JobDTO> joblist = empService.selectAllJobService();
		List<EmpDTO> managerlist = empService.selectAllService();
		List<DeptDTO> departmentlist = deptService.selectAllService();
		
		log.info("모든 직업 : " + joblist.size());

		model.addAttribute("joblist", joblist);
		model.addAttribute("managerlist", managerlist);
		model.addAttribute("departmentlist", departmentlist);

		return "emp/empInsert";
	}

	@PostMapping("/insert.do")
	public String insertPost(EmpDTO emp, RedirectAttributes attr) {
		log.info("삽입 emp" + emp);
		int result = empService.insertService(emp);
		log.info("삽입 개수 : " + result);

		attr.addFlashAttribute("resultMessage", result > 0 ? "삽입 성공" : "삽입 실패");

		return "redirect:list.do";
	}

	@GetMapping("/detail.do")
	public String detailGet(int emp_id, Model model) {
		EmpDTO emp = empService.selectByIdService(emp_id);
		List<EmpDTO> managerlist = empService.selectAllService();
		List<JobDTO> joblist = empService.selectAllJobService();
		List<DeptDTO> departmentlist = deptService.selectAllService();

		model.addAttribute("managerlist", managerlist);
		model.addAttribute("emp", emp);
		model.addAttribute("joblist", joblist);
		model.addAttribute("departmentlist", departmentlist);

		return "emp/empDetail";
	}

	@PostMapping("/detail.do")
	public String detailPost(EmpDTO emp, RedirectAttributes attr) {
		int result = empService.updateService(emp);
		log.info("업데이트 개수 : " + result);

		attr.addFlashAttribute("resultMessage", result > 0 ? "업데이트 성공" : "업데이트 실패");
		return "redirect:list.do";
	}

	@RequestMapping(value = "/delete.do", method = { RequestMethod.GET, RequestMethod.POST })
	public String delete(int emp_id, RedirectAttributes attr) {
		int result = empService.deleteService(emp_id);
		log.info("삭제 개수 : " + result);

		attr.addFlashAttribute("resultMessage", result > 0 ? "삭제 성공" : "삭제 실패");
		return "redirect:list.do";
	}

}
