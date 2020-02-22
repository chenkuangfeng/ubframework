package com.ubsoft.framework.core.service.impl;

import com.ubsoft.framework.core.dal.mybatis.mapper.BaseMapper;
import com.ubsoft.framework.core.exception.DataAccessException;
import com.ubsoft.framework.core.service.IBaseService;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Propagation;
import org.springframework.transaction.annotation.Transactional;

import java.io.Serializable;
import java.lang.reflect.ParameterizedType;
import java.lang.reflect.Type;
import java.util.List;

@Service
@Transactional
public class BaseService<T> implements IBaseService<T> {

    protected final Logger logger = LoggerFactory.getLogger(getClass());

    @Autowired
    protected  BaseMapper<T> baseMapper;
    protected Class<T> clazz;
    public BaseService() {
        // 当前对象的直接超类的 Type
        Type genericSuperclass = getClass().getGenericSuperclass();
        if (genericSuperclass instanceof ParameterizedType) {
            // 参数化类型
            ParameterizedType parameterizedType = (ParameterizedType) genericSuperclass;
            // 返回表示此类型实际类型参数的 Type 对象的数组
            Type[] actualTypeArguments = parameterizedType.getActualTypeArguments();
            this.clazz = (Class<T>) actualTypeArguments[0];
        } else {
            this.clazz = (Class<T>) genericSuperclass;
        }

    }
    @Override
    @Transactional(propagation = Propagation.SUPPORTS)
    public T get(Serializable id) {
       return baseMapper.get(clazz,id);
    }

    public T get(String filterPoperty,Object... param) {
        List<T> entities= baseMapper.select(clazz,filterPoperty,param);
        if(entities.size()==1){
            return  entities.get(0);
        }else{
            throw new DataAccessException(DataAccessException.MIN_ERROR_CODE_DAL, clazz.getName() + "存在多条记录。");
        }
    }

    @Override
    public T get(String properties, String filterProperty, Object... value) {
        List<T> entities= baseMapper.selectOption(clazz,properties,filterProperty,value);
        if(entities.size()==1){
            return  entities.get(0);
        }else{
            throw new DataAccessException(DataAccessException.MIN_ERROR_CODE_DAL, clazz.getName() + "存在多条记录。");
        }
    }

    @Override
    public List<T> select() {
        return baseMapper.selectAll(clazz);
    }

    @Override
    public List<T> select(String filterProperty, Object... value) {
        return  baseMapper.select(clazz,filterProperty,value);
    }

    @Override
    public List<T> select(String properties, String filterProperty, Object... value) {
        return  baseMapper.selectOption(clazz,properties,filterProperty,value);
    }

    @Override
    public int save(T entity) {
        return baseMapper.save(entity);
    }

    @Override
    public int save(List<T> entities) {
        int count=entities.size();
        int insertCount=0;
        for(T entity:entities){
            int j=update(entity);
            insertCount+=j;
        }
        if(insertCount!=count){
            throw new DataAccessException(DataAccessException.MIN_ERROR_CODE_DAL, clazz.getName() + "批量保存失败。");
        }
        return  insertCount;
    }

    @Override
    public int update(T entity) {
        return baseMapper.update(entity);
    }

    @Override
    public int update(List<T> entities) {
        int count=entities.size();
        int updateCount=0;
        for(T entity:entities){
            int j=update(entity);
            updateCount+=j;
        }
        if(updateCount!=count){
            throw new DataAccessException(DataAccessException.MIN_ERROR_CODE_DAL, clazz.getName() + "数据不存在或者已经修改。");
        }
        return  updateCount;
    }

    @Override
    public int delete(T entity) {
        return baseMapper.delete(entity);
    }

    @Override
    public int delete(List<T> entities) {
        return  baseMapper.batchDelete(entities);
    }

    @Override
    public int delete(Serializable id) {
        return baseMapper.deleteById(clazz,id);
    }

    @Override
    public int delete(Serializable[] ids) {
        return baseMapper.deleteByIds(clazz,ids);
    }

}
