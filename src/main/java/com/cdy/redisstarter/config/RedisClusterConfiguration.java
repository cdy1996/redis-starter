package com.cdy.redisstarter.config;

import com.cdy.redis.RedisClusterUtil;
import com.cdy.redis.RedisSingleUtil;
import com.cdy.redis.RedisUtil;
import lombok.extern.slf4j.Slf4j;
import org.apache.commons.lang3.StringUtils;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

import java.util.Arrays;

/**
 * cluster redis自动配置类
 * Created by 陈东一
 * 2019-11-10 19:52:10
 */
@Configuration
@Slf4j
public class RedisClusterConfiguration {
    
    @Bean
    public RedisUtil redisUtil(RedisProperties redisProperties) {
        RedisUtil redisUtil = null;
        if (StringUtils.isBlank(redisProperties.getMasterName()) || StringUtils.isBlank(redisProperties.getHostAndPorts())) {
            log.warn("RedisProperties not configure master");
            redisUtil = new RedisSingleUtil();
        } else {
            String[] hosts = Arrays.stream(redisProperties.getHostAndPorts().split(";")).map(e -> e.split(":")[0]).toArray(String[]::new);
            int[] ports = Arrays.stream(redisProperties.getHostAndPorts().split(";")).mapToInt(e -> Integer.parseInt(e.split(":")[1])).toArray();
            redisUtil = new RedisClusterUtil(hosts, ports, redisProperties.getPassword());
        }
        redisUtil.init();
        return redisUtil;
    }
    
}
