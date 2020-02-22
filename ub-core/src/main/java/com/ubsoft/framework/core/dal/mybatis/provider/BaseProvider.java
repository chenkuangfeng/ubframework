package com.ubsoft.framework.core.dal.mybatis.provider;

import com.ubsoft.framework.core.dal.entity.BaseEntity;
import com.ubsoft.framework.core.util.UUIDUtil;

import java.lang.reflect.Method;
import java.sql.Timestamp;
import java.util.HashMap;
import java.util.Map;

public class BaseProvider<T> {


    public Map<String, Method> getMethodMap() {
        return methodMap;
    }

    private Map<String, Method> methodMap = new HashMap();

    public void addMethodMap(String methodName, Method method) {
        this.methodMap.put(methodName, method);
    }



    protected void defaultSaveEntity(BaseEntity entity){
            BaseEntity baseEntity=(BaseEntity)entity;
            baseEntity.setVersion(0);
            Timestamp currentTime= new Timestamp(System.currentTimeMillis());
            baseEntity.setCreatedTime(currentTime);
            // baseEntity.setCreatedBy("chenkf");
            baseEntity.setId(UUIDUtil.randomUUID());
            baseEntity.setStatus("1");
    }
    protected void defaultUpdateEntity(BaseEntity entity){
        BaseEntity baseEntity=(BaseEntity)entity;
        baseEntity.setVersion(baseEntity.getVersion()+1);
        Timestamp currentTime= new Timestamp(System.currentTimeMillis());
        baseEntity.setUpdatedTime(currentTime);
    }

}
