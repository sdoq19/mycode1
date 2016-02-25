package com.resource.thread.test2;

public class Thread2 implements Runnable {

	Service service;
	
	public Thread2(Service service) {
		this.service = service;
	}
	
	public void run() {
		service.fun2();
	}
	
}
