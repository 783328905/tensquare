package com.tensquare.notice;

import com.baomidou.mybatisplus.extension.plugins.PaginationInterceptor;
import com.tensquare.notice.appcontext.ApplicationContextProvider;
import com.tensquare.notice.netty.NettyServer;
import com.tensquare.util.IdWorker;
import org.mybatis.spring.annotation.MapperScan;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cloud.netflix.eureka.EnableEurekaClient;
import org.springframework.cloud.openfeign.EnableFeignClients;
import org.springframework.context.annotation.Bean;

/**
 * 2 * @Author: Cai
 * 3 * @Date: 2020/7/24 11:29
 * 4
 */
@SpringBootApplication
@EnableFeignClients
@MapperScan("com.tensquare.notice.dao")
@EnableEurekaClient
public class NoticeApplication {
    public static void main(String[] args) {
        SpringApplication.run(NoticeApplication.class,args);
        NettyServer server = ApplicationContextProvider.getApplicationContext().getBean(NettyServer.class);
        try {
            server.start(12345);
        } catch (Exception e) {
            e.printStackTrace();
        }
    }
    @Bean
    public IdWorker idWorkker(){
        return new IdWorker(1, 1);
    }

}
