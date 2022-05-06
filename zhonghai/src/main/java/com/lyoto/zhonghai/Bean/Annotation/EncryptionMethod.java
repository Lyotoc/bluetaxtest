package com.lyoto.zhonghai.Bean.Annotation;

import java.lang.annotation.ElementType;
import java.lang.annotation.Retention;
import java.lang.annotation.RetentionPolicy;
import java.lang.annotation.Target;

/**
 @author Lyoto
 @Description
 @Date 2022-05-06 14:50
 @Version
 **/
@Retention(RetentionPolicy.RUNTIME)
@Target(ElementType.METHOD)
public @interface EncryptionMethod {
}
