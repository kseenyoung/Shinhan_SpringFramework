package com.shinhan.myapp.model;

import java.util.List;

import org.apache.ibatis.session.SqlSession;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

import com.shinhan.myapp.vo.DeptDTO;

import lombok.extern.slf4j.Slf4j;

@Slf4j
@Repository("deptMybatis") 
public class DeptDAOMybatis  implements DeptDAOInterface {

	// DB정보 -> DataSource -> SQLSessionFactory -> SqlSession 생성
	@Autowired
	SqlSession sqlSession;
	String namespace = "com.shinhan.dept.";
	
	// 1.모두조회
	public List<DeptDTO> selectAll() {
		List<DeptDTO> deptlist = sqlSession.selectList(namespace + "selectAll");
		log.info("deptALL 조회 건 : " + deptlist.size());
		
		return deptlist;
	}

	public DeptDTO selectById(int deptid) {
		DeptDTO dept = sqlSession.selectOne(namespace+"selectById", deptid); 
		log.info("dept1건 : " + dept);
		return dept;

	}

	public int insert(DeptDTO dept) {
		int result = sqlSession.insert(namespace+"insert", dept);
		log.info(result + "건 입력");
		return result;
	}

	public int update(DeptDTO dept) {
		int result = sqlSession.update(namespace+"update", dept);
		log.info(result + "건 수정");
		return result;
	}

	public int delete(int deptid) {
		int result = sqlSession.delete(namespace+"delete", deptid);
		log.info(result + "건 수정");
		return result;
	}

	public int deleteArray(Integer[] deptid) {
		
		return 0;
	}

}
