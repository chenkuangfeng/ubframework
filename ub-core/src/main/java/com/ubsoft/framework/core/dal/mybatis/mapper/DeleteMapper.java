package com.ubsoft.framework.core.dal.mybatis.mapper;


import com.ubsoft.framework.core.dal.mybatis.provider.BaseDeleteProvider;
import org.apache.ibatis.annotations.DeleteProvider;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Param;

import java.io.Serializable;
import java.util.List;

@Mapper
public interface DeleteMapper <T> {

    /**
     * 删除一个对象
     * @param entity
     * @return
     */
    @DeleteProvider(
            type = BaseDeleteProvider.class,
            method = "delete"
    )
  int  delete(T entity);

    /**
     * 批量删除多个对象
     * @param entities
     * @return
     */
    @DeleteProvider(
            type = BaseDeleteProvider.class,
            method = "batchDelete"
    )
    int  batchDelete(@Param("list")List<T> entities);


    /**
     * 根据ID删除一个对象
     * @param clazz
     * @param id
     * @return
     */
    @DeleteProvider(
            type = BaseDeleteProvider.class,
            method = "deleteById"
    )
     int  deleteById(@Param("clazz")Class<T> clazz, @Param("id") Serializable id);


    /**
     * 根据id数组删除多个对象
     * @param clazz
     * @param ids
     * @return
     */
    @DeleteProvider(
            type = BaseDeleteProvider.class,
            method = "deleteByIds"
    )
    int  deleteByIds(@Param("clazz")Class<T> clazz, @Param("ids")  Serializable [] ids);


}
