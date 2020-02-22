//package com.ubsoft.framework.core.support.json;
//
//import com.ubsoft.framework.core.util.StringUtil;
//import net.sf.json.JsonConfig;
//import net.sf.json.processors.JsonValueProcessor;
//
//import java.sql.Timestamp;
//import java.text.SimpleDateFormat;
//
//
///**
// * Json-Lib日期格式系列化辅助格式化工具<br>
// *
// * @author XiongChun
// * @since 2009-07-06
// * @see JsonValueProcessor
// */
//public class JsonValueProcessorImpl implements JsonValueProcessor {
//
//	/**
//	 * 默认的格式
//	 */
//	private String format = "yyyy-MM-dd HH:mm:ss";
//
//	public JsonValueProcessorImpl() {
//	};
//
//	public JsonValueProcessorImpl(String format) {
//		this.format = format;
//	}
//
//	/**
//	 * 格式化数组
//	 */
//	public Object processArrayValue(Object value, JsonConfig jsonConfig) {
//		String[] obj = null;
//		if (value instanceof java.util.Date[]) {
//			format = "yyyy-MM-dd HH:mm:ss";
//			SimpleDateFormat sf = new SimpleDateFormat(format);
//			java.util.Date[] dates = (java.util.Date[]) value;
//			obj = new String[dates.length];
//			for (int i = 0; i < dates.length; i++) {
//				obj[i] = sf.format(dates[i]);
//			}
//		}
//		if (value instanceof Timestamp[]) {
//			format = "yyyy-MM-dd HH:mm:ss";
//			SimpleDateFormat sf = new SimpleDateFormat(format);
//			Timestamp[] dates = (Timestamp[]) value;
//			obj = new String[dates.length];
//			for (int i = 0; i < dates.length; i++) {
//				obj[i] = sf.format(dates[i]);
//			}
//		}
//		if (value instanceof java.sql.Date[]) {
//			format = "yyyy-MM-dd";
//			SimpleDateFormat sf = new SimpleDateFormat(format);
//			java.sql.Date[] dates = (java.sql.Date[]) value;
//			obj = new String[dates.length];
//			for (int i = 0; i < dates.length; i++) {
//				obj[i] = sf.format(dates[i]);
//			}
//		}
//
//		return obj;
//	}
//
//	/**
//	 * 格式化单一对象
//	 */
//	public Object processObjectValue(String key, Object value,
//			JsonConfig jsonConfig) {
//
//		if(StringUtil.isEmpty(value))
//			return null;
//		if (value instanceof Timestamp) {
//			format = "yyyy-MM-dd HH:mm:ss";
//			String str = new SimpleDateFormat(format).format((Timestamp) value);
//			return str;
//		} else if (value instanceof java.util.Date) {
//			format = "yyyy-MM-dd HH:mm:ss";
//			String str = new SimpleDateFormat(format).format((java.util.Date) value);
//			return str;
//		} else if (value instanceof java.sql.Date) {
//			format = "yyyy-MM-dd";
//			String str = new SimpleDateFormat(format).format((java.sql.Date) value);
//			return str;
//		}
//		return value;
//	}
//
//	public String getFormat() {
//		return format;
//	}
//
//	public void setFormat(String format) {
//		this.format = format;
//	}
//
//}
