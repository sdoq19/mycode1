package com.resource.designpatterns.proxy.dynamicProxy.adder;

import java.lang.reflect.InvocationHandler;
import java.lang.reflect.Proxy;

public class Test {

	public static void main(String[] args) {
		AdderIface calc = new AdderImpl();

		// 类加载器
		ClassLoader loader = Test.class.getClassLoader();

		// 需要代理的接口
		Class[] interfaces = { AdderIface.class };

		// 方法调用处理器，保存实际的AdderImpl的引用
		InvocationHandler h = new AdderHandler(calc);

		// 为calc加上代理
		calc = (AdderIface) Proxy.newProxyInstance(loader, interfaces, h);

		// 什么？你说还有别的需求？
		// 另一个处理器，保存前处理器的引用
		// InvocationHandler h2 = new XXOOHandler(h);
		// 再加代理 calc = (Adder) Proxy.newProxyInstance(loader, interfaces, h2);
		int result = calc.add(1, 2);
		System.out.println("The result is " + result);
	}
}
