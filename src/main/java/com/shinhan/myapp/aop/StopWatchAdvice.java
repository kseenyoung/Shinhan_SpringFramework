package com.shinhan.myapp.aop;

import org.aopalliance.intercept.MethodInterceptor;
import org.aspectj.lang.ProceedingJoinPoint;
import org.aspectj.lang.annotation.Around;
import org.aspectj.lang.annotation.Aspect;
import org.aspectj.lang.annotation.Pointcut;
import org.springframework.stereotype.Component;
import org.springframework.util.StopWatch;

@Component
@Aspect // @Pointcut + Advice
public class StopWatchAdvice { // POJO : extends, implements 없는 순수 Java

	// return 아무거나, select로 시작하는 모든 method
//	@Pointcut("execution(* select*(..))")
	// EmpController 안에 있는 애들만
	@Pointcut("within(com.shinhan.myapp.controller.EmpController)")
	public void timer() {
	}
	
	@Around("timer()")
	public Object aoround(ProceedingJoinPoint jp) throws Throwable {
		// 보조업무
		System.out.println("******" + jp.getSignature().getName() + "메서드 호출 전");
		StopWatch watch = new StopWatch("계산시간");
		watch.start();

		// 주업무
		Object obj = jp.proceed();

		// 보조업무
		System.out.println("*****" + jp.getSignature().getName() + "메서드 호출 후");
		watch.stop();
		System.out.println("주업무 수행 시간:" + watch.getTotalTimeMillis());
		System.out.println(watch.prettyPrint());
		
		return obj;
	}
}
