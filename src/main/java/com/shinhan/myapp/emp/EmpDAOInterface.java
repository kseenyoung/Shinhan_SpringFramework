package com.shinhan.myapp.emp;

import java.util.List;
import java.util.Map;

public interface EmpDAOInterface {
	public List<Map<String, Object>> selectJoin2(String jobid);
	
	public List<EmpJoinDTO> selectJoin(String jobid);
	
	//1.특정부서의 직원조회      where department_id = ? 
	public List<EmpDTO> selectByDept(int dept_id);

	public List<JobDTO> selectAllJob();
	
	//2.특정job_id인 직원조회   where job_id = ? 
	public List<EmpDTO> selectByJob(String job_id);
	
	//3.급여가 ?이상인 직원조회   where salary >= ? 
	public List<EmpDTO> selectBySalary(double salary);
	
	//4.부서, 직책, 급여, 입사일 조건으로 조회 
	//     where department_id = ? and job_id = ? and salary >= ? and  hire_date >= ?
	public List<EmpDTO> selectByCondition(Map<String,Object> map);
	
	public List<EmpDTO> selectAll();

	public EmpDTO selectById(int empid);
	// DB에 입력
	public int insert(EmpDTO emp);

	// 수정
	public int update(EmpDTO emp) ;

	// 삭제
	public int delete(int empid);
}
