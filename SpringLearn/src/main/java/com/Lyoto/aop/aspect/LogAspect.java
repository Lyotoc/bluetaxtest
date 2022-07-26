package com.Lyoto.aop.aspect;

import java.util.Arrays;

import lombok.extern.slf4j.Slf4j;
import org.aspectj.lang.JoinPoint;
import org.aspectj.lang.annotation.After;
import org.aspectj.lang.annotation.AfterReturning;
import org.aspectj.lang.annotation.AfterThrowing;
import org.aspectj.lang.annotation.Aspect;
import org.aspectj.lang.annotation.Before;
import org.aspectj.lang.annotation.Pointcut;

/**
 * 切面类
 * @author liayun
 *
 */
@Aspect
@Slf4j
public class LogAspect {
	@Pointcut("execution(public int com.Lyoto.aop.Service.MathCalculator.*(..))")
	public void cutPoint(){
	}

	// @Before：在目标方法（即div方法）运行之前切入，public int com.meimeixia.aop.MathCalculator.div(int, int)这一串就是切入点表达式，指定在哪个方法切入
	@Before("cutPoint()")
	public void logStart() {
		log.info("除法运行......@Before，参数列表是：{}");
	}
	// @Before：在目标方法（即div方法）运行之前切入，public int com.meimeixia.aop.MathCalculator.div(int, int)这一串就是切入点表达式，指定在哪个方法切入
	@Before("cutPoint()")
	public void logStart(JoinPoint joinPoint) {
		log.info("{}除法运行......@Before，参数列表是：{}",joinPoint.getSignature().getName(), Arrays.asList(joinPoint.getArgs()));
	}

	// 在目标方法（即div方法）结束时被调用
	@After("cutPoint()")
	public void logEnd() {
		log.info("除法结束......@After");
	}

	// 在目标方法（即div方法）结束时被调用
	@After("cutPoint()")
	public void logEnd(JoinPoint joinPoint) {
		log.info("{}除法结束......@After",joinPoint.getSignature().getName());
	}

	// 在目标方法（即div方法）正常返回了，有返回值，被调用
	@AfterReturning("cutPoint()")
	public void logReturn() {
		log.info("除法正常返回......@AfterReturning，运行结果是：{}");
	}

	// 在目标方法（即div方法）正常返回了，有返回值，被调用
	@AfterReturning(value = "cutPoint()",returning = "o")
	public void logReturn(JoinPoint joinPoint,Object o) {
		log.info("{}除法正常返回......@AfterReturning，运行结果是：{}",joinPoint.getSignature().getName(),o);
	}

	// 在目标方法（即div方法）出现异常，被调用
	@AfterThrowing("cutPoint()")
	public void logException() {
		log.info("除法出现异常......异常信息：{}");
	}
	// 在目标方法（即div方法）出现异常，被调用
	@AfterThrowing(value = "cutPoint()",throwing = "exception")
	public void logException(JoinPoint joinPoint , Exception exception) {
		log.info("{}除法出现异常......异常信息：{}",joinPoint.getSignature().getName(),exception);
	}

}
