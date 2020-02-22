package com.ubsoft.framework.core.dal.mybatis.mapper;


import com.ubsoft.framework.core.dal.model.Bio;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Param;

import java.util.List;
import java.util.Map;

@Mapper
public interface BaseMapper<T> extends SelectMapper<T>,InsertMapper<T>,UpdateMapper<T>,DeleteMapper<T> {



}
