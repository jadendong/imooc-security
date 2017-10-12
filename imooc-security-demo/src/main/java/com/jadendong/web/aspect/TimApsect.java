package com.jadendong.web.aspect;

import java.util.Date;

import org.aspectj.lang.ProceedingJoinPoint;
import org.aspectj.lang.annotation.Around;
import org.aspectj.lang.annotation.Aspect;
import org.springframework.stereotype.Component;

//@Aspect
//@Component
public class TimApsect {
	
	@Around("execution(* com.jadendong.web.controller.UserController.*(..))")
	public Object handleControllerMethod(ProceedingJoinPoint pjp) throws Throwable {
		System.out.println("Aspect start");
		Object[] args=pjp.getArgs();
		
		for (Object arg : args) {
			System.out.println("args is "+arg);
		}
		
		long start=new Date().getTime();
		
		Object object=pjp.proceed();
		
		System.out.println("time aspect 耗时："+(new Date().getTime()-start));
		System.out.println("aspect end");
		
		return object;
	}
	
}
