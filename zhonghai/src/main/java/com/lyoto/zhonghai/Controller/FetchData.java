package com.lyoto.zhonghai.Controller;

import java.io.File;
import java.nio.charset.Charset;
import java.util.HashMap;
import java.util.Map;
import java.util.concurrent.ScheduledThreadPoolExecutor;
import java.util.concurrent.ThreadPoolExecutor;

import cn.hutool.core.io.file.FileWriter;
import cn.hutool.core.thread.ThreadUtil;
import cn.hutool.http.HttpRequest;
import cn.hutool.json.JSONUtil;
import com.alibaba.fastjson.JSON;
import com.alibaba.fastjson.JSONObject;
import com.alibaba.fastjson.serializer.SerializerFeature;
import com.lyoto.zhonghai.Utils.EncryptUtil;
import lombok.extern.slf4j.Slf4j;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Component;

/**
 @author Lyoto
 @Description
 @Date 2022-01-24 17:40
 @Version
 **/
@Slf4j
@Component
public class FetchData {
	@Value("${zhonghai.appKey:PersonalTax}")
	private String appKey;
	@Value("${zhonghai.appSecret:ab9782f3776acb47934cfc830cd217a4}")
	private String appSecret;
	@Value("${zhonghai.url.user:user.cdp.coli688.com/userApi/v1/synUserInfo}")
	private String userUrl;

	@Value("${zhonghai.url.company:cdp.coli688.com/productApi/v1/rdt/findTaxpayerList}")
	private String companyUrl;

	public void getUserInfo() {

		JSONObject param = new JSONObject();
		Integer pages = this.getPage(userUrl);
		String timestamp = String.valueOf(System.currentTimeMillis());
		StringBuilder stringBuilder = new StringBuilder();
		ThreadPoolExecutor executor = ThreadUtil.newExecutor(5, 10);
		for (int pageNum = 1; pageNum <= pages ; pageNum++) {
			int finalPageNum = pageNum;
			executor.execute(() -> {
				//组装报文
				param.put("pageNum", finalPageNum);
				log.info("报文参数--->{}", param);
				String body = HttpRequest.post(userUrl).contentType("application/x-www-form-urlencoded")
						.header("appKey", appKey)
						.header("appSecret", appSecret)
						.header("sign", EncryptUtil.generateSign(new String[] {appKey, appSecret, timestamp}))
						.header("timestamp", timestamp).form("param", param).execute().body();
				stringBuilder.append(body);
				JSONObject data1 = JSON.parseObject(body).getJSONObject("data");
			});
		}
		executor.shutdown();
		while(true){
			if(executor.isTerminated()){
				break;
			}else{

			}
		}
		log.info("总页面:" + pages);
		FileWriter writer = FileWriter.create(new File("E:\\xunleixiazai\\a.json"), Charset.forName("utf8"));
		writer.write(JSON.toJSONString(stringBuilder.toString(), SerializerFeature.PrettyFormat));
	}

	private Integer getPage(String url) {
		String timestamp = String.valueOf(System.currentTimeMillis());
		JSONObject param = new JSONObject();
		param.put("pageNum",1);
		String body = HttpRequest.post(url).contentType("application/x-www-form-urlencoded")
				.header("appKey", appKey)
				.header("appSecret", appSecret)
				.header("sign", EncryptUtil.generateSign(new String[] {appKey, appSecret, timestamp}))
				.header("timestamp", timestamp).form("param", param).execute().body();
		return JSON.parseObject(body).getJSONObject("data").getInteger("pages");
	}

	public void fetchCompany() {
		//数据清洗
		log.info(">>>>>>>>>>>>>>>>>>>开始执行法人公司信息同步任务<<<<<<<<<<<<<<<<<<<<<");
		Integer pageNum = 1;
		Integer pages = null;
		String timestamp = String.valueOf(System.currentTimeMillis());
		Map<String, Object> inputBody = new HashMap<>();
		StringBuilder builder = new StringBuilder();
		//装填
		do {
			inputBody.put("pageNum", pageNum);
			//if (!isFrist) {
			//	inputBody.put("updateTime", updateTime);
			//}
			log.info("报文参数--->{}", inputBody);
			String body = HttpRequest.get(companyUrl).header("appKey", appKey)
					.header("appSecret", appSecret)
					.header("timestamp", timestamp)
					.header("sign", EncryptUtil.generateSign(new String[] {appKey, appSecret, timestamp}))
					.form(inputBody).execute().body();
			//builder.append(data);
			//log.info("已获取第{}页", pageNum);
			//pageNum = data.getInt("pageNum") + 1;
			//pages = data.getInt("pages");
		}
		while (pageNum <= pages);
		log.info("总页面：" + pages);
		FileWriter writer = new FileWriter(new File("E:\\xunleixiazai\\公司.json"), "utf8");
		writer.write(JSONUtil.formatJsonStr(builder.toString()));
	}

}
