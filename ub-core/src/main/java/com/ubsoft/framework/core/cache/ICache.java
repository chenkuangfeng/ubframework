package com.ubsoft.framework.core.cache;

import java.util.List;

;

public interface ICache {

    /**
     * 根据Key获取对象
     * @param key
     * @param clazz
     * @param <T>
     * @return
     */
    <T> T get(Object key, Class<T> clazz);

    /**
     * 根据Name和Key获取对象
     * @param name
     * @param key
     * @param clazz
     * @param <T>
     * @return
     */
    <T> T get(String name, Object key, Class<T> clazz);

    /**
     * 指定Name插入缓存对象
     * @param name
     * @param key
     * @param value
     */
    void put(String name, Object key,Object value);

    /**
     * 插入缓存对象
     * @param key
     * @param value
     */
    void put(Object key,Object value);

    /**
     * 指定Name获取缓存对象列表
     * @param name
     * @param key
     * @param clazz
     * @param <T>
     * @return
     */
    <T>  List<T> gets(String name, Object key, Class<T> clazz);

    /**
     * 获取缓存对象列表
     * @param key
     * @param clazz
     * @param <T>
     * @return
     */
    <T>  List<T> gets(Object key, Class<T> clazz);

    /**
     * 指定名称删除缓存对象
     * @param name
     * @param key
     * @return
     */
    boolean remove(String name, Object key);

    /**
     * 指定key获删除缓存对象
     * @param key
     * @return
     */
    boolean remove(Object key);

    /**
     * 指定名称清空对象缓存
     * @param name
     */
    void clean(String name);

    /**
     * 根据key和Name设置缓存神剑
     * @param keyName
     * @param time 秒
     * @return
     */
     boolean expire (String keyName,int time);
}
