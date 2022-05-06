package com.lyoto.zhonghai.Interceptor.Impl;

import java.lang.reflect.Method;
import java.sql.Driver;
import java.util.Properties;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

import cn.hutool.core.lang.Assert;
import com.lyoto.zhonghai.Bean.Annotation.EncryptionMapper;
import com.lyoto.zhonghai.Interceptor.BaseInterceptor;
import com.lyoto.zhonghai.Utils.EncryptUtil;
import com.lyoto.zhonghai.Utils.EncryptUtils;
import org.apache.ibatis.executor.Executor;
import org.apache.ibatis.mapping.MappedStatement;
import org.apache.ibatis.plugin.Intercepts;
import org.apache.ibatis.plugin.Invocation;
import org.apache.ibatis.plugin.Signature;
import org.apache.ibatis.session.Configuration;
import org.apache.ibatis.session.ResultHandler;
import org.apache.ibatis.session.RowBounds;

import org.springframework.stereotype.Component;

/**
 @author Lyoto
 @Description
 @Date 2022-05-06 14:06
 @Version
 **/
@Component
@Intercepts({@Signature(type = Executor.class, method = "update", args = {MappedStatement.class, Object.class}),
		@Signature(type = Executor.class, method = "query", args = {MappedStatement.class, Object.class, RowBounds.class, ResultHandler.class})})
public class DefaultInterceptor implements BaseInterceptor {
	@Override
	public Object intercept(Invocation invocation) throws Throwable {
		dealParameter(invocation);
		Object returnValue = invocation.proceed();
		dealReturnValue(returnValue);
		return returnValue;
	}

	private void dealParameter(Invocation invocation) {
		MappedStatement statement = (MappedStatement) invocation.getArgs()[0];
		String id = statement.getId();
		Pattern pattern = Pattern.compile(".+(?=\\.)");
		Matcher matcher = pattern.matcher(id);
		Assert.isTrue(matcher.find(),"类不存在！");
		String group = matcher.group(0);
		try {
			Class<?> name = Class.forName(group);
			boolean present = name.isAnnotationPresent(EncryptionMapper.class);
			if (present){
				Object arg = invocation.getArgs()[1];
				Configuration configuration = statement.getConfiguration();
				statement.getSqlSource().getBoundSql(invocation.getArgs()[1]);
				configuration.newMetaObject(arg);
				//configuration.newMetaObject(new String());
			}
		}
		catch (ClassNotFoundException e) {
			e.printStackTrace();
		}
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
