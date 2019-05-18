package com.cdy.redisstarter.actuator;

import com.cdy.redis.RedisUtil;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.actuate.health.AbstractHealthIndicator;
import org.springframework.boot.actuate.health.Health;
import redis.clients.jedis.Jedis;

/**
 * todo
 * Created by 陈东一
 * 2019/5/17 0017 21:52
 */
@Slf4j
public class CustomRedisHealthIndicator extends AbstractHealthIndicator {
    @Autowired
    RedisUtil redisUtil;
    
    @Override
    protected void doHealthCheck(Health.Builder builder) throws Exception {
        try (Jedis jedis = redisUtil.getJedis()){
            String info = jedis.info();
            builder.up().withDetail("info", info);
            log.info(info);
        }
    }
}
