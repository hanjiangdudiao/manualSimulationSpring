package com.fengyang.service;

import com.spring.*;

/**
 * @author: fengyang
 * @date: 2023/3/5 21:40
 * @desc:
 **/
@Component("userService")
@Scope("prototype")
public class UserService implements UserInterface, BeanNameAware {

    @Autowired
    private OrderService orderService;

    @FengyangValue("fengyangValue")
    private String fengyangValue;

    private String beanName;

    @Override
    public void setBeanName(String beanName) {
        this.beanName = beanName;
    }

    @Override
    public void test() {
        orderService.test();
        System.out.println(fengyangValue);
        System.out.println(beanName);
    }

}
