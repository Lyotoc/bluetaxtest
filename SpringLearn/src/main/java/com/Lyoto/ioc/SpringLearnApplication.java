package com.Lyoto.ioc;

import com.Lyoto.ioc.Business.Beans.Student;
import com.Lyoto.ioc.FrameWork.config.MyBeanDefinitionRegistryPosttProcessor;

import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.AnnotationConfigApplicationContext;
import org.springframework.context.annotation.ComponentScan;

/**
 @author Lyoto
 @Description
 @Date 2022-07-13 14:42
 @Version
 **/
@SpringBootApplication

@ComponentScan("com.Lyoto.*.**")
public class SpringLearnApplication {
	public static void main(String[] args) {
		AnnotationConfigApplicationContext context = new AnnotationConfigApplicationContext();
		MyBeanDefinitionRegistryPosttProcessor myBeanDefinitionRegistryPosttProcessor = new MyBeanDefinitionRegistryPosttProcessor();
		context.addBeanFactoryPostProcessor(myBeanDefinitionRegistryPosttProcessor);
		context.refresh();
		Student myStudent = context.getBean("myStudent", Student.class);
		System.out.println(myStudent);

	}
}
