package com.cdy.redisstarter.subscribe;

import com.cdy.redis.RedisUtil;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import redis.clients.jedis.Jedis;

/**
 * redis发布消息
 * Created by 陈东一
 * 2018/11/9 0009 21:44
 */
@Slf4j
public class RedisPublisher implements Publisher{
    
    @Autowired
    RedisUtil redisUtil;
    
    public void publish(String channel, String message) {
        Jedis jedis = redisUtil.getJedis();
        try {
            log.info("发布一个消息"+channel+"-->"+message);
            jedis.publish(channel, message);
        } finally {
            jedis.close();
        }
    }
}
