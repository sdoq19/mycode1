package com.utils;

import java.util.Properties;

/**
 * 异常综合类。
 * 
 * Sep 18, 2013 6:21:54 AM
 */
public class ExceptionUtil {
	
	//系统属性
	private static Properties props=System.getProperties();
	
	/**
	 * 功能：得到异常的堆栈信息。
	 * 
	 * Sep 18, 2013 6:26:04 AM
	 * @param e 异常
	 * @return String
	 */
	public static String getStackTrace(Exception e){
		StringBuffer sb=new StringBuffer();
		for(StackTraceElement line : e.getStackTrace()){
			sb.append(line.toString());
			//系统换行符
			sb.append(props.getProperty("line.separator"));
		}
		return sb.toString();
	}
}
