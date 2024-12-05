package com.shinhan.myapp.emp;

import java.util.List;
import java.util.Map;

import org.apache.ibatis.session.SqlSession;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

import lombok.extern.slf4j.Slf4j;


@Repository("empMybatis")
@Slf4j
public class EmpDAOMybatis implements EmpDAOInterface {

	@Autowired
	SqlSession sqlSession;
	String namespace = "com.shinhan.emp.";
	
	public List<Map<String, Object>> selectJoin2(String jobid) {
		List<Map<String, Object>> emplist = sqlSession.selectList(namespace+"selectJoin2", jobid);
		log.info("selectJoin : " + emplist);
		return emplist;
	}
	
	public List<EmpJoinDTO> selectJoin(String jobid) {
		List<EmpJoinDTO> emplist = sqlSession.selectList(namespace+"selectJoin", jobid);
		log.info("selectJoin 건수 : " + emplist.size());
		return emplist;
	}
	
	public List<EmpDTO> selectByArray(List<Integer> deptArr) {
		List<EmpDTO> emplist = sqlSession.selectList(namespace+"selectByArray", deptArr);
		log.info("파라메터(list, array) : " + emplist.size());
		return emplist;
	}
	
	public List<JobDTO> selectAllJob(){
		List<JobDTO> joblist = sqlSession.selectList(namespace+"selectAllJob");
		log.info("selectAllJob 건수 : " + joblist.size());
		return joblist;
	}
	
	//1.특정부서의 직원조회      where department_id = ? 
	public List<EmpDTO> selectByDept(int dept_id) {
		List<EmpDTO> emplist = sqlSession.selectList(namespace+"selectByDept", dept_id);
		log.info("selectByDept 건수 : " + emplist.size());
		return emplist;
	}

	
	//2.특정job_id인 직원조회   where job_id = ? 
	public List<EmpDTO> selectByJob(String job_id) {
		List<EmpDTO> emplist = sqlSession.selectList(namespace+"selectByJob", job_id);
		log.info("selectByJob 건수 : " + emplist.size());
		return emplist;
	}
	
	//3.급여가 ?이상인 직원조회   where salary >= ? 
	public List<EmpDTO> selectBySalary(double salary) {
		List<EmpDTO> emplist = sqlSession.selectList(namespace+"selectBySalary", salary);
		log.info("selectBySalary 건수 : " + emplist.size());
		return emplist;
	}
	
	//4.부서, 직책, 급여, 입사일 조건으로 조회 
	//     where department_id = ? and job_id = ? and salary >= ? and  hire_date >= ?
	public List<EmpDTO> selectByCondition(Map<String,Object> map) {
		List<EmpDTO> emplist = sqlSession.selectList(namespace+"selectByCondition2", map);
		log.info("selectByCondition 건수 : " + emplist.size());
		return emplist;
	}	
	
	public List<EmpDTO> selectAll() {
		List<EmpDTO> emplist = sqlSession.selectList(namespace+"selectAll");
		log.info("selectAll 건수 : " + emplist.size());
		return emplist;
	}

	public EmpDTO selectById(int empid) {
		EmpDTO emp = sqlSession.selectOne(namespace+"selectById", empid);
		log.info("selectById : " + emp);
		return  emp;
	}

	// DB에 입력
	public int insert(EmpDTO emp) {
		int result = sqlSession.insert(namespace+"insert", emp);
		log.info("insert 건수 : " + result);
		return  result;
	}

	// 수정
	public int update(EmpDTO emp) {
		int result = sqlSession.update(namespace+"update", emp);
		log.info("update 건수 : " + result);
		return  result;
	}

	// 삭제
	public int delete(int empid) {
		int result = sqlSession.delete(namespace+"delete", empid);
		log.info("delete 건수 : " + result);
		return  result;
	}
	

}
