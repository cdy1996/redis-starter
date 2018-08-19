package com.cdy.redisstarter.config;

import com.cdy.common.util.middleware.redis.JedisSingleUtil;
import com.cdy.common.util.middleware.redis.JedisUtil;
import org.apache.commons.lang3.StringUtils;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.autoconfigure.condition.ConditionalOnBean;
import org.springframework.boot.autoconfigure.condition.ConditionalOnProperty;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Conditional;
import org.springframework.context.annotation.Configuration;

/**
 * 单机redis自动配置类
 * Created by 陈东一
 * 2018/8/19 12:02
 */
@Configuration
public class SingleRedisConfiguration {
    
    private Logger log = LoggerFactory.getLogger(this.getClass());
    
    @Autowired
    RedisProperties redisProperties;
    
    @Bean
    public JedisUtil jedisUtil() {
        JedisUtil jedisUtil = null;
        if (StringUtils.isBlank(redisProperties.getHost()) || redisProperties.getPort() == null) {
            log.warn("RedisProperties not configure host or post");
            jedisUtil = new JedisSingleUtil();
        } else {
            if (redisProperties.getExpireTime() == null) {
                jedisUtil = new JedisSingleUtil(redisProperties.getHost(), redisProperties.getPort());
            }
            jedisUtil = new JedisSingleUtil(redisProperties.getExpireTime(), redisProperties.getHost(), redisProperties.getPort());
        }
        jedisUtil.init();
        return jedisUtil;
    }
    
}
