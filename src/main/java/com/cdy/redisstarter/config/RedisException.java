package com.cdy.redisstarter.config;

/**
 * 该项目的异常
 * Created by 陈东一
 * 2018/8/19 11:48
 */
public class RedisException extends RuntimeException {
    
    public RedisException(String message) {
        super(message);
    }
    
    public RedisException() {
        super();
    }
}
