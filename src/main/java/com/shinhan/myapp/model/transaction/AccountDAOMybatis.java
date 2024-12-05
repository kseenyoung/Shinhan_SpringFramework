package com.shinhan.myapp.model.transaction;

import org.apache.ibatis.session.SqlSession;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

@Repository
public class AccountDAOMybatis {

	@Autowired
	SqlSession sqlSession;
	
	// 입금
	public int deposit() {
		return sqlSession.update("com.shinhan.account.update1", "112");
	}
	
	// 출금
	public int withdraw() {
		return sqlSession.update("com.shinhan.account.update2");
	}
}
