package com.ubsoft.framework.core.dal.mybatis.provider;

import com.ubsoft.framework.core.dal.model.EntityColumn;
import com.ubsoft.framework.core.dal.model.EntityTable;
import com.ubsoft.framework.core.dal.support.EntityHelper;
import com.ubsoft.framework.core.exception.DataAccessException;
import org.apache.ibatis.jdbc.SQL;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class BaseSelectProvider<T> extends BaseProvider<T> {

    private  static Map<Class<?>,String> cacheColumnSql= new HashMap<Class<?>,String>();

    /**
     * 生成查询所有数据的select语句
     * @param map
     * @return
     */
    public String selectAll(Map map){
        StringBuilder sql = new StringBuilder();
        Class<T> clazz=(Class<T>)map.get("clazz");
        EntityTable table = EntityHelper.getEntityTable(clazz);
        sql.append("SELECT ");
        sql.append(getAllColumnByClass(clazz));
        sql.append(" FROM ").append(table.getName());
        return sql.toString();

    }

    /**
     * 生成根据property条件查询的Sql
     * @param map
     * @return
     */
    public String select(Map map){
        StringBuilder sql = new StringBuilder();
        Class<T> clazz=(Class<T>)map.get("clazz");
        EntityTable table = EntityHelper.getEntityTable(clazz);
        String filter=(String)map.get("filter");
        sql.append("SELECT ");
        sql.append(getAllColumnByClass(clazz));
        sql.append(" FROM ").append(table.getName());
        sql.append(" WHERE ");
        sql.append(getWhereByFilter(clazz,filter));
        return sql.toString();
    }

    public String selectOption(Map map){
        StringBuilder sql = new StringBuilder();
        Class<T> clazz=(Class<T>)map.get("clazz");
        EntityTable table = EntityHelper.getEntityTable(clazz);
        String property=(String)map.get("property");
        String filter=(String)map.get("filter");
        sql.append("SELECT ");
        sql.append(getOptionColumnProperty(clazz,property));
        sql.append(" FROM ").append(table.getName());
        sql.append(" WHERE ");
        sql.append(getWhereByFilter(clazz,filter));
        return sql.toString();
    }

//    public String select(Map map){
//        Class<T> clazz=(Class<T>)map.get("clazz");
//        String where=(String)map.get("where");
//        String property=(String)map.get("property");
//        Map args=(Map)map.get("p");
//        StringBuilder sql = new StringBuilder();
//        EntityTable table = EntityHelper.getEntityTable(clazz);
//        List<EntityColumn> columns= table.getEntityColumns();
//        sql.append("SELECT  ");
//        if(StringUtil.isNotEmpty(property)){
//            sql.append(this.getOptionColumnProperty(clazz,property));
//        }else{
//            sql.append(getAllColumnByClass(clazz));
//        }
//        sql.append(" FROM ").append(table.getName());
//        if(StringUtil.isNotEmpty(where)){
//            sql.append(" WHERE ").append(where);
//        }
//        if(StringUtil.isEmpty(where)&&(args!=null&&args.size()>0)){
//            sql.append(" WHERE ");
//            final int[] j = {0};
//            args.forEach((key, value) -> {
//                String columnName=table.getMapEntityColumns().get(key).getName();
//                sql.append(columnName).append("=#{p.").append(key).append("}");
//                if(j[0] <args.size()-1){
//                    sql.append(" and ");
//                }
//                j[0]++;
//            });
//        }
//        return sql.toString();
//    }
    public String get(Map map){
        Class<T> clazz=(Class<T>)map.get("clazz");
        EntityTable table = EntityHelper.getEntityTable(clazz);
        return new SQL(){{
            SELECT("*");
            FROM(table.getName());
            WHERE(table.getPkColumn().getName()+" = #{id} ");
        }}.toString();
    }

    public String queryBio(Map map){
        StringBuilder sql = new StringBuilder();
        String selectSql=(String)map.get("sql");
        sql.append(selectSql);
        return sql.toString();
    }
    public String query(Map map){
        StringBuilder sql = new StringBuilder();
        String selectSql=(String)map.get("sql");
        sql.append(selectSql);
        return sql.toString();
    }
    /**
     * 根据entity类，生成数据库列并缓存用","号隔开
     * @param clazz
     * @return
     */
    private String getAllColumnByClass(Class<T> clazz){
        EntityTable table = EntityHelper.getEntityTable(clazz);
        List<EntityColumn> columns= table.getEntityColumns();
        //解析列部分sql缓存
        String cacheSql=cacheColumnSql.get(clazz);
        StringBuilder sql= new StringBuilder();
        if(cacheSql!=null){
           return cacheSql;
        }else {
            int i = 0;
            for (EntityColumn column : columns) {
                sql.append(column.getName()).append(" AS ").append(column.getProperty());
                if (i < columns.size() - 1) {
                    sql.append(",");
                }
                i++;
            }
            String result=sql.toString();
            cacheColumnSql.put(clazz,result);
            return  result;


        }

    }

    private String getWhereByFilter(Class<T> clazz,String filter) {
        StringBuilder sql = new StringBuilder();
        EntityTable table = EntityHelper.getEntityTable(clazz);
        List<EntityColumn> columns = table.getEntityColumns();
        String[] filterArr = filter.split(",");
        for (int i = 0; i < filterArr.length; i++) {
            String property = filterArr[i];
            EntityColumn column = table.getMapEntityColumns().get(property);
            if (column != null) {
                sql.append(column.getName()).append("=#{param[").append(i).append("]}");
                if (i < filterArr.length - 1) {
                    sql.append(" AND ");
                }
            } else {
                throw new DataAccessException(DataAccessException.MIN_ERROR_CODE_DAL, clazz.getName() + "不存在属性:" + property);
            }

        }
        String result = sql.toString();
        return result;

    }

    /**
     * 根据指定的属性，获取select中列的sql。
     * @param clazz
     * @param properties
     * @return
     */
    private String getOptionColumnProperty(Class<T> clazz,String properties) {
        StringBuilder sql = new StringBuilder();
        EntityTable table = EntityHelper.getEntityTable(clazz);
        List<EntityColumn> columns = table.getEntityColumns();
        String[] propertyArr = properties.split(",");
        for (int i = 0; i < propertyArr.length; i++) {
            String property = propertyArr[i];
            EntityColumn column = table.getMapEntityColumns().get(property);
            if (column != null) {
                sql.append(column.getName()).append(" AS ").append(property);
                if (i < propertyArr.length - 1) {
                    sql.append(",");
                }
            } else {
                throw new DataAccessException(DataAccessException.MIN_ERROR_CODE_DAL, clazz.getName() + "不存在属性:" + property);
            }

        }
        String result = sql.toString();
        return result;

    }




}
