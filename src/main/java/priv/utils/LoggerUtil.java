package priv.utils;


import org.slf4j.LoggerFactory;

/** 日志 */
public class LoggerUtil {

	public static void debug(Class<?> clazz,String str) {
		LoggerFactory.getLogger(clazz).debug(str);
	}
	
	public static void info(Class<?> clazz,String str) {
		LoggerFactory.getLogger(clazz).info(str);
	}
	
	public static void error(Class<?> clazz,String str) {
		LoggerFactory.getLogger(clazz).error(str);
	}
	
	public static void error(Class<?> clazz,String str, Throwable e) {
		LoggerFactory.getLogger(clazz).error(str, e);
	}
	
	public static void error(Class<?> clazz,Throwable e) {
		LoggerFactory.getLogger(clazz).error(e.getMessage(), e);
	}
}
