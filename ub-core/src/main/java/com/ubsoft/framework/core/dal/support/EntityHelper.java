package com.ubsoft.framework.core.dal.support;


import com.ubsoft.framework.core.dal.annotation.ColumnType;
import com.ubsoft.framework.core.dal.model.EntityColumn;
import com.ubsoft.framework.core.dal.model.EntityTable;
import com.ubsoft.framework.core.dal.mybatis.provider.BaseProvider;
import com.ubsoft.framework.core.exception.ComException;
import com.ubsoft.framework.core.util.BeanUtil;
import com.ubsoft.framework.core.util.StringUtil;
import org.apache.ibatis.annotations.DeleteProvider;
import org.apache.ibatis.annotations.InsertProvider;
import org.apache.ibatis.annotations.SelectProvider;
import org.apache.ibatis.annotations.UpdateProvider;
import org.apache.ibatis.cache.Cache;
import org.apache.ibatis.cache.decorators.SoftCache;
import org.apache.ibatis.cache.impl.PerpetualCache;
import org.apache.ibatis.mapping.MappedStatement;
import org.apache.ibatis.type.JdbcType;
import org.apache.ibatis.type.UnknownTypeHandler;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import javax.persistence.*;
import java.lang.reflect.Field;
import java.lang.reflect.Method;
import java.util.*;
import java.util.concurrent.ConcurrentHashMap;
public class EntityHelper {
    private   static Logger logger = LoggerFactory.getLogger(EntityHelper.class);

    private static final Map<Class<?>, EntityTable> entityTableMap = new ConcurrentHashMap();

    private static final Cache CLASS_CACHE = new SoftCache(new PerpetualCache("MAPPER_CLASS_CACHE"));

    public static EntityTable getEntityTable(Class<?> entityClass) {
        EntityTable entityTable = (EntityTable) entityTableMap.get(entityClass);
        if (entityTable == null) {
            entityTable = resolveEntity(entityClass);
            entityTableMap.put(entityClass,entityTable);
        }
        if (entityTable == null) {
            throw new ComException(ComException.MIN_ERROR_CODE_Entity, "无法获取实体类" + entityClass.getCanonicalName() + "对应的表名!");
        }
        return entityTable;
    }
    public static EntityTable resolveEntity(Class<?> entityClass) {
        EntityTable entityTable = null;
        List<EntityColumn> columns= new ArrayList<EntityColumn>();
        Table table;
        if (entityClass.isAnnotationPresent(Table.class)) {
            table = (Table) entityClass.getAnnotation(Table.class);
            if (!"".equals(table.name())) {
                entityTable = new EntityTable();
                entityTable.setName(table.name());
            }
        }

        if (entityTable == null) {
            throw new ComException(ComException.MIN_ERROR_CODE_Entity + 1, entityClass.getCanonicalName() + "没有配置Table注解或者Table名称!");
        }

        List<Field> listField= new ArrayList<Field>();
        Map<String, EntityColumn> mapEntityColumn= new HashMap<String,EntityColumn>();
        listField= BeanUtil.getFields(entityClass,listField);
        for(Field field:listField){
            processField(entityTable, field,columns,mapEntityColumn);
            //columns.add(entityColumn);
        }
        entityTable.setEntityColumns(columns);
        entityTable.setMapEntityColumns(mapEntityColumn);
        return entityTable;


    }

    protected static void processField(EntityTable entityTable, Field field,List<EntityColumn> columns, Map<String, EntityColumn> mapEntityColumn) {
        EntityColumn entityColumn = new EntityColumn();
        if (field.isAnnotationPresent(Column.class)&&!field.isAnnotationPresent(Transient.class)) {
            Column column = (Column)field.getAnnotation(Column.class);
            String columnName = column.name();
            if(columnName==null){
                columnName=field.getName();
            }
            if(columnName==null){
                logger.error(field.getType().getName());
            }
            entityColumn.setName(columnName);
            entityColumn.setUpdatable(column.updatable());
            entityColumn.setInsertable(column.insertable());
            entityColumn.setNullable(column.nullable());

            if (field.isAnnotationPresent(Id.class)) {
                entityColumn.setId(true);
                entityTable.setPkColumn(entityColumn);
            }
            if (field.isAnnotationPresent(Version.class)) {
                entityColumn.setVersion(true);
                entityTable.setVerColumn(entityColumn);
            }
            if (field.isAnnotationPresent(Lob.class)) {
                if(field.getType().getName().equals(String.class.getName())){                    //String jdbcType="tes";
                    entityColumn.setJdbcType(JdbcType.CLOB);
                }else{
                    entityColumn.setJdbcType(JdbcType.BLOB);
                }
            }
            if (field.isAnnotationPresent(ColumnType.class)) {
                ColumnType columnType = field.getAnnotation(ColumnType.class);
                //entityColumn.setBlob(columnType.isBlob());
                if (StringUtil.isEmpty(columnName) && StringUtil.isNotEmpty(columnType.column())) {
                    columnName = columnType.column();
                }

                if (columnType.jdbcType() != JdbcType.UNDEFINED) {
                    entityColumn.setJdbcType(columnType.jdbcType());
                }

                if (columnType.typeHandler() != UnknownTypeHandler.class) {
                    entityColumn.setTypeHandler(columnType.typeHandler());
                }
            }
            //entityColumn.setjdbc
            if (field.getType().isPrimitive()) {
                logger.warn("警告信息: <[" + entityColumn + "]> 使用了基本类型，基本类型在动态 SQL 中由于存在默认值，因此任何时候都不等于 null，建议修改基本类型为对应的包装类型!");
            }
            entityColumn.setJavaType(field.getType());
            entityColumn.setProperty(field.getName());
            columns.add(entityColumn);
            mapEntityColumn.put(entityColumn.getProperty(),entityColumn);


        }



    }
    public static String  getColumnHolder(EntityColumn column) {
        StringBuffer sb = new StringBuffer("#{");
        sb.append(column.getProperty());
        if (column.getJdbcType() != null) {
            sb.append(", jdbcType=");
            sb.append(column.getJdbcType().toString());
        }

        if (column.getTypeHandler() != null) {
            sb.append(", typeHandler=");
            sb.append(column.getTypeHandler().getCanonicalName());
        }
        sb.append("}");
        return sb.toString();
    }

    public static String  getColumnHolder(String alias,EntityColumn column) {
        StringBuffer sb = new StringBuffer();
        sb.append("#{").append(alias).append(".");
        sb.append(column.getProperty());
        if (column.getJdbcType() != null) {
            sb.append(", jdbcType=");
            sb.append(column.getJdbcType().toString());
        }

        if (column.getTypeHandler() != null) {
            sb.append(", typeHandler=");
            sb.append(column.getTypeHandler().getCanonicalName());
        }
        sb.append("}");
        return sb.toString();
    }
    public static String  getListColumnHolder(EntityColumn column) {
        StringBuffer sb = new StringBuffer("#'{'list[{0}].");
        sb.append(column.getProperty());
        if (column.getJdbcType() != null) {
            sb.append(", jdbcType=");
            sb.append(column.getJdbcType().toString());
        }

        if (column.getTypeHandler() != null) {
            sb.append(", typeHandler=");
            sb.append(column.getTypeHandler().getCanonicalName());
        }
        sb.append("}");
        return sb.toString();
    }

    private static String getLobJdbcType(String dbType,String javaType){
        String jdbcType=null;
        if(dbType.equals("oracel")){
             if(javaType.equals(String.class)){
                 jdbcType="CLOB";
             }else{
                 jdbcType="";
             }

        }

        if(dbType.equals("mysql")){
            if(javaType.equals(String.class)){
                jdbcType="CLOB";
            }else{
                jdbcType="text";
            }

        }
        return jdbcType;
    }

    public static Class<?> getMapperClass(String msId) {
        if (msId.indexOf(".") == -1) {
            throw new RuntimeException("当前MappedStatement的id=" + msId + ",不符合MappedStatement的规则!");
        }
        String mapperClassStr = msId.substring(0, msId.lastIndexOf("."));
        //由于一个接口中的每个方法都会进行下面的操作，因此缓存
        Class<?> mapperClass = (Class<?>) CLASS_CACHE.getObject(mapperClassStr);
        if(mapperClass != null){
            return mapperClass;
        }
        ClassLoader[] classLoader = getClassLoaders();

        for (ClassLoader cl : classLoader) {
            if (null != cl) {
                try {
                    mapperClass = Class.forName(mapperClassStr, true, cl);
                    if (mapperClass != null) {
                        break;
                    }
                } catch (ClassNotFoundException e) {
                    // we'll ignore this until all class loaders fail to locate the class
                }
            }
        }
        if (mapperClass == null) {
            throw new RuntimeException("class loaders failed to locate the class " + mapperClassStr);
        }
        CLASS_CACHE.putObject(mapperClassStr, mapperClass);
        return mapperClass;
    }

    private static ClassLoader[] getClassLoaders() {
        return new ClassLoader[]{Thread.currentThread().getContextClassLoader(), EntityHelper.class.getClassLoader()};
    }

    /**
     * 获取执行的方法名
     *
     * @param ms
     * @return
     */
    public static String getMethodName(MappedStatement ms) {
        return getMethodName(ms.getId());
    }

    /**
     * 获取执行的方法名
     *
     * @param msId
     * @return
     */
    public static String getMethodName(String msId) {
        return msId.substring(msId.lastIndexOf(".") + 1);
    }

    public static void setSqlSource(MappedStatement ms) throws Exception {
//            Method method = (Method)this.methodMap.get(getMethodName(ms));
//            try {
//                if (method.getReturnType() == Void.TYPE) {
//                    method.invoke(this, ms);
//                } else if (SqlNode.class.isAssignableFrom(method.getReturnType())) {
//                    SqlNode sqlNode = (SqlNode)method.invoke(this, ms);
//                    DynamicSqlSource dynamicSqlSource = new DynamicSqlSource(ms.getConfiguration(), sqlNode);
//                    this.setSqlSource(ms, dynamicSqlSource);
//                } else {
//                    if (!String.class.equals(method.getReturnType())) {
//                        throw new RuntimeException("自定义Mapper方法返回类型错误,可选的返回类型为void,SqlNode,String三种!");
//                    }
//
//                    String xmlSql = (String)method.invoke(this, ms);
//                    SqlSource sqlSource = this.createSqlSource(ms, xmlSql);
//                    this.setSqlSource(ms, sqlSource);
//                }
//
//                //this.checkCache(ms);
//            } catch (IllegalAccessException var5) {
//                throw new RuntimeException(var5);
//            } catch (InvocationTargetException var6) {
//                throw new RuntimeException((Throwable)(var6.getTargetException() != null ? var6.getTargetException() : var6));
//            }
//
    }

    public  static BaseProvider getProvider(Class<?> mapperClass){
        Method[] methods = mapperClass.getDeclaredMethods();
        Class<?> templateClass = null;
        Class<?> tempClass = null;
        Set<String> methodSet = new HashSet<String>();
        for (Method method : methods) {
            if (method.isAnnotationPresent(SelectProvider.class)) {
                SelectProvider provider = method.getAnnotation(SelectProvider.class);
                tempClass = provider.type();
               methodSet.add(method.getName());
            } else if (method.isAnnotationPresent(InsertProvider.class)) {
                InsertProvider provider = method.getAnnotation(InsertProvider.class);
               // tempClass = provider.type();
                methodSet.add(method.getName());
            } else if (method.isAnnotationPresent(DeleteProvider.class)) {
                DeleteProvider provider = method.getAnnotation(DeleteProvider.class);
               // tempClass = provider.type();
                methodSet.add(method.getName());
            } else if (method.isAnnotationPresent(UpdateProvider.class)) {
                UpdateProvider provider = method.getAnnotation(UpdateProvider.class);
               // tempClass = provider.type();
                methodSet.add(method.getName());
            }

        }
        if(tempClass==null){
            return null;
        }
        BaseProvider baseProvider = null;
        try {

            baseProvider = (BaseProvider) tempClass.newInstance();
        } catch (Exception e) {
            throw new RuntimeException("实例化baseProvider对象失败:" + e.getMessage());
        }
        //注册方法
        for (String methodName : methodSet) {
            try {
                baseProvider.addMethodMap(methodName, tempClass.getMethod(methodName, Map.class));
            } catch (NoSuchMethodException e) {

                throw new RuntimeException(templateClass.getCanonicalName() + "中缺少" + methodName + "方法!");
            }
        }
        return baseProvider;
    }

}



