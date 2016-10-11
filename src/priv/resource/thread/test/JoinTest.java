package priv.resource.thread.test;

import priv.utils.DateUtil;

import java.text.SimpleDateFormat;

/**
 * 1. 调用join()方法的线程将被优先执行，直到此线程运行结束，当前线程才能继续运行
 * 2. 调用join(long mills)方法的线程将被优先执行， 直到此线程运行结束或者经过mills时间，当前线程才能继续运行
 */
public class JoinTest {
	
	public static void main (String[] args) {

		Thread t3 = new Thread(new Thread3());
		Thread t4 = new Thread(new Thread4());
		t3.start();
		try {
			//t3.join(); // 等待t3完成后再运行t4及主线程
			t3.join(5000);
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