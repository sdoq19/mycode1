package com.resource.thread.test3;

/**
 * ThreadLocal为每个使用该变量的线程提供独立的变量副本，所以每一个线程都可以独立地改变自己的副本， 而不会影响其它线程所对应的副本。
 * 在ThreadLocal类中有一个Map，用于存储每一个线程的变量副本，Map中元素的键为线程对象，而值对应线程的变量副本。
 * 
 * @author zhuzh
 * 
 */
public class ThreadLocalTest {

	private static ThreadLocal<Integer> seqNum = new ThreadLocal<Integer>() {
		public Integer initialValue() {
			return 0;
		}
	};

	public int getNextNum() {
		seqNum.set(seqNum.get() + 1);
		return seqNum.get();
	}

	public static void main(String[] args) {
		ThreadLocalTest tt = new ThreadLocalTest();
		TestClient t1 = new TestClient(tt);
		TestClient t2 = new TestClient(tt);
		TestClient t3 = new TestClient(tt);
		t1.start();
		t2.start();
		t3.start();
	}

	private static class TestClient extends Thread {
		private ThreadLocalTest sn;

		public TestClient(ThreadLocalTest t) {
			this.sn = t;
		}

		@Override
		public void run() {
			for (int i = 0; i < 3; i++) {
				System.out
						.println("ThreadName:" + Thread.currentThread().getName() + "---->" + "sn:" + sn.getNextNum());
			}
		}
	}
}
