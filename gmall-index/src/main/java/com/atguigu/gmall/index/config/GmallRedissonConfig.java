package com.atguigu.gmall.index.config;

import org.redisson.Redisson;
import org.redisson.api.RedissonClient;
import org.redisson.config.Config;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

/**
 * @author feilong
 * @create 2019-11-09 0:10
 */
@Configuration
public class GmallRedissonConfig {

    @Bean
    public RedissonClient redissonClient(){
        // 默认连接地址 127.0.0.1:6379
        Config config = new Config();
        // 可以用"rediss://"来启用SSL连接
        config.useSingleServer().setAddress("redis://192.168.186.128:6379");
        return Redisson.create(config);
    }
}

