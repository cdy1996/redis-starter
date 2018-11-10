package com.cdy.redisstarter.subscribe;

import lombok.extern.slf4j.Slf4j;
import redis.clients.jedis.JedisPubSub;

import java.lang.reflect.InvocationTargetException;
import java.lang.reflect.Method;

/**
 * redis监听通道模板
 * Created by 陈东一
 * 2018/11/9 0009 20:04
 */
@Slf4j
public class RedisSubscribe extends JedisPubSub {
    
    private Object target;
    private Method method;
    
    public RedisSubscribe(Object target, Method method) {
        this.target = target;
        this.method = method;
    }
    
    @Override
    public void onMessage(String channel, String message) {
        try {
            method.invoke(target, channel);
        } catch (IllegalAccessException | InvocationTargetException e) {
            log.error(e.getMessage(), e);
        }
    }
    
    @Override
    public void onSubscribe(String channel, int subscribedChannels) {
        log.info("订阅通道"+channel);
    }
    
    @Override
    public void onUnsubscribe(String channel, int subscribedChannels) {
        log.info("取消订阅"+channel);
    }
}
