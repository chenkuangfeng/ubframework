package com.ubsoft.framework.core.service;

import java.io.Serializable;
import java.util.List;

public interface IBaseService<T> {
    /**
     * 根据ID获取bean
     *
     * @param id
     * @return
     */
    T get(Serializable id);

    /**
     * 根据property过滤条件获取Bean
     *
     * @param filterProperty 过滤属性名称，多个用“,”号分开。
     * @param value          属性对应的值
     * @return
     */
    T get(String filterProperty, Object... value);

    /**
     * 根据property过滤条件获取指定属性的Bean
     *
     * @param properties     指定要获取对象的属性，多个用“,”号分开
     * @param filterProperty 过滤属性名称，多个用“,”号分开。
     * @param value          属性对应的值
     * @return
     */
    T get(String properties, String filterProperty, Object... value);

    /**
     * 查询所有数据列表
     *
     * @return
     */
    List<T> select();

    /**
     * 根据property过滤条件获取Bean列表
     *
     * @param filterProperty 过滤属性名称，多个用“,”号分开。
     * @param value          属性对应的值
     * @return
     */
    List<T> select(String filterProperty, Object... value);

    /**
     * 根据property过滤条件获取指定字段的Bean列表
     *
     * @param properties     指定要获取对象的属性，多个用“,”号分开
     * @param filterProperty 过滤属性名称，多个用“,”号分开。
     * @param value          属性对应的值
     * @return
     */
    List<T> select(String properties, String filterProperty, Object... value);

    /**
     * 保存一个实体
     * @param entity
     * @return
     */
    int save(T entity);

    /**
     * 保存多个实体
     * @param entities
     * @return
     */
    int save(List<T> entities);
    /**
     * 更新一个实体
     * @param entity
     * @return
     */
    int update(T entity);
    /**
     * 保存多个实体（循环保存,不是批量)
     * @param entities
     * @return
     */
    int update(List<T> entities);

    /**
     * 删除一个实体
     * @param entity
     * @return
     */
    int delete(T entity);
    /**
     * 删除多个实体
     * @param entities
     * @return
     */
    int delete(List<T> entities);

    /**
     * 根据ID删除一个实体
     * @param id
     * @return
     */
    int delete(Serializable id);
    /**
     * 根据id数组删除多个实体
     * @param ids
     * @return
     */
    int delete( Serializable [] ids);


}
