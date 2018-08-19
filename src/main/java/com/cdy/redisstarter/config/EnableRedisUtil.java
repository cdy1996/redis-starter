package com.cdy.redisstarter.config;

import org.springframework.boot.context.properties.EnableConfigurationProperties;
import org.springframework.context.annotation.Import;

import java.lang.annotation.*;

/**
 * redis自动配置导入注解
 *
 * Created by 陈东一
 * 2018/8/19 12:10
 */
@Target(ElementType.TYPE)
@Retention(RetentionPolicy.RUNTIME)
@Documented
@EnableConfigurationProperties({RedisProperties.class})
@Import({RedisSelector.class})
public @interface EnableRedisUtil {
    /**
     * redis的类型
     * @return
     */
    String value() default RedisType.SINGLE;
}
