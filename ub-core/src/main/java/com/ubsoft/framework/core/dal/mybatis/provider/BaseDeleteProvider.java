package com.ubsoft.framework.core.dal.mybatis.provider;

import com.ubsoft.framework.core.dal.model.EntityColumn;
import com.ubsoft.framework.core.dal.model.EntityTable;
import com.ubsoft.framework.core.dal.support.EntityHelper;
import com.ubsoft.framework.core.util.BeanUtil;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class BaseDeleteProvider<T> extends BaseProvider {
    private  static Map<Class<?>,String> cacheDeleteSql= new HashMap<Class<?>,String>();
    public String delete(T entity){
        String cacheSql=cacheDeleteSql.get(entity.getClass());
        if(cacheSql!=null){
            return cacheSql;
        }
        StringBuilder sql = new StringBuilder();
        sql.append("DELETE FROM ");
        EntityTable table = EntityHelper.getEntityTable(entity.getClass());
        sql.append(table.getName()).append(" ");
        sql.append(" WHERE ");
        EntityColumn pkColumn=table.getPkColumn();
        sql.append(pkColumn.getName()).append("=").append(EntityHelper.getColumnHolder(pkColumn));
        String result=sql.toString();
        cacheDeleteSql.put(entity.getClass(),result);
        return result;

    }


    public String batchDelete(Map map){
        List<T> entities =(List<T>)map.get("list");
        EntityTable table = EntityHelper.getEntityTable(entities.get(0).getClass());
        StringBuilder sql = new StringBuilder();
        sql.append("DELETE FROM ");
        sql.append(table.getName()).append(" ");
        EntityColumn pkColumn=table.getPkColumn();
        String idsStr="(";
        int i=0;
        for(T entity:entities){
            idsStr+="'"+ BeanUtil.getProperty(entity,table.getPkColumn().getProperty())+"'";
            if(i<entities.size()-1){
                idsStr+=",";
            }
        }
        idsStr+=")";
        String finalIdsStr = idsStr;
        sql.append(" WHERE ");
        sql.append(pkColumn.getName()).append(" IN ").append(idsStr);
        return sql.toString();
    }

    public String deleteById(Map map){
        Class<T> clazz=(Class<T>)map.get("clazz");
        EntityTable table = EntityHelper.getEntityTable(clazz);
        StringBuilder sql = new StringBuilder();
        sql.append("DELETE FROM ");
        sql.append(table.getName());
        sql.append(" WHERE ").append(table.getPkColumn().getName()).append(" = #{id} ");
        return  sql.toString();
    }
    public String deleteByIds(Map map){
        Class<T> clazz=(Class<T>)map.get("clazz");
        String [] ids=(String [])map.get("ids");
        EntityTable table = EntityHelper.getEntityTable(clazz);
        String idsStr="(";
       for(int i=0;i<ids.length;i++){
           idsStr+="'"+ids[i]+"'";
           if(i<ids.length-1){
               idsStr+=",";
           }

       }
        idsStr+=")";

        StringBuilder sql = new StringBuilder();
        sql.append("DELETE FROM ");
        sql.append(table.getName());
        sql.append(" WHERE ").append(table.getPkColumn().getName()).append(" IN ").append(idsStr);
        return  sql.toString();
    }

}
