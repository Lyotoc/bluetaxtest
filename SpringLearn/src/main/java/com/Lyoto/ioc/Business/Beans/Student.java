package com.Lyoto.ioc.Business.Beans;

/**
 @author Lyoto
 @Description
 @Date 2022-07-15 15:24
 @Version
 **/
public class Student {
	public Student(){
		System.out.println("Constructor……");
	}
	public void init() {
		System.out.println("init……");
	}
	public void destory(){
		System.out.println("destory……");
	}

	}
