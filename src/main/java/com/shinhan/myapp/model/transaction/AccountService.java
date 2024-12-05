package com.shinhan.myapp.model.transaction;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Propagation;
import org.springframework.transaction.annotation.Transactional;

import lombok.extern.slf4j.Slf4j;

@Slf4j
@Service
public class AccountService {

	@Autowired
	AccountDAOMybatis dao;
	
	@Transactional(propagation = Propagation.REQUIRED)  // default : REQUIRED
	public void transferService() {
		int result1 = dao.deposit();
		int result2 = dao.withdraw();
		log.debug("transferService 결과 : deposit/ withdraw" + result1 + "/" + result2);
		log.info("transferService 결과 : deposit/ withdraw" + result1 + "/" + result2);
		log.warn("transferService 결과 : deposit/ withdraw" + result1 + "/" + result2);
		log.error("transferService 결과 : deposit/ withdraw" + result1 + "/" + result2);
		
	}
}
