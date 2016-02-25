package com.resource.designpatterns.proxy.dynamicProxy.aop;

public class RealSubject implements Subject {

	@Override
	public String say(String name, String age) {
		System.out.println("name:" + name + ",age:" + age);
		return "name:" + name + ",age:" + age;
	}

	@Override
	public String sayHello(String name) {
		System.out.println("Hello world!");
		return "hello world!";
	}
}