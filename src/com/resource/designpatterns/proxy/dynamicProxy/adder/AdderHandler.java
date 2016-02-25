package com.resource.designpatterns.proxy.dynamicProxy.adder;

import java.lang.reflect.InvocationHandler;
import java.lang.reflect.Method;
import java.util.Date;

public class AdderHandler implements InvocationHandler {

	// 被代理的对象
	private Object target;

	public AdderHandler(Object target) {
		this.target = target;
	}

	/**
	 * @param proxy
	 *            接下来Proxy要为你生成的代理类的实例，注意，并不是我们new出来的AdderImpl
	 * @param method
	 *            调用的方法的Method实例。如果调用了add()，那么就是add()的Method实例
	 * @param args
	 *            调用方法时传入的参数。如果调用了add()，那么就是传入add()的参数
	 * @return 使用代理后将作为调用方法后的返回值。如果调用了add()，那么就是调用add()后的返回值
	 */
	@Override
	public Object invoke(Object proxy, Method method, Object[] args) throws Throwable {
		// 调用被代理对象的方法并得到返回值
		Object returnVal = method.invoke(target, args);
		// 调用方法前后都可以加入一些其他的逻辑
		System.out.println("Proxy: invoke " + method.getName() + "() at " + new Date().toLocaleString());
		return returnVal;
	}

	public Object getTarget() {
		return target;
	}

	public void setTarget(Object target) {
		this.target = target;
	}

}
