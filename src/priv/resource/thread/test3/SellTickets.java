package priv.resource.thread.test3;


public class SellTickets extends Thread {

	private static int ticketsCount;
	
	// static修饰，main方法中创建的多个SellTickets实例线程，共享一个lockObj的对象锁
	private static Object lockObj = new Object();

	@Override
	public void run() {
		synchronized (lockObj) {
			for (int i=0; i<200; i++) {
				ticketsCount++;
				System.out.println("[" + Thread.currentThread().getName() + "]remain tickets count: " + ticketsCount);
			}
		}
	}

	public static void main(String[] args) {
		SellTickets t1 = new SellTickets();
		SellTickets t2 = new SellTickets();
		SellTickets t3 = new SellTickets();
		t1.start();
		t2.start();
		t3.start();
	}
}

/*public class SellTickets implements Runnable {

	private static int ticketsCount = 10;

	@Override
	public void run() {
		while (ticketsCount != 0) {
			ticketsCount--;
			System.out.println("[" + Thread.currentThread().getName() + "]remain tickets count: " + ticketsCount);
		}
	}

	public static void main(String[] args) {
		Thread t1 = new Thread(new SellTickets(), "Thread-1");
		Thread t2 = new Thread(new SellTickets(), "Thread-2");
		Thread t3 = new Thread(new SellTickets(), "Thread-3");
		t1.start();
		t2.start();
		t3.start();
	}
}*/
