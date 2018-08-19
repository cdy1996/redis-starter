package com.cdy.redisstarter.config;

/**
 * redis常量类
 * Created by 陈东一
 * 2018/8/19 12:16
 */
public interface RedisType {
    //单机
    String SINGLE = "SINGLE";
    //主从
    String MASTER = "MASTER";
    //集群
    String CLUSTER = "CLUSTER";
}
