package com.cdy.redisstarter.config;

import lombok.Data;
import org.springframework.boot.context.properties.ConfigurationProperties;

/**
 * redis的配置项
 * Created by 陈东一
 * 2018/8/19 11:35
 */
@Data
@ConfigurationProperties(prefix = "redis")
public class RedisProperties {
    private String host;
    private Integer port;
    private Integer expireTime;
}
