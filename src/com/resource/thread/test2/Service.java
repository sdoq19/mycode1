package com.resource.thread.test2;

public class Service {
	public void fun1() {
		synchronized (this) {
			try {
				Thread.sleep(3 * 1000);
				System.out.println("fun1");
			} catch (InterruptedException e) {
				e.printStackTrace();
			}
		}
	}

	public void fun2() {
		synchronized (this) {
			try {
				System.out.println("fun2");
			} catch (Exception e) {
				e.printStackTrace();
			}
		}
	}

	public void fun3() {
		System.out.println("fun3");
	}
}
