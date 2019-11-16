package com.atguigu.gmall.item.config;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

import java.util.concurrent.ArrayBlockingQueue;
import java.util.concurrent.ThreadPoolExecutor;
import java.util.concurrent.TimeUnit;

/**
 * @author feilong
 * @create 2019-11-12 1:15
 */
@Configuration
public class ThreadPoolconfig {

    @Bean
    public ThreadPoolExecutor threadPoolExecutor(){
        return new ThreadPoolExecutor(1000 , 5000 , 2 , TimeUnit.SECONDS , new ArrayBlockingQueue<>(Integer.MAX_VALUE/10));
    }
}
