package com.fengyang.service;

import com.spring.BeanPostProcessor;
import com.spring.Component;

import java.lang.reflect.InvocationHandler;
import java.lang.reflect.Method;
import java.lang.reflect.Proxy;

/**
 * @author: fengyang
 * @date: 2023/3/6 22:10
 * @desc:
 **/
@Component
public class FengyangBeanPostProcessor implements BeanPostProcessor {

    @Override
    public Object postProcessAfterInitialization(Object bean, String beanName) {
        if(beanName.equals("userService")) {
            Object proxyBean = Proxy.newProxyInstance(FengyangBeanPostProcessor.class.getClassLoader(), bean.getClass().getInterfaces(), new InvocationHandler() {
                @Override
                public Object invoke(Object proxy, Method method, Object[] args) throws Throwable {
                    System.out.println("切面");
                    return method.invoke(bean, args);
                }
            });
            return proxyBean;
        }
        return bean;
    }

}
