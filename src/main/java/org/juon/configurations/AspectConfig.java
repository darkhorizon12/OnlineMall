package org.juon.configurations;

import java.util.Arrays;

import org.aspectj.lang.JoinPoint;
import org.aspectj.lang.ProceedingJoinPoint;
import org.aspectj.lang.annotation.Around;
import org.aspectj.lang.annotation.Aspect;
import org.aspectj.lang.annotation.Before;
import org.aspectj.lang.annotation.Pointcut;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.core.Ordered;
import org.springframework.core.annotation.Order;
import org.springframework.stereotype.Component;



@Component
@Aspect
@Order(Ordered.LOWEST_PRECEDENCE)
public class AspectConfig {
	
	private static final Logger logger = LoggerFactory.getLogger(AspectConfig.class);
	
	@Around("execution(* org.juon.jpashop.web.*.*(..))")
	public Object doExcutionTimeLog(ProceedingJoinPoint joinPoint) throws Throwable{
		final long start = System.currentTimeMillis();
		
		final Object proceed = joinPoint.proceed();
		
		long executionTime = System.currentTimeMillis() - start;
		
		logger.info("\n################### doExcutionTimeLog ############################\n");
		logger.info(joinPoint.getTarget().getClass().getName() + "." + joinPoint.getSignature() + " executeed in " + executionTime + "ms");
		logger.info("\n#############################################################\n");
		
		return proceed;
	}
	
	@Pointcut("execution(* org.juon.jpashop.repository.*.*(..))")
	private void repositoryLevel() {}
	
/*	@Before("repositoryLevel()")
	public void logDaoParameter(JoinPoint joinPoint) {
		logger.info("\n##################### logDaoAccess ################################\n");
		logger.info(joinPoint.getTarget().getClass().getName() + "." + joinPoint.getSignature());
		logger.info(joinPoint.getSignature().getDeclaringTypeName());
		Object[] args = joinPoint.getArgs();
		Arrays.asList(args).stream().map(arg -> String.valueOf(arg)).forEach(System.out::println);
		logger.info("\n#############################################################\n");
	}*/
	

}
