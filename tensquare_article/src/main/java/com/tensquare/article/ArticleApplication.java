package com.tensquare.article;

import com.tensquare.util.IdWorker;
import org.mybatis.spring.annotation.MapperScan;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cloud.netflix.eureka.EnableEurekaClient;
import org.springframework.cloud.openfeign.EnableFeignClients;
import org.springframework.context.annotation.Bean;

/**
 * 2 * @Author: Cai
 * 3 * @Date: 2020/7/21 11:36
 * 4
 */
@SpringBootApplication
@MapperScan("com.tensquare.article.dao")
@EnableFeignClients
@EnableEurekaClient
public class ArticleApplication {
    public static void main(String args[]){
        SpringApplication.run(ArticleApplication.class,args);
    }
    @Bean
    public IdWorker createIdwork(){
        return new IdWorker(1,1);
    }
}
