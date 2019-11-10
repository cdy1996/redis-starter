package com.cdy.redisstarter.config;

import com.cdy.redis.RedisSingleUtil;
import com.cdy.redis.RedisUtil;
import lombok.extern.slf4j.Slf4j;
import org.apache.commons.lang3.StringUtils;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

/**
 * 单机redis自动配置类
 * Created by 陈东一
 * 2018/8/19 12:02
 */
@Configuration
@Slf4j
public class RedisSingleConfiguration {
    
    
    @Bean
    public RedisUtil redisUtil(RedisProperties redisProperties) {
        RedisUtil redisUtil = null;
        if (StringUtils.isBlank(redisProperties.getHost()) || redisProperties.getPort() == null) {
            log.warn("RedisProperties not configure host or post");
            redisUtil = new RedisSingleUtil();
        } else {
            redisUtil = new RedisSingleUtil(redisProperties.getHost(), redisProperties.getPort());
        }
        redisUtil.init();
        return redisUtil;
    }
    
}
