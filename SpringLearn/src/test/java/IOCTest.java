import java.util.Arrays;
import java.util.concurrent.CompletableFuture;
import java.util.concurrent.CountDownLatch;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;
import java.util.concurrent.TimeUnit;

import com.Lyoto.FrameWork.config.FactoryBeanConfig;
import com.Lyoto.FrameWork.config.UserFactoryBean;
import lombok.extern.java.Log;
import lombok.extern.slf4j.Slf4j;
import org.junit.Test;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import org.springframework.context.annotation.AnnotationConfigApplicationContext;

/**
 @author Lyoto
 @Description
 @Date 2022-07-14 17:06
 @Version
 **/
@Slf4j
public class IOCTest {
	AnnotationConfigApplicationContext context = new AnnotationConfigApplicationContext(FactoryBeanConfig.class);
	@Test
	public void testImport() throws InterruptedException {
		new Thread(()->{
			for (int i = 0; i < 100 ; i++) {
				test1();
			}
		},"t1").start();
		new Thread(()->{
			for (int i = 0; i < 100 ; i++) {
				test1();
			}
		},"t2").start();
		new Thread(()->{
			for (int i = 0; i < 100 ; i++) {
				test1();
			}
		},"t3").start();
		new Thread(()->{
			for (int i = 0; i < 100 ; i++) {
				test1();
			}
		},"t4").start();
			TimeUnit.SECONDS.sleep(50);
	}
	void test1(){
		String[] beanDefinitionNames = context.getBeanDefinitionNames();
		Arrays.stream(beanDefinitionNames).forEach(System.out::println);
		UserFactoryBean bean = context.getBean(UserFactoryBean.class);
		log.info("拿到bean:{}",bean);
		System.out.println(bean.getObject()+Thread.currentThread().getName());
	}
}
