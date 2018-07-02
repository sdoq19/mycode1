package priv.resource.thread.test3;

import priv.utils.LoggerUtil;

/**
 * 静态变量、实例变量、局部变量线程安全问题
 * 静态变量：线程非安全。
 * 静态变量即类变量，位于方法区，为所有对象共享，共享一份内存，一旦静态变量被修改，其他对象均对修改可见，故线程非安全。
 * 实例变量：单例模式（只有一个对象实例存在）线程非安全，非单例线程安全。
 * 实例变量为对象实例私有，在虚拟机的堆中分配，若在系统中只存在一个此对象的实例，在多线程环境下，“犹如”静态变量那样，被某个线程修改后，其他线程对修改均可见，故线程非安全；如果每个线程执行都是在不同的对象中，那对象与对象之间的实例变量的修改将互不影响，故线程安全。
 * 局部变量：线程安全。
 * 每个线程执行时将会把局部变量放在各自栈帧的工作内存中，线程间不共享，故不存在线程安全问题。
 */
public class VariableTest extends Thread {

	private static int i = 0;
	private static Object obj = new Object();
	
	@Override
	public void run() {
		for (int j = 0; j < 10000; j++) {
			if (Thread.currentThread().getName().equals("Thread-1")
					|| Thread.currentThread().getName().equals("Thread-3")
					|| Thread.currentThread().getName().equals("Thread-5")
					|| Thread.currentThread().getName().equals("Thread-7")
					|| Thread.currentThread().getName().equals("Thread-9")) {
				synchronized (obj) { // 若不加锁，则结果不为0
					i++;
				}
			} else {
				synchronized (obj) {
					i--;
				}
			}
			LoggerUtil.info(getClass(), Thread.currentThread().getName() + ":" + i + "");
		}
	}

	public static void main(String[] args) {
		new VariableTest().start();
		new VariableTest().start();
		new VariableTest().start();
		new VariableTest().start();
		new VariableTest().start();
		new VariableTest().start();
		new VariableTest().start();
		new VariableTest().start();
		new VariableTest().start();
		new VariableTest().start();
	}

}
