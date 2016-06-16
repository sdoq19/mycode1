package priv.resource.thread.test;

import java.text.SimpleDateFormat;

import priv.utils.DateUtil;

public class JoinTest {
	
	public static void main (String[] args) {
		
		Thread t3 = new Thread(new Thread3());
		Thread t4 = new Thread(new Thread4());
		t3.start();
		try {
			t3.join(); // 等待t3完成后再运行t4及主线程
			//t3.join(500);
		} catch (InterruptedException e) {
			e.printStackTrace();
		}
		t4.start();
	}
}

class Thread3 implements Runnable {

	public void run() {
		try {
			Thread.sleep(1000);
		} catch (InterruptedException e) {
			e.printStackTrace();
		}
		System.out.println("[Thread3]:Now time: " + DateUtil.formatDate(new DateUtil(), new SimpleDateFormat("yyyy-MM-dd HH:mm:ss:SS")));
	}
}

class Thread4 implements Runnable {
	
	public void run() {
		
		System.out.println("[Thread4]:Now time: " + DateUtil.formatDate(new DateUtil(), new SimpleDateFormat("yyyy-MM-dd HH:mm:ss:SS")));
	}
}