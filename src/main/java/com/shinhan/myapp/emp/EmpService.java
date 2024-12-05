package com.shinhan.myapp.emp;

import java.util.List;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.stereotype.Service;

//사용자요청-->Controller--->Service--->DAO--->DB
//DB관련없는 업무로직은 Service에서 수행 
@Service
public class EmpService {

//	EmpDAO empDAO = new EmpDAO();
	@Autowired  // type -> name 순으로 찾아서 DI
	@Qualifier("empMybatis")
	EmpDAOMybatis empDAO;
	
	public List<EmpDTO> selectByArray(List<Integer> deptArr){
		return empDAO.selectByArray(deptArr);
	}
	
	// 직원(employees), 부서(departments), 지역(locations), 나라(country)
	public List<EmpJoinDTO> selectByJobJoin(String job_id) {
		return empDAO.selectJoin(job_id);
	}
	
	public List<Map<String, Object>> selectByJobJoin2(String job_id) {
		return empDAO.selectJoin2(job_id);
	}

	// Jobs테이블의 모든 data가져오기
	public List<JobDTO> selectAllJobService() {
		return empDAO.selectAllJob();
	}

	//
	public List<EmpDTO> selectByDept(int dept_id) {
		return empDAO.selectByDept(dept_id);
	}

	public List<EmpDTO> selectByJob(String job_id) {
		return empDAO.selectByJob(job_id);
	}

	public List<EmpDTO> selectBySalary(double salary) {
		return empDAO.selectBySalary(salary);
	}

	public List<EmpDTO> selectByCondition(Map<String, Object> map) {
		return empDAO.selectByCondition(map);
	}

	//
	public List<EmpDTO> selectAllService() {
		return empDAO.selectAll();
	}

	public EmpDTO selectByIdService(int empid) {
		// TODO Auto-generated method stub
		return empDAO.selectById(empid);
	}

	// 입력서비스
	public int insertService(EmpDTO emp) {
		return empDAO.insert(emp);

	}

	// 수정서비스
	public int updateService(EmpDTO emp) {
		return empDAO.update(emp);
	}

	// 삭제서비스
	public int deleteService(int empid) {
		return empDAO.delete(empid);
	}
}
