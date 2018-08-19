package com.cdy.redisstarter.config;

import org.springframework.boot.context.properties.ConfigurationProperties;

/**
 * redis的配置项
 * Created by 陈东一
 * 2018/8/19 11:35
 */
@ConfigurationProperties(prefix = "redis")
public class RedisProperties {
    private String host;
    private Integer port;
    private Integer expireTime;
    
    public String getHost() {
        return host;
    }
    
    public void setHost(String host) {
        this.host = host;
    }
    
    public Integer getPort() {
        return port;
    }
    
    public void setPort(Integer port) {
        this.port = port;
    }
    
    public Integer getExpireTime() {
        return expireTime;
    }
    
    public void setExpireTime(Integer expireTime) {
        this.expireTime = expireTime;
    }
}
