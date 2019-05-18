package com.cdy.redisstarter.actuator;

import org.springframework.boot.actuate.autoconfigure.HealthIndicatorAutoConfiguration;
import org.springframework.boot.autoconfigure.AutoConfigureBefore;
import org.springframework.boot.autoconfigure.condition.ConditionalOnProperty;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

/**
 * todo
 * Created b 陈东一
 * 2019/5/17 0017 22:33
 */
@AutoConfigureBefore(HealthIndicatorAutoConfiguration. class)
@Configuration
@ConditionalOnProperty(value = "redis.health", havingValue = "true")
public class CustomRedisHealthConfiguration {

    @Bean
    public CustomRedisHealthIndicator customRedisHealthIndicator(){
        return new CustomRedisHealthIndicator();
    }

}
