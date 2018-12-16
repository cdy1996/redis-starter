package com.cdy.redisstarter.subscribeByList;

import com.cdy.redis.RedisUtil;
import lombok.extern.slf4j.Slf4j;
import org.apache.commons.lang3.StringUtils;
import org.springframework.context.ApplicationContext;
import org.springframework.context.ApplicationListener;
import org.springframework.context.event.ContextRefreshedEvent;

import java.lang.reflect.InvocationTargetException;
import java.lang.reflect.Method;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;

/**
 * redis订阅通知 通过redis的list实现
 * Created by 陈东一
 * 2018/11/9 20:03
 */
@Slf4j
public class SubscribeApplicationListener implements ApplicationListener<ContextRefreshedEvent> {
    
    private RedisUtil redisUtil;
    private ExecutorService executorService;
    private static Map<String, List<ObjectAndMethod>> observers = new HashMap<>();
    
    public SubscribeApplicationListener() {
        executorService = Executors.newCachedThreadPool();
    }
    
    @Override
    public void onApplicationEvent(ContextRefreshedEvent event) {
        ApplicationContext applicationContext = event.getApplicationContext();
        redisUtil = applicationContext.getBean(RedisUtil.class);
        String[] beanDefinitionNames = applicationContext.getBeanDefinitionNames();
        //查找容器中所有被注解的监听方法
        for (String beanDefinitionName : beanDefinitionNames) {
            Object bean = applicationContext.getBean(beanDefinitionName);
            Class<?> aClass = bean.getClass();
            Method[] methods = aClass.getMethods();
            for (Method method : methods) {
                RedisListListener annotation = method.getAnnotation(RedisListListener.class);
                if (annotation != null) {
                    String channel = StringUtils.isBlank(annotation.channel()) ? "*" : annotation.channel();
                    List<ObjectAndMethod> objects = observers.computeIfAbsent(channel, k -> new ArrayList<>());
                    objects.add(new ObjectAndMethod(bean, method));
                    log.info("添加一个订阅" + bean.getClass().getSimpleName() + "#" + method.getName());
                }
            }
        }
        //添加订阅
        for (Map.Entry<String, List<ObjectAndMethod>> stringListEntry : observers.entrySet()) {
            String channel = stringListEntry.getKey();
            List<ObjectAndMethod> value = stringListEntry.getValue();
            executorService.execute(() -> {
                while (true) {
                    String blpop = redisUtil.blpop(3600, channel);
                    for (ObjectAndMethod objectAndMethod : value) {
                        Object target = objectAndMethod.target;
                        Method method = objectAndMethod.method;
                        try {
                            method.invoke(target, blpop);
                        } catch (IllegalAccessException | InvocationTargetException e) {
                            log.error(e.getMessage(), e);
                        }
                    }
                }
            });
        }
    }
    
    static class ObjectAndMethod {
        Object target;
        Method method;
        
        ObjectAndMethod(Object target, Method method) {
            this.target = target;
            this.method = method;
        }
    }
}
