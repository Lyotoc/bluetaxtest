import java.io.BufferedInputStream;
import java.io.BufferedOutputStream;
import java.util.Arrays;
import java.util.concurrent.TimeUnit;

import ch.qos.logback.core.pattern.ConverterUtil;
import cn.hutool.core.convert.Convert;
import cn.hutool.core.io.FileUtil;
import cn.hutool.poi.ofd.OfdWriter;
import com.Lyoto.Business.Beans.User;
import com.Lyoto.FrameWork.config.ExtConfig;
import com.Lyoto.FrameWork.config.FactoryBeanConfig;
import com.Lyoto.FrameWork.config.MyBeanFactoryPostProcessor;
import com.Lyoto.FrameWork.config.UserFactoryBean;
import lombok.extern.slf4j.Slf4j;
import org.junit.Test;
import org.ofdrw.converter.ConvertHelper;

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
