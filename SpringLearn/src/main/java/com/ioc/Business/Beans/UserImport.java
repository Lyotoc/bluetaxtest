package com.ioc.Business.Beans;

import org.springframework.beans.factory.support.BeanDefinitionRegistry;
import org.springframework.beans.factory.support.RootBeanDefinition;
import org.springframework.context.annotation.ImportBeanDefinitionRegistrar;
import org.springframework.context.annotation.ImportSelector;
import org.springframework.core.type.AnnotationMetadata;

/**
 @author Lyoto
 @Description
 @Date 2022-07-14 15:53
 @Version
 **/
//@Configuration
//@Import(Myselector.class)
//@Import(User.class)
//@Import(MyBeanRegister.class)
public class UserImport {

}

/**
 * @import+importSelector
 */
class Myselector implements ImportSelector {
	/**
	 * 使用自定义类实现ImportSelector接口的selectImports方法，
	 * 可以配合@Import将多个第三方类注入IOC中
	 * @param importingClassMetadata
	 * @return
	 */
	@Override
	public String[] selectImports(AnnotationMetadata importingClassMetadata) {
		//selectImports返回回的数组会通过@import注入IOC
		return new String[]{User.class.getName()};
	}
}

/**
 *
 */
class MyBeanRegister implements ImportBeanDefinitionRegistrar{
	/**
	 * 通过实现ImportBeanDefinitionRegistrar并重写registerBeanDefinitions方法配合@import注入bean
	 * @param importingClassMetadata
	 * @param registry
	 */
	@Override
	public void registerBeanDefinitions(AnnotationMetadata importingClassMetadata, BeanDefinitionRegistry registry) {
		RootBeanDefinition definition = new RootBeanDefinition(User.class);
		registry.registerBeanDefinition("user",definition);
	}
}
