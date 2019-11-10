package com.cdy.redisstarter.config;

import lombok.extern.slf4j.Slf4j;
import org.springframework.context.annotation.ImportSelector;
import org.springframework.core.type.AnnotationMetadata;

import java.util.Map;

import static com.cdy.redisstarter.config.RedisType.*;

/**
 * 用于筛选什么类型的redis配置类
 * Created by 陈东一
 * 2018/8/19 12:17
 */
@Slf4j
public class RedisSelector implements ImportSelector {
    @Override
    public String[] selectImports(AnnotationMetadata importingClassMetadata) {
        Map<String, Object> annotationAttributes = importingClassMetadata.getAnnotationAttributes(EnableRedisUtil.class.getName());
        String value = (String) annotationAttributes.get("value");
        log.info("Redis type is {} .", value);
        switch (value) {
            case SINGLE:
                return new String[]{RedisSingleConfiguration.class.getName()};
            case MASTER:
                return new String[]{RedisSentinelConfiguration.class.getName()};
            case CLUSTER:
                return new String[]{RedisClusterConfiguration.class.getName()};
            default:
                return new String[]{RedisSingleConfiguration.class.getName()};
        }
        
    }
}
