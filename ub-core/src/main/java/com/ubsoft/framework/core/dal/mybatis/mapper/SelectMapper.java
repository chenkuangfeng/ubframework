package com.ubsoft.framework.core.dal.mybatis.mapper;


import com.ubsoft.framework.core.dal.model.Bio;
import com.ubsoft.framework.core.dal.mybatis.provider.BaseSelectProvider;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Param;
import org.apache.ibatis.annotations.SelectProvider;

import java.io.Serializable;
import java.util.List;
import java.util.Map;

@Mapper
public interface SelectMapper<T> {
    /**
     * 查询所有数据
     * @param clazz
     * @return
     */
    @SelectProvider(
            type = BaseSelectProvider.class,
            method = "selectAll"
    )
    List<T> selectAll(@Param("clazz") Class<T> clazz);
    /**
     * 根据属性条件查询数据
     * @param clazz 实体类
     * @param filterProperty 过滤属性名称，多个用"，"号分开
     * @param param 属性值
     * @return
     */
    @SelectProvider(
            type = BaseSelectProvider.class,
            method = "select"
    )
    List<T> select(@Param("clazz") Class<T> clazz,@Param("filter")String filterProperty,@Param("param")Object... param);

    /**
     *
     * @param clazz
     * @param property 指定查询的属性，多个用","分开
     * @param filterProperty  过滤属性名称，多个用"，"号分开
     * @param param 属性值
     * @return
     */
    @SelectProvider(
            type = BaseSelectProvider.class,
            method = "selectOption"
    )
    List<T> selectOption(@Param("clazz") Class<T> clazz,@Param("property")String property,@Param("filter")String filterProperty,@Param("param")Object... param);

//    @SelectProvider(
//            type = BaseSelectProvider.class,
//            method = "select"
//    )
//    List<T> select(@Param("clazz") Class<T> clazz, @Param("property") String property, @Param("where") String where, @Param("p") Map<String, Object> param, @Param("pType") Map<String, JdbcType> jdbcType);

    /**
     * 根据id获取一个对象
     * @param clazz
     * @param id
     * @return
     */
    @SelectProvider(
            type = BaseSelectProvider.class,
            method = "get"
    )
    T get(@Param("clazz") Class<T> clazz, @Param("id") Serializable id);


    @SelectProvider(
            type = BaseSelectProvider.class,
            method = "queryBio"
    )
    List<Bio> queryBio(@Param("sql")String sql, @Param("p") Map<String,Object> param);

    @SelectProvider(
            type = BaseSelectProvider.class,
            method = "query"
    )
    List<T> query(@Param("sql")String sql,@Param("p") Map<String,Object> param) ;




}
