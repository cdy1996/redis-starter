package com.cdy.redisstarter.subscribe;

/**
 * 发布接口
 * Created by 陈东一
 * 2018/11/10 0010 20:33
 */
public interface Publisher {
    
    void publish(String channel, String message);
}
