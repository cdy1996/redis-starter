package com.cdy.redisstarter.subscribeByList;

import java.lang.annotation.*;

@Target({ElementType.METHOD, ElementType.ANNOTATION_TYPE})
@Retention(RetentionPolicy.RUNTIME)
@Documented
public @interface RedisListListener {
    
    /**
     * 监听的通道名称
     */
    String channel() default "*";
}
