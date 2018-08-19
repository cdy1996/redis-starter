package com.cdy.redisstarter.config;

import com.cdy.common.core.exception.CommonException;

/**
 * 该项目的异常
 * Created by 陈东一
 * 2018/8/19 11:48
 */
public class RedisException extends CommonException {
    
    public RedisException(String message) {
        super(message);
    }
    
    public RedisException() {
        super();
    }
}
