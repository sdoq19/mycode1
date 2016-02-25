package com.resource.designpatterns.proxy.dynamicProxy;

public class DynaProxyDemo {
	
	public static void main(String args[]) {
		Subject sub = (Subject) new MyInvocationHandler().bind(new RealSubject());
		String info = sub.say("hardy", "23");
		System.out.println(info);
	}
};
