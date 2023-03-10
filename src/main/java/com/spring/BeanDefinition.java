package com.spring;

/**
 * @author: fengyang
 * @date: 2023/3/5 22:26
 * @desc:
 **/
public class BeanDefinition {

    private Class clazz;
    private String scope;
    private Boolean isLazy;

    public Class getClazz() {
        return clazz;
    }

    public void setClazz(Class clazz) {
        this.clazz = clazz;
    }

    public String getScope() {
        return scope;
    }

    public void setScope(String scope) {
        this.scope = scope;
    }

    public Boolean getLazy() {
        return isLazy;
    }

    public void setLazy(Boolean lazy) {
        isLazy = lazy;
    }

}
