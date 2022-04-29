package com.lyoto.zhonghai;

import org.mybatis.spring.annotation.MapperScan;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

@SpringBootApplication
@MapperScan("com.lyoto.zhonghai.Service.mapper")
public class ZhonghaiApplication {
	public static void main(String[] args) {
		SpringApplication.run(ZhonghaiApplication.class, args);
	}

}
