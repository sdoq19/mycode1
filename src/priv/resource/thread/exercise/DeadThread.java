package priv.resource.thread.exercise;

/**
 * 死锁产生的四个必要条件:

 *  1>互斥使用，即当资源被一个线程使用(占有)时，别的线程不能使用

 *  2>不可抢占，资源请求者不能强制从资源占有者手中夺取资源，资源只能由资源占有者主动释放。

 *  3>请求和保持，即当资源请求者在请求其他的资源的同时保持对原有资源的战友。

 *  4>循环等待，即存在一个等待队列：P1占有P2的资源，P2占有P3的资源，P3占有P1的资源。这样就形成了一个等待环路。
 */

/**
 * 该类存放两个资源等待被使用
 * 
 * @author lu
 * 
 */
class Resource {

	public static Object o1 = new Object();
	public static Object o2 = new Object();

}

/**
 * 线程启动调用run(),run()调用fun()方法
 * 
 */
class DeadThread1 implements Runnable {

	@Override
	public void run() {
		fun();
	}

	// fun()方法首先占用o1资源,然后休眠1秒,让给其他线程执行。
	// 然后请求o2资源
	public void fun() {
		synchronized (Resource.o1) {
			try {
				Thread.sleep(1000);
			} catch (InterruptedException e) {
			}
			synchronized (Resource.o2) {
				System.out.println("DeadThread1里的fun()被执行");
			}
		}
	}

}

/**
 * 线程启动调用run(),run()调用fun()方法
 * 
 * @author lu
 * 
 */
class DeadThread2 implements Runnable {

	@Override
	public void run() {
		fun();
	}

	// fun()方法首先占用o2资源,然后休眠1秒,让给其他线程执行。
	// 然后请求o1资源
	public void fun() {
		synchronized (Resource.o2) {
			try {
				Thread.sleep(1000);
			} catch (InterruptedException e) {
			}

			synchronized (Resource.o1) {
				System.out.println("DeadThread1里的fun()被执行");
			}
		}
	}

}

/**
 * 客户端
 * 
 */
public class DeadThread {

	public static void main(String[] args) {
		DeadThread1 dt1 = new DeadThread1();
		DeadThread2 dt2 = new DeadThread2();

		Thread t1 = new Thread(dt1);
		Thread t2 = new Thread(dt2);

		// 启动两个线程
		t1.start();
		t2.start();

	}
}