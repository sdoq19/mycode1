package com.resource.thread.test;

import com.utils.DateUtil;

public class YieldTest {
	
	public static void main (String[] args) {
		
		Thread t5 = new Thread(new Thread5());
		t5.start();
		
		Thread t6 = new Thread(new Thread6());
		t6.start();
	}
	
}

class Thread5 implements Runnable {

	public void run() {
		try {
			Thread.sleep(1000);
		} catch (InterruptedException e) {
			e.printStackTrace();
		}
		System.out.println("[Thread1]:Now time: " + DateUtil.formatDate(new DateUtil()));
	}
}

class Thread6 implements Runnable {
	
	public void run() {
		Thread.yield();
		System.out.println("[Thread2]:Now time: " + DateUtil.formatDate(new DateUtil()));
	}
}