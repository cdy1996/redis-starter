package com.cdy.redisstarter.subscribeByList;

import com.cdy.common.util.cache.redis.RedisUtil;
import com.cdy.redisstarter.subscribe.Publisher;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;

/**
 * redis发布消息
 * Created by 陈东一
 * 2018/11/9 0009 21:44
 */
@Slf4j
public class RedisListPublisher implements Publisher {
    
    @Autowired
    RedisUtil redisUtil;
    
    public void publish(String channel, String message) {
        redisUtil.rpush(channel, message);
        log.info("发布一个消息" + channel + "-->" + message);
    }
}
