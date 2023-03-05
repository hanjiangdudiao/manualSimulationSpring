package com.fengyang;

import com.fengyang.service.UserService;
import com.spring.FengyangApplicationContext;

/**
 * @author: fengyang
 * @date: 2023/3/5 21:40
 * @desc:
 **/
public class Test {

    public static void main(String[] args) {
        FengyangApplicationContext applicationContext = new FengyangApplicationContext(AppConfig.class);
        UserService userService = (UserService)applicationContext.getBean("userService");
        userService.test();
    }

}
