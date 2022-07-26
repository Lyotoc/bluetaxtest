package com.Lyoto.ioc.Business.Beans;

import javax.annotation.PostConstruct;

import lombok.Builder;
import lombok.Data;

import org.springframework.context.annotation.Import;

/**
 @author Lyoto
 @Description
 @Date 2022-07-14 15:48
 @Version
 **/
@Data
public class User {
private String userName;
private boolean isAdmin;

	/**
	 * @PostConstruct 随对象在IOC中的创建而加载
	 */
	@PostConstruct
	public void init(){
	long l = System.currentTimeMillis();
	this.userName = String.valueOf(l);
	this.isAdmin = (l & 1) == 0;

}
}
