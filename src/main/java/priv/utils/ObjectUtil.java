package priv.utils;

import java.io.ByteArrayInputStream;
import java.io.IOException;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;
import java.lang.reflect.Field;
import java.lang.reflect.ParameterizedType;
import java.lang.reflect.Type;
import java.util.HashMap;
import java.util.Map;
import java.util.Set;

import com.sun.xml.internal.messaging.saaj.util.ByteOutputStream;

import priv.utils.bean.BaseBean;

/**
 * Object 工具类
 * 
 *  2013-11-6 下午02:25:00
 */
public class ObjectUtil {
	
	/**
	 * 功能：判断两个object是否为同一个引用。
	 * 
	 * 2013-11-6 下午02:25:46
	 * @param obj1 对象1
	 * @param obj2 对象2
	 * @return boolean 是返回true，否则返回false。
	 */
	public static boolean equals(Object obj1, Object obj2) {
		return (obj1 != null) ? (obj1.equals(obj2)) : (obj2 == null);
	}
	
	/**
	 * 功能：判断两个object的类型是否相同。
	 * 
	 * 2013-11-6 下午02:28:00
	 * @param object 第一个对象
	 * @param thiz 第二个对象
	 * @return boolean 是返回true，否则返回false。
	 */
	public static boolean equalsType(Object object, Object thiz) {
		return (object != null) && (object.getClass().equals(thiz.getClass()));
	}
	
	/***
	 * 依据class的名称获取对应class
	 * @param <T>	非基本数据类型的任意类型
	 * @param classAllName	类的全称(如: java.lang.String)
	 * @return	返回依据类名映射的class对象
	 * @throws ClassNotFoundException 
	 */
	@SuppressWarnings("unchecked")
	public static Class getClassByName(String classAllName) throws ClassNotFoundException {
		return Class.forName(classAllName);
	}
	
	/**
	 * 初始化对象
	 * @param clazz		创建的对象的类型
	 * @param attrMap	初始对象的属性值
	 * @return	创建的对象
	 * @throws Exception
	 */
	public static <T> T initObject(Class<T> clazz, Map<String, Object> attrMap) throws Exception {
		T obj = clazz.newInstance();
		if(attrMap != null) {
			for(String attrName : attrMap.keySet()) {
				if(!attrName.equals("serialVersionUID")) {
					setAttribute(obj, attrName, attrMap.get(attrName));
				}
			}
		}
		return obj;
	}
	
	/** 
	 * 给对象的属性赋值 
	 * @param obj	对象
	 * @param attrName	对象的属性名
	 * @param value	对象的属性值
	 * @throws Exception
	 */
	@SuppressWarnings("unchecked")
	public static void setAttribute(Object obj, String attrName, Object value) throws Exception {
		Class clazz = obj.getClass();
		try {
			Field field = clazz.getDeclaredField(attrName);
			if(field != null) {
				field.setAccessible(true);
				field.set(obj, parseToObject(value, field.getType()));
				return;
			}
		} catch (Exception e) {}
		clazz = clazz.getSuperclass();
		while(clazz != null){
			Field[] fs = clazz.getDeclaredFields();
			for(Field f : fs) {
				if(f.getName().equalsIgnoreCase(attrName)) {
					f.setAccessible(true);
					f.set(obj, value);
					return;
				}
			}
			clazz = clazz.getSuperclass();
		}
	}
	
	/**
	 * 从对象中取值
	 * @param obj	对象
	 * @param attrName	要取值的属性名
	 * @return	值
	 * @throws Exception
	 */
	@SuppressWarnings("unchecked")
	public static Object getAttributeValue(Object obj, String attrName) throws Exception {
		Class clazz = obj.getClass();
		try {
			Field field = clazz.getDeclaredField(attrName);
			if(field != null) {
				field.setAccessible(true);
				return field.get(obj);
			}
		} catch (Exception e) {
			//LoggerUtil.error(ObjectUtil.class, e);
		}
		
		clazz = clazz.getSuperclass();
		while(clazz != null){
			Field[] fs = clazz.getDeclaredFields();
			for(Field f : fs) {
				if(f.getName().equalsIgnoreCase(attrName)) {
					f.setAccessible(true);
					return f.get(obj);
				}
			}
			clazz = clazz.getSuperclass();
		}
		return null;
	}
	
	/**
	 * 获取对象中的所有属性
	 * @param bean	对象
	 * @return	属性和值(Map[属性名, 属性值])
	 */
	@SuppressWarnings("unchecked")
	public static Map<String, Object> getFields(Object bean) throws Exception {
		Map<String, Object> map = new HashMap<String, Object>();
		Class clazz = bean.getClass();
		do {
			Field[] fs = clazz.getDeclaredFields();
			for(Field f : fs) {
				f.setAccessible(true);
				Object value = f.get(bean);
				map.put(f.getName(), value);
			}
			clazz = clazz.getSuperclass();
		} while(clazz != null);
		return map;
	}
	
	/**
	 * 获取类的所有属性与属性的类型
	 * @param clazz	类
	 * @return	该类的所有属性名与属性类型(包含父类属性)
	 */
	@SuppressWarnings("unchecked")
	public static Map<String, Class> getFieldNames(Class clazz) {
		Map<String, Class> attrMap = new HashMap<String, Class>();
		do {
			Field[] fs = clazz.getDeclaredFields();
			for(Field f : fs) {
				attrMap.put(f.getName(), f.getType());
			}
			clazz = clazz.getSuperclass();
		} while(clazz != null);
		return attrMap;
	}
	
	/**
	 * 获取对象中的非空属性
	 * @param bean	对象
	 * @return	非空属性和值(Map[属性名, 属性值])
	 */
	@SuppressWarnings("unchecked")
	public static Map<String, Object> getNotNullFields(Object bean) throws Exception {
		Map<String, Object> map = new HashMap<String, Object>();
		Class clazz = bean.getClass();
		do {
			Field[] fs = clazz.getDeclaredFields();
			for(Field f : fs) {
				f.setAccessible(true);
				Object value = f.get(bean);
				if(value!=null && !value.equals(0) && !value.toString().trim().equals("") &&
						!(value.toString().trim().replace("0", "").replace(".", "").equals(""))) {
					map.put(f.getName(), value);
				}
			}
			clazz = clazz.getSuperclass();
		} while(clazz != null);
		return map;
	}
	
	
	/***
	 * 依据类，获取该类的泛型class
	 * @param bean	类对象
	 * @return	泛型类型
	 */
	@SuppressWarnings("unchecked")
	public static <T extends Object> Class<T> getGeneric(Class clazz) {
		Type genType = clazz.getGenericSuperclass();
		if (!(genType instanceof ParameterizedType)) {
			return (Class<T>) Object.class;
		}

		Type[] params = ((ParameterizedType) genType).getActualTypeArguments();
		return (Class<T>) params[0];
	}
	
	/**
	 * 将byte字节转换成对象
	 * @param bts	字节数据
	 * @return	对象
	 */
	@SuppressWarnings("unchecked")
	public static <T extends Object> T parseByteForObj(byte[] bts) {
		ByteArrayInputStream input = new ByteArrayInputStream(bts);
		ObjectInputStream objectInput = null;
		try {
			objectInput = new ObjectInputStream(input);
			return (T) objectInput.readObject();
		} catch (Exception e) {
			e.printStackTrace();
			return null;
		} finally {
			try {
				if(objectInput != null) {
					objectInput.close();
				}
				if(input != null) {
					input.close();
				}
			} catch (IOException e) {
				e.printStackTrace();
			}
		}
	}
	
	/**
	 * 将对象转换为byte数据
	 * @param obj	对象
	 * @return	byte数据
	 */
	public static byte[] parseObjForByte(Object obj) {
		ByteOutputStream byteOut = new ByteOutputStream();
		ObjectOutputStream objOut = null;
		try {
			objOut = new ObjectOutputStream(byteOut);
			objOut.writeObject(obj);
			return byteOut.getBytes();
		} catch (IOException e) {
			e.printStackTrace();
			return null;
		} finally {
			try {
				if(objOut != null) {
					objOut.close();
				}
				if(byteOut != null) {
					byteOut.close();
				}
			} catch (IOException e) {
				e.printStackTrace();
				return null;
			}
		}
	}
	
	/***
	 * 转换类型
	 * @param value	字符串的值
	 * @param type	要转换的类型
	 * @return	转换后的值
	 */
	@SuppressWarnings("unchecked")
	public static <T> Object parseToObject(Object value, Class<T> type) {
		if(value==null || type==String.class) {
			return value;
		}
		if(type==Long.class || type==long.class) {
			return Long.parseLong(value.toString());
		}
		if(type==Integer.class || type==int.class) {
			return Integer.parseInt(value.toString());
		}
		if(type==Double.class || type==double.class) {
			return Double.parseDouble(value.toString());
		}
		if(type==Float.class || type==float.class) {
			return Float.parseFloat(value.toString());
		}
		if(type==Byte.class || type==byte.class) {
			return Byte.parseByte(value.toString());
		}
		if(type==Boolean.class || type==boolean.class) {
			return Boolean.parseBoolean(value.toString());
		}
		if(type==Short.class || type==short.class) {
			return Short.parseShort(value.toString());
		}
		if(type==Character.class || type==char.class) {
			char[] chars = value.toString().toCharArray();
			return chars.length>0?chars.length>1?chars:chars[0]:Character.MIN_VALUE;
		}
		return (T)value;
	}
	
	/***
	 * 克隆有序列化的对象
	 * @param <T>	要返回的数据类型
	 * @param bean	所有继承过BaseBean的对象
	 * @return	克隆后的对象
	 * @throws Exception 
	 */
	public static <T>T CloneObject(Class<T> clazz, Object bean) throws Exception {
		Map<String, Object> attrMap = getFields(bean);
		attrMap.remove("serialVersionUID");
		return initObject(clazz, attrMap);
	}
	
	/***
	 * 克隆有序列化的对象
	 * @param <T>	要返回的数据类型
	 * @param bean	所有继承过BaseBean的对象
	 * @return	克隆后的对象
	 * @throws Exception 
	 */
	@SuppressWarnings("unchecked")
	public static <T>T CloneObject(BaseBean bean) throws Exception {
		Map<String, Object> attrMap = getFields(bean);
		attrMap.remove("serialVersionUID");
		return (T) initObject(bean.getClass(), attrMap);
	}
	
	/**
	 * 将新数据的非空属性值插入到基本数据中
	 * @param baseData	基本数据
	 * @param newData	新数据
	 * @throws Exception 
	 */
	public static void insertObj(BaseBean baseData, BaseBean newData) throws Exception {
		if(baseData==null || newData==null) {
			return;
		}
		Map<String, Object> attrList = getNotNullFields(newData);
		Set<String> keys = attrList.keySet();
		if(keys!=null && keys.size()>0) {
			for(String key : keys) {
				if(!key.equals("serialVersionUID")) {
					setAttribute(baseData, key, attrList.get(key));
				}
			}
		}
	}
}
