package com.lyoto.zhonghai.Bean.Annotation;

import java.lang.annotation.Documented;
import java.lang.annotation.ElementType;
import java.lang.annotation.Retention;
import java.lang.annotation.RetentionPolicy;
import java.lang.annotation.Target;

/**
 @author Lyoto
 @Description
 @Date 2022-04-08 17:27
 @Version
 **/
@Retention(RetentionPolicy.RUNTIME)
@Target(value = {ElementType.TYPE, ElementType.FIELD})
@Documented
public @interface NeedEncryption {
}
