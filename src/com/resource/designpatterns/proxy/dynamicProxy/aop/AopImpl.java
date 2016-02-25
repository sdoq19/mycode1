package com.resource.designpatterns.proxy.dynamicProxy.aop;

public class AopImpl implements AopIface {

	@Override
	public void before(Object object) {
		System.out.println("调用前处理");
	}

	@Override
	public void end(Object object) {
		System.out.println("调用后处理");
	}

}
