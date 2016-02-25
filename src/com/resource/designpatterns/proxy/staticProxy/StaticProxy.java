package com.resource.designpatterns.proxy.staticProxy;

public class StaticProxy implements HelloWorld {

	private HelloWorld helloWorld;

	public StaticProxy(HelloWorld helloWorld) {
		this.helloWorld = helloWorld;
	}

	@Override
	public void print() {
		System.out.println("hi~");
		helloWorld.print();
	}
}
