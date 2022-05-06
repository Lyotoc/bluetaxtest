package com.lyoto.zhonghai.Bean;

import java.math.BigDecimal;

import com.baomidou.mybatisplus.annotation.TableField;
import com.baomidou.mybatisplus.annotation.TableName;
import com.lyoto.zhonghai.Bean.Annotation.NeedEncryption;
import lombok.Data;

/**
 @author Lyoto
 @Description
 @Date 2022-05-05 11:11
 @Version
 **/
@TableName("test")
@Data
@NeedEncryption
public class Test {
	@TableField
	@NeedEncryption
	private BigDecimal money;
}
