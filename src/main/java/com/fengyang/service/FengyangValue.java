package com.fengyang.service;

import java.lang.annotation.ElementType;
import java.lang.annotation.Retention;
import java.lang.annotation.RetentionPolicy;
import java.lang.annotation.Target;

/**
 * @author: fengyang
 * @date: 2023/3/6 22:38
 * @desc:
 **/
@Retention(RetentionPolicy.RUNTIME)
@Target(ElementType.FIELD)
public @interface FengyangValue {

    String value() default "";

}
