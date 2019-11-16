package com.atguigu.gmall.index.config;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import redis.clients.jedis.JedisPool;

/**
 * @author feilong
 * @create 2019-11-08 21:03
 */
@Configuration
public class GmallJedisConfig {

    @Bean
    public JedisPool jedisPool(){

//        GenericObjectPoolConfig poolConfig = new GenericObjectPoolConfig();
       return new JedisPool("192.168.186.128" , 6379);
    }
}
