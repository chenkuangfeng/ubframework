package com.ubsoft.framework.core.cache.impl;

import com.ubsoft.framework.core.cache.ICache;
import org.springframework.data.redis.core.RedisTemplate;

import java.util.List;
import java.util.concurrent.TimeUnit;

public class RedisCache implements ICache {

    private RedisTemplate redisTemplate;

    public RedisCache(RedisTemplate redisTemplate){

        this.redisTemplate=redisTemplate;
    }

    @Override
    public <T> T get(Object key, Class<T> clazz) {
        return (T)redisTemplate.opsForValue().get(key);
    }

    @Override
    public <T> T get(String name, Object key, Class<T> clazz) {
        return (T)redisTemplate.opsForHash().get(name,key);
    }

    @Override
    public void put(String name, Object key, Object value) {
         redisTemplate.opsForHash().put(name,key,value);
    }

    @Override
    public void put(Object key, Object value) {
        redisTemplate.opsForValue().set(key,value);
    }

    @Override
    public <T> List<T> gets(String name, Object key, Class<T> clazz) {
        return (List<T>)redisTemplate.opsForHash().get(name,key);
    }

    @Override
    public <T> List<T> gets(Object key, Class<T> clazz) {
        return  (List<T>)redisTemplate.opsForValue().get(key);
    }

    @Override
    public boolean remove(String name, Object key) {
         redisTemplate.opsForHash().delete(name,key);
         return true;
    }

    @Override
    public boolean remove(Object key) {
        return false;
    }

    @Override
    public void clean(String name) {
        redisTemplate.opsForHash().keys(name).clear();
    }

    @Override
    public boolean expire(String name, int time) {
        return redisTemplate.expire(name,time, TimeUnit.SECONDS);
    }
}
