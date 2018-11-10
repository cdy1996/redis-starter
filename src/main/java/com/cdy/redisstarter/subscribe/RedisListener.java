package com.cdy.redisstarter.subscribe;

import java.lang.annotation.*;

@Target({ElementType.METHOD, ElementType.ANNOTATION_TYPE})
@Retention(RetentionPolicy.RUNTIME)
@Documented
public @interface RedisListener {
    
    /**
     * 监听的通道名称
     */
    String channel() default "*";
}
