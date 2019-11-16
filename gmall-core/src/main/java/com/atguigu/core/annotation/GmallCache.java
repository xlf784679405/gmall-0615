package com.atguigu.core.annotation;

import org.springframework.core.annotation.AliasFor;

import java.lang.annotation.*;

/**
 * @author feilong
 * @create 2019-11-11 16:55
 */
@Target({ElementType.METHOD})
@Retention(RetentionPolicy.RUNTIME)
@Documented
public @interface GmallCache {
    /**
     * 缓存的前缀
     *
     * @return
     */
    String prefix() default "cache";

    /**
     * 单位是秒
     *
     * @return
     */
    long timeout() default 300l;

    /**
     * 为了防止缓存雪崩，过期时间的随机值范围
     *
     * @return
     */
    long random() default 300l;


}
