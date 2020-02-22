//package com.ubsoft.framework.core.dal.session;
//
//import org.apache.poi.ss.formula.functions.T;
//
//import java.io.Serializable;
//import java.util.List;
//
///**
// * 数据库dao层通用操作类
// *
// * @author chenkf
// *
// */
//public interface IDataSession{
//	/**
//	 * 根据ID获取一个Entity对象
//	 * @param clazz
//	 * @param id
//	 * @return
//	 */
//	 <T extends Serializable> T get(Class<T> clazz, Serializable id);
//	/**
//	 * 根据过滤条件获取一个entity对象
//	 * @param clazz
//	 * @param filterProperty 属性查询条件，多个用","号分开。
//	 * @param value   属性值数组
//
//	 * @return
//	 */
//	 T get(Class<T> clazz, String filterProperty, Object... value);
//	/**
//	 * 根据过滤条件获取一个包含指定属性entity对象
//	 * @param clazz
//	 * @param properties 属性字段，多个用，号分开
//	 * @param filterProperty 属性查询条件，多个用","号分开。
//	 * @param value 属性值数组
//	 * @return
//	 */
//	 T get(Class<T> clazz,String properties, String filterProperty, Object... value);
//	/**
//	 * 查询多个entity对象
//	 * @param clazz
//	 * @return
//	 */
//	 List<T> select(Class<T> clazz);
//
//	/**
//	 *根据过滤条件获取多个entity对象
//	 * @param clazz
//	 * @param filterProperty 属性查询条件，多个用","号分开。
//	 * @param value 属性值数组
//	 * @return
//	 */
//	  List<T> select(Class<T> clazz, String filterProperty, Object... value);
//	/**
//	 * 根据过滤条件获取多个包含指定属性entity对象
//	 * @param clazz
//	 * @param properties 属性字段，多个用，号分开
//	 * @param filterProperty 属性查询条件，多个用","号分开。
//	 * @param value 属性值数组
//	 * @return
//	 */
//	  List<T> select(Class<T> clazz,String  properties,String filterProperty,Object... value);
//
//	/**
//	 * 保存一个实体
//	 * @param entity
//	 * @return
//	 */
//	  int save(T entity);
//
//	/**
//	 * 保存多个实体
//	 * @param entities
//	 * @return
//	 */
//	  int save(List<T> entities);
//	/**
//	 * 更新一个实体
//	 * @param entity
//	 * @return
//	 */
//	  int update(T entity);
//	/**
//	 * 保存多个实体（循环保存,不是批量)
//	 * @param entities
//	 * @return
//	 */
//	  int update(List<T> entities);
//
//	/**
//	 * 删除一个实体
//	 * @param entity
//	 * @return
//	 */
//	  int delete(T entity);
//	/**
//	 * 删除多个实体
//	 * @param entities
//	 * @return
//	 */
//	 int delete(List<T> entities);
//
//	/**
//	 * 根据ID删除一个实体
//	 * @param clazz
//	 * @param id
//	 * @return
//	 */
//	  int delete(Class<T> clazz,Serializable id);
//	/**
//	 * 根据id数组删除多个实体
//	 * @param clazz
//	 * @param id
//	 * @return
//	 */
//	 int delete(Class<T> clazz,Serializable [] id);
//
//
//}
