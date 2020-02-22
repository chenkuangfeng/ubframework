package com.ubsoft.framework.core.dal.mybatis.mapper;


import com.ubsoft.framework.core.dal.mybatis.provider.BaseUpdateProvider;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Param;
import org.apache.ibatis.annotations.UpdateProvider;

@Mapper
public interface UpdateMapper<T> {

    @UpdateProvider(
            type = BaseUpdateProvider.class,
            method = "update"
    )

    int  update(T entity);


    @UpdateProvider(
            type = BaseUpdateProvider.class,
            method = "updateOption"
    )

    int updateOption(@Param("property")String property, @Param("entity")T entity);

}
