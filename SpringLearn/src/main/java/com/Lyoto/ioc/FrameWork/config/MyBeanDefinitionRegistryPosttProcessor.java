package com.Lyoto.ioc.FrameWork.config;

import com.Lyoto.ioc.Business.Beans.Student;
import lombok.extern.slf4j.Slf4j;

import org.springframework.beans.BeansException;
import org.springframework.beans.factory.config.ConfigurableListableBeanFactory;
import org.springframework.beans.factory.support.AbstractBeanDefinition;
import org.springframework.beans.factory.support.BeanDefinitionBuilder;
import org.springframework.beans.factory.support.BeanDefinitionRegistry;
import org.springframework.beans.factory.support.BeanDefinitionRegistryPostProcessor;
import org.springframework.stereotype.Component;

/**
 @author Lyoto
 @Description
 @Date 2022-07-18 17:39
 @Version
 **/
@Slf4j
@Component
public class MyBeanDefinitionRegistryPosttProcessor implements BeanDefinitionRegistryPostProcessor {

	@Override
	public void postProcessBeanDefinitionRegistry(BeanDefinitionRegistry registry) throws BeansException {
		log.info("{}中Bean的数量->{}",getClass().getSimpleName(),registry.getBeanDefinitionCount());
		AbstractBeanDefinition beanDefinition = BeanDefinitionBuilder.rootBeanDefinition(Student.class)
				.getBeanDefinition();
		registry.registerBeanDefinition("myStudent",beanDefinition);
		log.info("{}执行完毕",getClass().getSimpleName()+"postProcessBeanDefinitionRegistry");
	}

	@Override
	public void postProcessBeanFactory(ConfigurableListableBeanFactory beanFactory) throws BeansException {
		log.info("{}中Bean的数量->{}",getClass().getSimpleName(),beanFactory.getBeanDefinitionCount());
	}
}
