package com.ubsoft.framework.core.cache.impl;

import com.ubsoft.framework.core.cache.ICache;
import com.ubsoft.framework.core.context.AppContext;
import com.ubsoft.framework.core.exception.ComException;
import net.sf.ehcache.Cache;
import net.sf.ehcache.CacheManager;
import net.sf.ehcache.Element;

import java.util.List;

public class Ehcache implements ICache {

    private CacheManager cacheManager;
    private String defaultCache="DEFAULT_";
    public Ehcache(){
         cacheManager= AppContext.getBean(CacheManager.class);
    }

    @Override
    public <T> T get(Object key, Class<T> clazz) {
        Cache cache = cacheManager.getCache(defaultCache);
        return (T) cache.get(key);
    }

    @Override
    public <T> T get(String name, Object key, Class<T> clazz) {
        Cache cache = cacheManager.getCache(name);
        return (T)cache.get(key);
    }

    @Override
    public <T> List<T> gets(String name, Object key, Class<T> clazz) {
        Cache cache = cacheManager.getCache(name);
        return (List<T>)cache.get(key);
    }

    @Override
    public <T> List<T> gets(Object key, Class<T> clazz) {
        Cache cache = cacheManager.getCache(defaultCache);
        return (List<T>)cache.get(key);
    }

    @Override
    public void put(String name, Object key, Object value) {
        Cache cache = cacheManager.getCache(name);
        cache.put(new Element(key, value));
    }

    @Override
    public void put(Object key, Object value) {
        Cache cache = cacheManager.getCache(defaultCache);
        cache.put(new Element(key, value));
    }




    @Override
    public boolean remove(String name, Object key) {
        Cache cache = cacheManager.getCache(name);
        return cache.remove(key);
    }

    @Override
    public boolean remove(Object key) {
        Cache cache = cacheManager.getCache(defaultCache);
        return cache.remove(key);
    }

    @Override
    public void clean(String name) {
        Cache cache = cacheManager.getCache(name);
         cache.removeAll();
    }

    @Override
    public boolean expire(String name, int time) {
        throw new ComException(ComException.MIN_ERROR_CODE_GLOBAL,"该方法Ehcache不支持");
    }
}
