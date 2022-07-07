package com.lyoto.zhonghai.Utils;

import java.lang.reflect.Field;
import java.nio.charset.Charset;
import java.nio.charset.StandardCharsets;
import java.sql.PreparedStatement;
import java.util.Arrays;

import cn.hutool.crypto.SecureUtil;
import cn.hutool.crypto.symmetric.AES;
import org.apache.ibatis.executor.parameter.ParameterHandler;
import org.apache.ibatis.mapping.BoundSql;

import org.springframework.stereotype.Component;

/**
 @author Lyoto
 @Description
 @Date 2022-05-07 16:43
 @Version
 **/
@Component
public class EncryptHandler {
	private void parameterProcess(ParameterHandler handler,PreparedStatement statement) throws Throwable{
		//参数对象属性
		Field parameterField = handler.getClass().getDeclaredField("parameterObject");
		parameterField.setAccessible(true);
		Object bean = parameterField.get(handler);
		Class<?> beanClass = bean.getClass();

		// 反射获取 BoundSql 对象，此对象包含生成的sql和sql的参数map映射
		Field boundSqlField = handler.getClass().getDeclaredField("boundSql");
		boundSqlField.setAccessible(true);
		BoundSql boundSql = (BoundSql) boundSqlField.get(handler);

	}

	public static void main(String[] args) {
		AES aes =SecureUtil.aes(EncryptUtils.generateKeyByUTF8("bluetax"));
		String encrypt = aes.encryptBase64("Aa123456");
		System.out.println(encrypt);
		String s =aes.decryptStr("MOWbQxucn3sv42ub7IGNdQ==");
		System.out.println(s);

	}
}
