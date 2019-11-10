package com.cdy.redisstarter.config;

import com.cdy.redis.RedisSentinelUtil;
import com.cdy.redis.RedisSingleUtil;
import com.cdy.redis.RedisUtil;
import lombok.extern.slf4j.Slf4j;
import org.apache.commons.lang3.StringUtils;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

import java.util.Arrays;
import java.util.Set;
import java.util.stream.Collectors;

/**
 * sentinel redis自动配置类
 * Created by 陈东一
 * 2019-11-10 19:52:10
 */
@Configuration
@Slf4j
public class RedisSentinelConfiguration {
    
    @Bean
    public RedisUtil redisUtil(RedisProperties redisProperties) {
        RedisUtil redisUtil = null;
        if (StringUtils.isBlank(redisProperties.getMasterName()) || StringUtils.isBlank(redisProperties.getHostAndPorts())) {
            log.warn("RedisProperties not configure master");
            redisUtil = new RedisSingleUtil();
        } else {
            Set<String> collect = Arrays.stream(redisProperties.getHostAndPorts().split(";")).collect(Collectors.toSet());
            redisUtil = new RedisSentinelUtil(collect, redisProperties.getMasterName(), redisProperties.getPassword());
        }
        redisUtil.init();
        return redisUtil;
    }
    
}
