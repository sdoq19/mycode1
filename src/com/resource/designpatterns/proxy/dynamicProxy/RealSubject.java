package com.resource.designpatterns.proxy.dynamicProxy;

public class RealSubject implements Subject {

	@Override
	public String say(String name, String age) {
		String str = "name:" + name + ",age:" + age;
		return str;
	}
}