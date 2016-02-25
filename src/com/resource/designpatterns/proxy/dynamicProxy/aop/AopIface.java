package com.resource.designpatterns.proxy.dynamicProxy.aop;

public interface AopIface {
	public void before(Object object); // 调用前处理
	public void end(Object object); // 调用后处理
}
