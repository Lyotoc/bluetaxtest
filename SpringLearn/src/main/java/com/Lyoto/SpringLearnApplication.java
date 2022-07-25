package com.Lyoto;

import java.util.List;

import com.Lyoto.Business.Beans.Student;
import com.Lyoto.Business.Beans.User;
import com.Lyoto.FrameWork.config.MyBeanDefinitionRegistryPosttProcessor;
import com.Lyoto.FrameWork.config.UserFactoryBean;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.ConfigurableApplicationContext;
import org.springframework.context.annotation.AnnotationConfigApplicationContext;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.data.redis.core.RedisTemplate;

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
