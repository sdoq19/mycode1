package priv.resource.thread.test;

import priv.utils.DateUtil;

public class SleepTest {
	
	public static void main (String[] args) {
		
		Thread t1 = new Thread(new Thread1());
		t1.start();
		
		Thread t2 = new Thread(new Thread2());
		t2.start();
	}
	
}

class Thread1 implements Runnable {

	public void run() {
		try {
			Thread.sleep(1000);
		} catch (InterruptedException e) {
			e.printStackTrace();
		}
		System.out.println("[Thread1]:Now time: " + DateUtil.formatDate(new DateUtil()));
	}
}

class Thread2 implements Runnable {
	
	public void run() {
		System.out.println("[Thread2]:Now time: " + DateUtil.formatDate(new DateUtil()));
	}
}