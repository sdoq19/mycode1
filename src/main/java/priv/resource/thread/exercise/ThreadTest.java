package priv.resource.thread.exercise;

/**
 * 测试继承Thread和实现Runnable方式实现多线程的区别
 * 
 */
public class ThreadTest {

	// public static void main(String[] args) {
	// int i=0;
	// while(i<100) {
	// i++;
	// Thread t1 = new Thread(new Runnable() {
	// @Override
	// public void run() {
	// System.out.println(Thread.currentThread().getId() +
	// Thread.currentThread().getName());
	// try {
	// Thread.sleep(1000l);
	// } catch (InterruptedException e) {
	// e.printStackTrace();
	// Thread.interrupted();
	// }
	// }
	// });
	// t1.start();
	// }
	// }

	public static void main(String[] args) throws InterruptedException {
		System.out.println("-----------------此种方式产生10个线程实例，生成10个线程");
		for (int i = 0; i < 10; i++) {
			Thread thread = new A();
			thread.start();
		}

		Thread.sleep(1000l);
		System.out.println("-----------------此种方式产生10个线程实例，生成10个线程");

		for (int i = 0; i < 10; i++) {
			Thread thread = new Thread(new R());
			thread.start();
		}
		Thread.sleep(1000l);
		System.out.println("-----------------此种方式产生1个线程实例，生成10个线程，这10个线程称为同一实例(Runnable实例)的多个线程");
		R r = new R();
		for (int i = 0; i < 10; i++) {
			Thread thread = new Thread(r);
			thread.start();
		}
	}
}

class A extends Thread {

	public int x = 0;

	@Override
	public void run() {
		x++;
		System.out.println(x + ":" + Thread.currentThread().getName());
	}
}

class R implements Runnable {

	public int x = 0;

	@Override
	public void run() {
		x++;
		System.out.println(x + ":" + Thread.currentThread().getName());
	}
}
