package com.spring;

/**
 * @author: fengyang
 * @date: 2023/3/6 22:07
 * @desc:
 **/
public interface BeanPostProcessor {

    default Object postProcessBeforeInitialization(Object bean, String beanName) {
        return bean;
    }

    default Object postProcessAfterInitialization(Object bean, String beanName) {
        return bean;
    }

}
