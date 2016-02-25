package com.resource.thread.forFun;

/**
 * 创建一个军队线程
 * 
 * @author feizi
 * @time 2014-11-14下午2:31:04
 */
public class ArmyRunnable implements Runnable {

	// 这里的volatile关键字具体的用法还不是非常了解，只是看了相关博客之后有了一个概念和认识Java语言是支持多线程的，为了解决线程并发的问题，在语言内部引入了 同步块 和 volatile 关键字机制。
	// 具体的可以参考博客园的这篇博客：http://www.cnblogs.com/aigongsi/archive/2012/04/01/2429166.html
	volatile boolean keepRunning = true;

	public void run() {
		System.out.println("全军出击..." + Thread.currentThread().getName() + "向对方发起了进攻！");

		// 连续进攻，收到命令，才停止进攻
		while (keepRunning) {
			// 5连击
			for (int i = 0; i < 5; i++) {
				System.out.println(Thread.currentThread().getName() + "攻击了对方[" + i + "]次...");

				// 让出处理器的资源，下次该谁进攻还不一定。（yield方法：暂停当前正在执行的线程对象，并执行其他线程。 ）
				Thread.yield();
			}
		}
		System.out.println("鸣金收兵..." + Thread.currentThread().getName() + "停止了进攻！");
	}

}