package com.cdy.redisstarter.config;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.context.annotation.ImportSelector;
import org.springframework.core.type.AnnotationMetadata;

import java.util.Map;

import static com.cdy.redisstarter.config.RedisType.*;

/**
 * 用于筛选什么类型的redis配置类
 * Created by 陈东一
 * 2018/8/19 12:17
 */
public class RedisSelector implements ImportSelector {
    private Logger log = LoggerFactory.getLogger(this.getClass());
    
    @Override
    public String[] selectImports(AnnotationMetadata importingClassMetadata) {
        Map<String, Object> annotationAttributes = importingClassMetadata.getAnnotationAttributes(EnableRedisUtil.class.getName());
        String value = (String) annotationAttributes.get("value");
        log.info("RedisUtil type is {} .", value);
        switch (value) {
            case SINGLE:
                return new String[]{SingleRedisConfiguration.class.getName()};
            case MASTER:
                break;
            case CLUSTER:
                break;
            default:
                break;
        }
        return new String[0];
    }
}
