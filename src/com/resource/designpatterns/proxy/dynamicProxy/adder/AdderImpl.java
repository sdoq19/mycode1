package com.resource.designpatterns.proxy.dynamicProxy.adder;

public class AdderImpl implements AdderIface {
	@Override
	public int add(int a, int b) {
		return a + b;
	}
}