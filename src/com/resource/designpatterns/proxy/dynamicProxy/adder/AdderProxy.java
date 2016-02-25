package com.resource.designpatterns.proxy.dynamicProxy.adder;

import java.lang.reflect.InvocationHandler;
import java.lang.reflect.Method;
import java.lang.reflect.Proxy;

/**
 * 模拟Proxy生成的代理类，这个类是动态生成的，并没有对应的.java文件
 * @author zhuzh
 */
public class AdderProxy extends Proxy implements AdderIface {
	
	private static final long serialVersionUID = 3518123281948992962L;

	protected AdderProxy(InvocationHandler h) {
		super(h);
	}

	@Override
	public int add(int i, int j) {
		Method method = null;
		try {
			method = AdderIface.class.getMethod("add", new Class[]{int.class, int.class});
		} catch (NoSuchMethodException e) {
			e.printStackTrace();
		} catch (SecurityException e) {
			e.printStackTrace();
		}
		Object[] args = {i, j};
		int result = 0;
		try {
			result = (Integer)h.invoke(this, method, args);
		} catch (Throwable e) {
			e.printStackTrace();
		}
		return result;
	}
}
