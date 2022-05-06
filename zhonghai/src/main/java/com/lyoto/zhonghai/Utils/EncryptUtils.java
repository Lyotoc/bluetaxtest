package com.lyoto.zhonghai.Utils;

import java.nio.charset.Charset;
import java.nio.charset.StandardCharsets;

import cn.hutool.core.lang.Assert;
import cn.hutool.core.util.StrUtil;
import cn.hutool.crypto.SecureUtil;
import cn.hutool.crypto.symmetric.AES;

/**
 @author Lyoto
 @Description
 @Date 2022-05-06 16:54
 @Version
 **/
public class EncryptUtils {

	final static String Enckey = "123456789";
	private static String getMethodName(String fildeName) {
		byte[] items = fildeName.getBytes();
		items[0] = (byte) ((char) items[0] - 'a' + 'A');
		return new String(items);
	}

	public static byte[] generateKeyByUTF8(final String key){
		return generateKey(key, StandardCharsets.UTF_8);
	}
	public static byte[] generateKey(final String key, final Charset encoding) {
		final byte[] finalKey = new byte[16];
		int i = 0;
		for(byte b : key.getBytes(encoding)) {
			finalKey[i++%16] ^= b;
		}
		return finalKey;
	}

	/**
	 *
	 *
	 * @param content
	 * @param isEncrypt
	 * @return
	 */
	public static String handle(String content, Boolean isEncrypt) {
		Assert.isTrue(StrUtil.isNotBlank(content),"该值为空{}",content);
		AES aes = SecureUtil.aes(generateKeyByUTF8(Enckey));
		// 加解密算法可自行替换，对称非对称都可以
		return isEncrypt ? aes.encryptHex(content) : aes.decryptStr(content);
	}

}
