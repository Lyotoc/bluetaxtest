package com.ioc.FrameWork.config;

import com.ioc.Business.Beans.Student;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.context.annotation.Configuration;

/**
 @author Lyoto
 @Description
 @Date 2022-07-18 16:13
 @Version
 **/
@ComponentScan("com.Lyoto.FrameWork")
@Configuration
public class ExtConfig {
	@Bean
	public Student student(){
		return new Student();
	}

}
