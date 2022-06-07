package com.lyoto.zhonghai.Interceptor.Impl;

import java.lang.reflect.AnnotatedElement;
import java.lang.reflect.Field;
import java.lang.reflect.Method;
import java.sql.Driver;
import java.sql.PreparedStatement;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Properties;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

import cn.hutool.core.annotation.AnnotationUtil;
import com.lyoto.zhonghai.Bean.Annotation.EncryptionMapper;
import com.lyoto.zhonghai.Interceptor.BaseInterceptor;
import org.apache.ibatis.executor.Executor;
import org.apache.ibatis.executor.parameter.ParameterHandler;
import org.apache.ibatis.mapping.BoundSql;
import org.apache.ibatis.mapping.MappedStatement;
import org.apache.ibatis.mapping.ParameterMapping;
import org.apache.ibatis.plugin.Intercepts;
import org.apache.ibatis.plugin.Invocation;
import org.apache.ibatis.plugin.Signature;
import org.apache.ibatis.session.Configuration;
import org.apache.ibatis.session.ResultHandler;
import org.apache.ibatis.session.RowBounds;
import org.apache.ibatis.type.BigDecimalTypeHandler;
import org.apache.ibatis.type.StringTypeHandler;

import org.springframework.core.annotation.AnnotationUtils;
import org.springframework.stereotype.Component;

/**
 @author Lyoto
 @Description
 @Date 2022-05-06 14:06
 @Version
 **/
@Component
@Intercepts(
		//执行参数接口，method为接口名称，args为参数对象（注意：不同版本个数不同，该版本：5.0.0）
		@Signature(type = ParameterHandler.class, method = "setParameters", args = {PreparedStatement.class})
)
public class DefaultInterceptor implements BaseInterceptor {
	@Override
	public Object intercept(Invocation invocation) throws Throwable {
		//拿到参数处理对象
		ParameterHandler handler = (ParameterHandler) invocation.getTarget();
		//参数映射的属性
		Field mappedStatementField = handler.getClass().getDeclaredField("mappedStatement");
		//设置属性为可访问
		mappedStatementField.setAccessible(true);
		//获取映射对象
		MappedStatement mappedStatement = (MappedStatement) mappedStatementField.get(handler);
		//通过id截取获得调用类的类名
		String invokeClassName = mappedStatement.getId().substring(0, mappedStatement.getId().lastIndexOf("."));
		Class<?> invokeClass = Class.forName(invokeClassName);
		boolean present = invokeClass.isAnnotationPresent(EncryptionMapper.class);
		if (!present) {
			return invocation.proceed();
		}
		PreparedStatement preparedStatement = (PreparedStatement) invocation.getArgs()[0];
		dealParameter(invocation);
		Object returnValue = invocation.proceed();
		dealReturnValue(returnValue);
		return returnValue;
	}

	private void dealParameter(Invocation invocation) throws Throwable {






		Object parameterObject = handler.getParameterObject();
		Class<?> beanClass = parameterObject.getClass();

		Field[] fields = beanClass.getDeclaredFields();
		Map map = new HashMap<String, String>();
		for (Field field : fields) {
			//
			map.put(field.getName(), "444");
		}
		Field parameterMappingsField = boundSql.getClass().getDeclaredField("parameterMappings");
		parameterMappingsField.setAccessible(true);
		ArrayList arrayList = (ArrayList) parameterMappingsField.get(boundSql);
		ParameterMapping parameterMapping = (ParameterMapping) arrayList.get(0);
		Field javaTypeField = parameterMapping.getClass().getDeclaredField("javaType");
		Field typeHandlerField = parameterMapping.getClass().getDeclaredField("typeHandler");
		typeHandlerField.setAccessible(true);
		javaTypeField.setAccessible(true);
		javaTypeField.set(parameterMapping, String.class);
		typeHandlerField.set(parameterMapping, new StringTypeHandler());
		parameterField.set(handler, map);
		handler.setParameters(preparedStatement);

	}

	private void dealReturnValue(Object o) {

	}

	@Override
	public Object plugin(Object target) {
		return BaseInterceptor.super.plugin(target);
	}

	@Override
	public void setProperties(Properties properties) {
		BaseInterceptor.super.setProperties(properties);
	}
}
