package com.cdy.redisstarter.config;

import com.cdy.common.util.cache.redis.RedisSingleUtil;
import com.cdy.common.util.cache.redis.RedisUtil;
import org.apache.commons.lang3.StringUtils;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.Primary;

/**
 * 单机redis自动配置类
 * Created by 陈东一
 * 2018/8/19 12:02
 */
@Configuration
public class SingleRedisConfiguration {
    
    private Logger log = LoggerFactory.getLogger(this.getClass());
    
    @Bean
    @Primary
    public RedisUtil redisUtil(RedisProperties redisProperties) {
        RedisUtil redisUtil = null;
        if (StringUtils.isBlank(redisProperties.getHost()) || redisProperties.getPort() == null) {
            log.warn("RedisProperties not configure host or post");
            redisUtil = new RedisSingleUtil();
        } else {
            if (redisProperties.getExpireTime() == null) {
                redisUtil = new RedisSingleUtil(redisProperties.getHost(), redisProperties.getPort());
            } else {
                redisUtil = new RedisSingleUtil(redisProperties.getExpireTime(), redisProperties.getHost(), redisProperties.getPort());
            }
        }
        redisUtil.init();
        return redisUtil;
    }
    
}
