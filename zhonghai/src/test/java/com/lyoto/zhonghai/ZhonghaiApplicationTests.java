package com.lyoto.zhonghai;

import cn.hutool.core.date.DateTime;
import cn.hutool.core.date.DateUtil;
import cn.hutool.extra.pinyin.PinyinUtil;
import com.lyoto.zhonghai.Bean.Exception.BusinessException;
import com.lyoto.zhonghai.Controller.FetchData;
import com.lyoto.zhonghai.Service.Impl.FetchDataToFile;
import org.junit.jupiter.api.Test;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.boot.test.context.SpringBootTest;

@SpringBootTest
class ZhonghaiApplicationTests {
	@Value("${zhonghai.appKey:PersonalTax}")
	String appKey;
	@Value("${zhonghai.appSecret:ab9782f3776acb47934cfc830cd217a4}")
	String appSecret;
	@Value("${zhonghai.url.user:cdp.coli688.com/userApi/v1/synUserInfo}")
	String userUrl;
	@Value("${zhonghai.url.company:cdp.coli688.com/productApi/v1/rdt/findTaxpayerList}")
	String companyUrl;
	Integer resultCode;
	@Autowired
	FetchData data;
	@Autowired
	FetchDataToFile dataToFile;
	@Test
	void contextLoads() {
		dataToFile.fetchUserSave();
		//data.getUserInfo();
	}



}
