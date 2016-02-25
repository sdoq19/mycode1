package com.resource.designpatterns.proxy.dynamicProxy.aop;

public class DynaProxyDemo {
	
	public static void main(String args[]) {
		MyInvocationHandler sj = new MyInvocationHandler();
		sj.setAop(new AopImpl());
		sj.setMethod("say");
		Subject sub = (Subject) sj.bind(new RealSubject());
		String info = sub.say("hardy", "23");
		//System.out.println(info);
	}
};
