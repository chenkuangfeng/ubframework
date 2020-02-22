package com.ubsoft.framework.core.dal.mybatis.provider;

import com.ubsoft.framework.core.dal.entity.BaseEntity;
import com.ubsoft.framework.core.dal.model.EntityColumn;
import com.ubsoft.framework.core.dal.model.EntityTable;
import com.ubsoft.framework.core.dal.support.EntityHelper;

import java.text.MessageFormat;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class BaseInsertProvider<T> extends BaseProvider<T> {
    /**缓存entity的标准插入SQl**/
    private  static Map<Class<?>,String> cacheInsertSql= new HashMap<Class<?>,String>();
    public String save(T entity) {
        String cacheSql=cacheInsertSql.get(entity.getClass());
        if(cacheSql!=null){
            return cacheSql;
        }
        if(entity instanceof BaseEntity){
            defaultSaveEntity((BaseEntity)entity);
        }
        StringBuilder sql = new StringBuilder();
        EntityTable table = EntityHelper.getEntityTable(entity.getClass());
        insertIntoSql(sql,table);
        insertColumnsSql(sql,table);
        insertValuesColumnsSql(sql,table);
        String result=sql.toString();
        cacheInsertSql.put(entity.getClass(),result);
        return result;
    }

    public String batchSave(Map map) {
        List<T> entities =(List<T>)map.get("list");
        StringBuilder sql = new StringBuilder();
        for(Object entity:entities){
            if(entity instanceof BaseEntity){
                defaultSaveEntity((BaseEntity)entity);
            }
        }
        EntityTable table = EntityHelper.getEntityTable(entities.get(0).getClass());
        insertIntoSql(sql,table);
        insertColumnsSql(sql,table);
        sql.append(" VALUES");
        List<EntityColumn> columns = table.getEntityColumns();
        int length = entities.size();
        StringBuilder mfStr=new StringBuilder();
        mfStr.append("(");
        int i = 0;
        for (EntityColumn column : columns) {
            if (column.isInsertable()) {
                mfStr.append(EntityHelper.getListColumnHolder(column));
                if (i < columns.size() - 1) {
                    mfStr.append(",");
                }
            }
            i++;
        }
        mfStr.append(")");
        MessageFormat mf = new MessageFormat(mfStr.toString());
        for(int j=0;j<length;j++){
            sql.append(mf.format(new Object[]{j}));
            if (j < length - 1) {
                sql.append(",");
            }
        }
        // sql.append("</trim>");

        return sql.toString();
    }

     public void  insertIntoSql( StringBuilder sql,   EntityTable table){
         sql.append("INSERT INTO ");
         sql.append(table.getName()).append(" ");
     }

    public  void insertColumnsSql(StringBuilder sql,   EntityTable table) {
        //sql.append("<trim prefix=\"(\" suffix=\")\" suffixOverrides=\",\">");
        sql.append("(");
        List<EntityColumn> columns = table.getEntityColumns();
        int i = 0;
        for (EntityColumn column : columns) {
            if (column.isInsertable()) {
                sql.append(column.getName());
                if (i < columns.size() - 1) {
                    sql.append(",");
                }
            }
            i++;
        }
        sql.append(")");
        //sql.append("</trim> ");
    }

    public static void insertValuesColumnsSql(StringBuilder sql,   EntityTable table) {
        //sql.append("<trim prefix=\" VALUES (\" suffix=\")\" suffixOverrides=\",\">");
        sql.append(" VALUES (");
        List<EntityColumn> columns = table.getEntityColumns();
        int i = 0;
        for (EntityColumn column : columns) {
            if (column.isInsertable()) {
                sql.append(EntityHelper.getColumnHolder(column));
                if (i < columns.size() - 1) {
                    sql.append(",");
                }
            }
            i++;
        }
        // sql.append("</trim>");
        sql.append(")");

    }


}
