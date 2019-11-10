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
    private String password;
    
    // sentienl masterName
    private String masterName;
    // sentienl/cluster 127.0.0.1:7300;127.0.0.2:7300
    private String hostAndPorts;
    
    
    
    
    
    
}
