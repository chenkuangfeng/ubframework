package com.ubsoft.framework.core.dal.mybatis.mapper;


import com.ubsoft.framework.core.dal.mybatis.provider.BaseInsertProvider;
import org.apache.ibatis.annotations.InsertProvider;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Param;

import java.util.List;

@Mapper
public interface InsertMapper <T> {

    /**
     * 插入一条数据对象
     * @param entity
     * @return
     */
    @InsertProvider(
            type = BaseInsertProvider.class,
            method = "save"
    )
     int save(T entity);

    /**
     * 批量保存多个对象，只支持Mysql
     * @param entities
     * @return
     */
    @InsertProvider(
            type = BaseInsertProvider.class,
            method = "batchSave"
    )
     /** 只针对mysql数据库有效**/
     int batchSave(@Param("list") List<T> entities);

}
