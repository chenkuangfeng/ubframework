package com.ubsoft.framework.core.util;

import com.ubsoft.framework.core.dal.model.Bio;
import com.ubsoft.framework.core.exception.ComException;
import org.springframework.util.StringUtils;

import java.beans.BeanInfo;
import java.beans.Introspector;
import java.beans.PropertyDescriptor;
import java.lang.reflect.Field;
import java.lang.reflect.Method;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class BeanUtil {

	private static Map<Class<?>, Map<String, PropertyDescriptor>> mapedProperty = new HashMap<Class<?>, Map<String, PropertyDescriptor>>();

	private static Map<Class<?>, Map<String, String>> mapedColumn = new HashMap<Class<?>, Map<String, String>>();

	public static <T> T bio2Bean(Class<T> type, Bio bio) {
		try {
			PropertyDescriptor[] propertyDescriptors = getPropertyDescriptors(type);
			T obj = type.newInstance(); // 创建 JavaBean 对象
			// 给 JavaBean 对象的属性赋值
			for (int i = 0; i < propertyDescriptors.length; i++) {
				PropertyDescriptor descriptor = propertyDescriptors[i];
				String propertyName = descriptor.getName();
				String propertyType = descriptor.getPropertyType().getSimpleName();
				if (bio.containsKey(propertyName)) {
					Object value = bio.getObject(propertyName);
					value = TypeUtil.convert(propertyType, value);
					Object[] args = new Object[1];
					args[0] = value;
					descriptor.getWriteMethod().invoke(obj, args);
				}
			}
			return obj;
		} catch (Throwable e) {
			throw new ComException(ComException.MIN_ERROR_CODE_Entity, e);
		}
	}

	public static <T> T bio2Bean(T obj, Bio bio) {
		try {
			Class type = obj.getClass();
			PropertyDescriptor[] propertyDescriptors = getPropertyDescriptors(type);

			for (int i = 0; i < propertyDescriptors.length; i++) {
				PropertyDescriptor descriptor = propertyDescriptors[i];
				String propertyName = descriptor.getName();
				String propertyType = descriptor.getPropertyType().getSimpleName();
				if (bio.containsKey(propertyName.toUpperCase())) {
					Object value = bio.getObject(propertyName);
					value = TypeUtil.convert(propertyType, value);
					Object[] args = new Object[1];
					args[0] = value;
					descriptor.getWriteMethod().invoke(obj, args);
				}
			}
			return obj;
		} catch (Throwable e) {
			throw new ComException(ComException.MIN_ERROR_CODE_Entity, e);
		}
	}

	public static <T> Bio bean2Bio(T bean) {
		try {
			Class type = bean.getClass();
			Bio bio = new Bio();
			PropertyDescriptor[] propertyDescriptors = getPropertyDescriptors(type);
			for (int i = 0; i < propertyDescriptors.length; i++) {
				PropertyDescriptor descriptor = propertyDescriptors[i];
				String propertyName = descriptor.getName();
				if (!propertyName.equals("class")) {
					Method readMethod = descriptor.getReadMethod();
					Object result = readMethod.invoke(bean, new Object[0]);
					// 转换成大写
					bio.setObject(propertyName, result);
				}
			}
			return bio;
		} catch (Throwable e) {
			throw new ComException(ComException.MIN_ERROR_CODE_Entity, e);
		}
	}

	public static <T> Bio bean2Bio(T bean, Bio bio) {
		try {
			Class type = bean.getClass();
			PropertyDescriptor[] propertyDescriptors = getPropertyDescriptors(type);
			for (int i = 0; i < propertyDescriptors.length; i++) {
				PropertyDescriptor descriptor = propertyDescriptors[i];
				String propertyName = descriptor.getName();
				if (!propertyName.equals("class")) {
					Method readMethod = descriptor.getReadMethod();
					Object result = readMethod.invoke(bean, new Object[0]);
					bio.setObject(propertyName, result);
				}
			}
			return bio;
		} catch (Throwable e) {
			throw new ComException(ComException.MIN_ERROR_CODE_Entity, e);
		}
	}

	public static PropertyDescriptor[] getPropertyDescriptors(Class type) {
		try {
			BeanInfo beanInfo = null;
			PropertyDescriptor[] propertyDescriptors = null;

			beanInfo = Introspector.getBeanInfo(type); // 获取类属性
			propertyDescriptors = beanInfo.getPropertyDescriptors();

			return propertyDescriptors;
		} catch (Exception ex) {
			throw new ComException(ComException.MIN_ERROR_CODE_Entity, ex);
		}
	}
	public static List<Field> getFields(Class<?> entityClass, List<Field> fields){
		if(fields==null){
			fields= new ArrayList<Field>();
		}
		Field[] fieldArray = entityClass.getDeclaredFields();
		for(Field field:fieldArray){
			fields.add(field);
		}
		Class<?> superClass = entityClass.getSuperclass();
		if(superClass!=null){
			getFields(superClass,fields);
		}
		return fields;

	}
	public static PropertyDescriptor getPropertyDescriptor(Class<?> clazz, String property) {
		return getMapPropertyDescriptor(clazz).get(property.toLowerCase());
	}
	
	public static Map<String,PropertyDescriptor> getMapPropertyDescriptor(Class<?> clazz) {
		Map<String,PropertyDescriptor> result=mapedProperty.get(clazz);
		if(result==null){
			result=new HashMap<String,PropertyDescriptor>();
			PropertyDescriptor[] propertyDescriptors = getPropertyDescriptors(clazz);
			for (PropertyDescriptor p : propertyDescriptors) {
				if(p.getName().equals("class"))
					continue;				
				result.put(p.getName().toLowerCase(), p);
				if (p.getWriteMethod() != null) {
					//大写前加下划线,兼容数据库字段
					String underscoredName = underscoreName(p.getName());
					if (!p.getName().toLowerCase().equals(underscoredName)) {
						result.put(underscoredName, p);
					}
				}
			}
			mapedProperty.put(clazz, result);
		
		}
		return result;
		
	}
	/** 大写前加下划线,兼容数据库字段*/
	private  static String underscoreName(String name) {

		if (!StringUtils.hasLength(name)) {
			return "";
		}
		StringBuilder result = new StringBuilder();
		result.append(name.toLowerCase().substring(0, 1));
		for (int i = 1; i < name.length(); i++) {
			String s = name.substring(i, i + 1);
			String slc = s.toLowerCase();
			if (!s.equals(slc)) {
				result.append("_").append(slc);
			} else {
				result.append(s);
			}
		}
		return result.toString();
	}
	public static Object getProperty(Object bean, String property) {
		boolean hasProperty = false;
		try {
			PropertyDescriptor descriptor = getPropertyDescriptor(bean.getClass(), property);
			String propertyName = descriptor.getName();
			if (propertyName.equals(property)) {
				hasProperty = true;
				Method readMethod = descriptor.getReadMethod();
				return readMethod.invoke(bean, new Object[0]);
			}

		} catch (Exception ex) {
			throw new ComException(ComException.MIN_ERROR_CODE_Entity, ex);
		}
		if (hasProperty) {
			throw new ComException(ComException.MIN_ERROR_CODE_Entity, bean.getClass().getName() + "不存在属性:" + property);
		}
		return null;

	}

	public static <T> Map bean2Map(T bean) {
		try {
			Class type = bean.getClass();
			Map bio = new HashMap();
			PropertyDescriptor[] propertyDescriptors = getPropertyDescriptors(type);
			for (int i = 0; i < propertyDescriptors.length; i++) {
				PropertyDescriptor descriptor = propertyDescriptors[i];
				String propertyName = descriptor.getName();
				if (!propertyName.equals("class")) {
					Method readMethod = descriptor.getReadMethod();
					Object result = readMethod.invoke(bean, new Object[0]);
					// 转换成大写
					bio.put(propertyName, result);
				}
			}
			return bio;
		} catch (Throwable e) {
			throw new ComException(ComException.MIN_ERROR_CODE_Entity, e);
		}
	}
}
