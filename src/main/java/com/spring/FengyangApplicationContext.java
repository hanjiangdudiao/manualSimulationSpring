package com.spring;

import java.io.File;
import java.lang.reflect.InvocationTargetException;
import java.net.URL;
import java.util.HashMap;
import java.util.Map;

/**
 * @author: fengyang
 * @date: 2023/3/5 21:41
 * @desc:
 **/
public class FengyangApplicationContext {

    private Class configClass;
    private Map<String, BeanDefinition> beanDefinitionMap = new HashMap<>();
    private Map<String, Object> singletonObjects = new HashMap<>();

    public FengyangApplicationContext(Class configClass) {
        this.configClass = configClass;
        scan(configClass);
        for(Map.Entry<String, BeanDefinition> entry : beanDefinitionMap.entrySet()) {
            String beanName = entry.getKey();
            BeanDefinition beanDefinition = entry.getValue();
            if(beanDefinition.getScope().equals("singleton")) {
                Object bean = createBean(beanName, beanDefinition);
                singletonObjects.put(beanName, bean);
            }
        }
    }

    private Object createBean(String beanName, BeanDefinition beanDefinition) {
        Class clazz = beanDefinition.getClazz();
        Object bean = null;
        try {
            bean = clazz.getConstructor().newInstance();
        } catch (InstantiationException e) {
            e.printStackTrace();
        } catch (IllegalAccessException e) {
            e.printStackTrace();
        } catch (InvocationTargetException e) {
            e.printStackTrace();
        } catch (NoSuchMethodException e) {
            e.printStackTrace();
        }
        return bean;
    }

    public Object getBean(String beanName) {
        if(!beanDefinitionMap.keySet().contains(beanName)) {
            throw new NullPointerException();
        }
        BeanDefinition beanDefinition = beanDefinitionMap.get(beanName);
        if(beanDefinition.getScope().equals("singleton")) {
            return singletonObjects.get(beanName);
        } else {
            return createBean(beanName, beanDefinition);
        }
    }

    private void scan(Class configClass) {
        if(configClass.isAnnotationPresent(ComponentScan.class)) {
            ComponentScan componentScanAnnotation = (ComponentScan) configClass.getAnnotation(ComponentScan.class);
            String component = componentScanAnnotation.value();
            component = component.replace(".", "/");
            System.out.println("component=" + component);
            ClassLoader classLoader = FengyangApplicationContext.class.getClassLoader();
            URL resource = classLoader.getResource(component);
            File file = new File(resource.getFile());
            if(file.isDirectory()) {
                for(File f : file.listFiles()) {
                    String absolutePath = f.getAbsolutePath();
                    absolutePath = absolutePath.substring(absolutePath.indexOf("com"), absolutePath.indexOf(".class"));
                    absolutePath = absolutePath.replace("\\", ".");
                    System.out.println("absolutePath=" + absolutePath);
                    try {
                        Class<?> clazz = classLoader.loadClass(absolutePath);
                        if(clazz.isAnnotationPresent(Component.class)) {
                            System.out.println("clazz=" + clazz);

                            Component componentAnnotation = clazz.getAnnotation(Component.class);
                            String beanName = componentAnnotation.value();

                            BeanDefinition beanDefinition = new BeanDefinition();
                            beanDefinition.setClazz(clazz);
                            if(clazz.isAnnotationPresent(Scope.class)) {
                                Scope scopeAnnotation = clazz.getAnnotation(Scope.class);
                                String scope = scopeAnnotation.value();
                                beanDefinition.setScope(scope);
                            } else {
                                beanDefinition.setScope("singleton");
                            }
                            beanDefinitionMap.put(beanName, beanDefinition);
                        }
                    } catch (ClassNotFoundException e) {
                        e.printStackTrace();
                    }
                }
            }
        }
    }

    public Class getConfigClass() {
        return configClass;
    }

    public void setConfigClass(Class configClass) {
        this.configClass = configClass;
    }

}
