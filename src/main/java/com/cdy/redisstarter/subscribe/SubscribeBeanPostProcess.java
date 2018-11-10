package com.cdy.redisstarter.subscribe;

import com.cdy.common.util.cache.redis.RedisUtil;
import lombok.extern.slf4j.Slf4j;
import org.apache.commons.lang3.StringUtils;
import org.springframework.beans.BeansException;
import org.springframework.beans.factory.config.BeanPostProcessor;
import org.springframework.context.ApplicationContext;
import org.springframework.context.ApplicationContextAware;
import redis.clients.jedis.Jedis;

import java.lang.reflect.Method;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;

/**
 * redis监听处理的bean后置处理
 * Created by 陈东一
 * 2018/11/9 0009 20:03
 */
@Slf4j
public class SubscribeBeanPostProcess implements BeanPostProcessor, ApplicationContextAware {
    
    private RedisUtil redisUtil;
    private ExecutorService executorService;
    public SubscribeBeanPostProcess() {
        executorService = Executors.newCachedThreadPool();
    }
    
    @Override
    public Object postProcessBeforeInitialization(Object bean, String beanName) throws BeansException {
        return bean;
    }
    
    @Override
    public Object postProcessAfterInitialization(Object bean, String beanName) throws BeansException {
        Class<?> aClass = bean.getClass();
        Method[] methods = aClass.getMethods();
        for (Method method : methods) {
            RedisListener annotation = method.getAnnotation(RedisListener.class);
            if (annotation != null) {
                Jedis jedis = redisUtil.getJedis();
                String channel = StringUtils.isBlank(annotation.channel())?"*":annotation.channel();
                log.info("添加一个订阅"+aClass.getSimpleName()+"#"+method.getName());
                executorService.execute(()->jedis.subscribe(new RedisSubscribe(bean, method), channel));
            }
        }
        return bean;
    }
    
    @Override
    public void setApplicationContext(ApplicationContext applicationContext) throws BeansException {
        this.redisUtil = applicationContext.getBean(RedisUtil.class);
    }
    
   
}
