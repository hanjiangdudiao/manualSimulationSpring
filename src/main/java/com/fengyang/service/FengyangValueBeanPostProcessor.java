package com.fengyang.service;

import com.spring.BeanPostProcessor;
import com.spring.Component;

import java.lang.reflect.Field;

/**
 * @author: fengyang
 * @date: 2023/3/6 22:42
 * @desc:
 **/
@Component
public class FengyangValueBeanPostProcessor implements BeanPostProcessor {

    @Override
    public Object postProcessBeforeInitialization(Object bean, String beanName) {
        for(Field field : bean.getClass().getDeclaredFields()) {
            if(field.isAnnotationPresent(FengyangValue.class)) {
                field.setAccessible(true);
                try {
                    field.set(bean, field.getAnnotation(FengyangValue.class).value());
                } catch (IllegalAccessException e) {
                    e.printStackTrace();
                }
            }
        }
        return bean;
    }

}
