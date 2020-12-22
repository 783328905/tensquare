package com.tensquare.user;

import org.mybatis.spring.annotation.MapperScan;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

/**
 * 2 * @Author: Cai
 * 3 * @Date: 2020/7/22 14:07
 * 4
 */
@SpringBootApplication
@MapperScan("com.tensquare.user.dao")
public class UserApplication {
    public static void main(String[] args) {
        SpringApplication.run(UserApplication.class,args);
    }
}
