package com.resource.designpatterns.proxy.dynamicProxy;

import java.lang.reflect.InvocationHandler;
import java.lang.reflect.Method;
import java.lang.reflect.Proxy;

public class MyInvocationHandler implements InvocationHandler {

	private Object obj;
	
	public Object bind (Object obj) {
		this.obj = obj;
		return Proxy.newProxyInstance(obj.getClass().getClassLoader(), obj.getClass().getInterfaces(), this);
	}
	
	@Override
	public Object invoke(Object proxy, Method method, Object[] args) throws Throwable {
		System.out.println("调用方法---->" + method);
		if (args != null) {
			StringBuffer sb = new StringBuffer("[");
			for (int i = 0; i < args.length; i++) {
				sb.append(args[i] + ",");
			}
			sb.append("]");
			System.out.println("方法有[" + args.length + "]个参数:" + sb.toString());
		}
		// 利用反射机制调用原方法
		Object temp = method.invoke(this.obj, args);
		return temp;
	}
	
}
