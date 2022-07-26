package com.Lyoto.aop.Config;

import com.Lyoto.aop.Service.MathCalculator;
import com.Lyoto.aop.aspect.LogAspect;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.EnableAspectJAutoProxy;

/**
 @author Lyoto
 @Description
 @Date 2022-07-26 13:51
 @Version
 **/
@Configuration
@EnableAspectJAutoProxy
public class AspectsConfig {
	@Bean
	public LogAspect logAspect(){
		return new LogAspect();
	}
	@Bean
	public MathCalculator mathCalculator(){
		return new MathCalculator();
	}
}
