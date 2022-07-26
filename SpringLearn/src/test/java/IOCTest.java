import com.Lyoto.ioc.FrameWork.config.ExtConfig;
import lombok.extern.slf4j.Slf4j;
import org.junit.Test;

import org.springframework.context.annotation.AnnotationConfigApplicationContext;

/**
 @author Lyoto
 @Description
 @Date 2022-07-14 17:06
 @Version
 **/
@Slf4j
public class IOCTest {
	@Test
	public void testImport(){

		AnnotationConfigApplicationContext context = new AnnotationConfigApplicationContext(ExtConfig.class);
		context.close();
	}
	void test1(){
		//String[] beanDefinitionNames = context.getBeanDefinitionNames();
		//Arrays.stream(beanDefinitionNames).forEach(System.out::println);
		//User user = context.getBean("userFactoryBean", User.class);
		//log.info("拿到bean:{}",user);
		//System.out.println(user.getUserName()+Thread.currentThread().getName());
	}
}
