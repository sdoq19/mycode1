package priv.resource.thread.test;

import priv.utils.DateUtil;

/**
 * yield()让当前运行线程回到可运行状态，以允许具有相同优先级的其他线程获得运行机会。
 * 因此，使用yield()的目的是让相同优先级的线程之间能适当的轮转执行。但是，实际中无法保证yield()达到让步目的，因为让步的线程还有可能被线程调度程序再次选中。
 */
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