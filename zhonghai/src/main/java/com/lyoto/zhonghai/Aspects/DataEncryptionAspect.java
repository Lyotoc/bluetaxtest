package com.lyoto.zhonghai.Aspects;

import java.io.UnsupportedEncodingException;
import java.lang.reflect.Field;
import java.lang.reflect.Method;
import java.nio.charset.Charset;
import java.nio.charset.StandardCharsets;
import java.util.List;

import javax.crypto.spec.SecretKeySpec;

import cn.hutool.core.util.StrUtil;
import cn.hutool.crypto.SecureUtil;
import cn.hutool.crypto.symmetric.AES;
import com.baomidou.mybatisplus.core.toolkit.StringUtils;
import com.lyoto.zhonghai.Bean.Annotation.NeedEncryption;
import org.aspectj.lang.ProceedingJoinPoint;
import org.aspectj.lang.annotation.Around;
import org.aspectj.lang.annotation.Aspect;
import org.aspectj.lang.annotation.Pointcut;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Component;

/**
 @author Lyoto
 @Description
 @Date 2022-04-08 17:32
 @Version
 **/
@Aspect
@Component
public class DataEncryptionAspect {
	@Value("${encKey:123456}")
	private  String Enckey;

	@Pointcut(value = "execution(* com.lyoto.zhonghai.Service.mapper.*.*(..))")
	public void encryptAndDecrypt() {
	}

	@Around("encryptAndDecrypt()")
	public Object handle(ProceedingJoinPoint proceedingJoinPoint)throws Throwable{
		// before
		Object[] args = proceedingJoinPoint.getArgs();
		for (Object arg : args) {
			// 集合类型目前只支持List，处理其他类型需要扩展
			if (arg instanceof List) {
				for (Object obj : (List) arg) {
					doEncrypt(obj, true);
				}
			} else {
				doEncrypt(arg, true);
			}
		}

		// proceed
		Object result;
		result = proceedingJoinPoint.proceed(args);

		// after
		// 集合类型目前只支持List，处理其他类型需要扩展
		if (result instanceof List) {
			for (Object obj : (List) result) {
				doEncrypt(obj, false);
			}
		} else {
			doEncrypt(result, false);
		}
		return result;
	}

	private void doEncrypt(Object obj, Boolean isEncrypt) throws Exception {
		if (null != obj) {
			Class objClazz = obj.getClass();
			if (objClazz.isAnnotationPresent(NeedEncryption.class)) {
				Field[] clazzFields = objClazz.getDeclaredFields();
				for (Field field : clazzFields) {
					// 需要脱敏的字段类型目前只支持String
					if ("class java.lang.String".equals(field.getGenericType().toString()) && field.isAnnotationPresent(NeedEncryption.class)) {
						Method getFieldMethod = objClazz.getMethod("get" + getMethodName(field.getName()));
						Method setFieldMethod = objClazz.getMethod("set" + getMethodName(field.getName()), String.class);
						String fieldValue = (String) getFieldMethod.invoke(obj);
						if (StringUtils.isNotBlank(fieldValue)) {
							setFieldMethod.invoke(obj, handle(fieldValue, isEncrypt));
						}
					}
				}
			}
		}
	}

	/**
	 *
	 * @param content
	 * @param isEncrypt
	 * @return
	 */
	private String handle(String content, Boolean isEncrypt) {
		if (StrUtil.isBlank(content)) {
			return content;
		}
		AES aes = SecureUtil.aes(generateKeyByUTF8(Enckey));
		// 加解密算法可自行替换，对称非对称都可以
		return isEncrypt ? aes.encryptHex(content) : aes.decryptStr(content);
	}

	private static String getMethodName(String fildeName) {
		byte[] items = fildeName.getBytes();
		items[0] = (byte) ((char) items[0] - 'a' + 'A');
		return new String(items);
	}

	public static byte[] generateKeyByUTF8(final String key){
		return generateKey(key,StandardCharsets.UTF_8);
	}
	public static byte[] generateKey(final String key, final Charset encoding) {
		final byte[] finalKey = new byte[16];
		int i = 0;
		for(byte b : key.getBytes(encoding)) {
			finalKey[i++%16] ^= b;
		}
		return finalKey;
	}

}
