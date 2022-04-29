package com.lyoto.zhonghai.Bean;

import java.io.Serializable;
import java.util.Date;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Index;
import javax.persistence.Table;

import com.alibaba.fastjson.annotation.JSONField;
import lombok.Data;

/**
 @author Lyoto
 @Description
 @Date 2022-01-13 11:09
 @Version
 **/
	@Data
public class UserManagerPo implements Serializable {
		@JSONField(name = "fullName")
		private String  managerName;
		@JSONField(name = "username")
		private String  account;
		@JSONField(name = "email")
		private String  email;
}
