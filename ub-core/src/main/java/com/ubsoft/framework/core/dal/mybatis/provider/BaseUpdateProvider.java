package com.ubsoft.framework.core.dal.mybatis.provider;

import com.ubsoft.framework.core.dal.entity.BaseEntity;
import com.ubsoft.framework.core.dal.model.EntityColumn;
import com.ubsoft.framework.core.dal.model.EntityTable;
import com.ubsoft.framework.core.dal.support.EntityHelper;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class BaseUpdateProvider<T> extends BaseProvider {
    private  static Map<Class<?>,String> cacheUpdateSql= new HashMap<Class<?>,String>();
    public String update(T entity){
        if(entity instanceof BaseEntity){
            defaultUpdateEntity((BaseEntity)entity);
        }
        String cacheSql=cacheUpdateSql.get(entity.getClass());
        if(cacheSql!=null){
            return cacheSql;
        }
        StringBuilder sql = new StringBuilder();
        sql.append("UPDATE ");
        EntityTable table = EntityHelper.getEntityTable(entity.getClass());
        sql.append(table.getName()).append(" ");
        List<EntityColumn> columns = table.getEntityColumns();
        sql.append(" SET ");
        int i=0;
        EntityColumn pkColumn=table.getPkColumn();
        EntityColumn verColumn=table.getVerColumn();
        for(EntityColumn column:columns){
            if(column.isId()){

            }else {
                if(column.isUpdatable()) {
                    if(column.isVersion()){
                        sql.append(column.getName()).append("=").append(column.getName()).append("+1");
                    }else {
                        sql.append(column.getName()).append("=").append(EntityHelper.getColumnHolder(column));
                    }
                    if (i < columns.size() - 1)
                        sql.append(",");
                }
            }
            i++;
        }
        sql.append(" WHERE ");
        sql.append(pkColumn.getName()).append("=").append(EntityHelper.getColumnHolder(pkColumn));
        if(verColumn!=null){
            sql.append(" AND ").append(verColumn.getName()).append("=").append(EntityHelper.getColumnHolder(verColumn)).append("-1");
        }
        String result=sql.toString();
        cacheUpdateSql.put(entity.getClass(),result);
        return result;

    }


    public String updateOption(Map map){
        StringBuilder sql = new StringBuilder();
        T entity=(T)map.get("entity");
        if(entity instanceof BaseEntity){
            defaultUpdateEntity((BaseEntity)entity);
        }
        String [] properties=((String)map.get("property")).split(",");
        EntityTable table = EntityHelper.getEntityTable(entity.getClass());
        List<EntityColumn> columns = table.getEntityColumns();
        sql.append("UPDATE ");
        sql.append(table.getName()).append(" ");
        sql.append(" SET ");
        int i=0;
        EntityColumn pkColumn=table.getPkColumn();
        EntityColumn verColumn=table.getVerColumn();
        for(String property:properties){
            EntityColumn column=table.getMapEntityColumns().get(property);
            sql.append(column.getName()).append(" = ").append(EntityHelper.getColumnHolder("entity",column));
            if (i < properties.length - 1)
                sql.append(",");
        }
        if(verColumn!=null){
          sql.append(",").append(verColumn.getName()).append(" = ").append(verColumn.getName()).append("+1");
        }
        sql.append(" WHERE ");
        sql.append(pkColumn.getName()).append("=").append(EntityHelper.getColumnHolder("entity",pkColumn));
        if(verColumn!=null){
            sql.append(" AND ").append(verColumn.getName()).append("=").append(EntityHelper.getColumnHolder("entity",verColumn)).append("-1");

        }
        return sql.toString();
    }

}
