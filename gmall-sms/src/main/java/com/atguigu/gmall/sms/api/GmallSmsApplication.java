package com.atguigu.gmall.sms.api;

import org.mybatis.spring.annotation.MapperScan;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cloud.client.discovery.EnableDiscoveryClient;
import springfox.documentation.swagger2.annotations.EnableSwagger2;

@EnableDiscoveryClient
@EnableSwagger2
@SpringBootApplication
@MapperScan(basePackages = "com.atguigu.gmall.sms.dao")
public class GmallSmsApplication {

    public static void main(String[] args) {
        SpringApplication.run(GmallSmsApplication.class, args);
    }

}
