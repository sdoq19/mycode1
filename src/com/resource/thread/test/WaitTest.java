package com.resource.thread.test;

class A implements Runnable {

	C c = null;

	public A(C c) {
		this.c = c;
	}

	@Override
	public void run() {
		int i = 0;
		while (i < 100) {
			synchronized (c) {
				System.out.println("A:" + i);
				i++;
				/*
				 * try { Thread.sleep(1000); } catch (InterruptedException e) { e.printStackTrace(); }
				 */
				if (i == 10) {
					try {
						c.wait();
					} catch (InterruptedException e) {
						e.printStackTrace();
					}
				}
			}
		}
	}

}

class B implements Runnable {
	C c = null;

	public B(C c) {
		this.c = c;
	}

	@Override
	public void run() {
		int i = 0;
		while (i < 100) {
			synchronized (c) {
				System.out.println("B:" + i);
				i++;
				/*
				 * try { Thread.sleep(1000); } catch (InterruptedException e) { e.printStackTrace(); }
				 */
				if (i == 100) {
					c.notifyAll();
				}
			}
		}
	}

}

class C {
	public void print() {
		System.out.println("---class C---");
	}
}

public class WaitTest {

	public static void main(String[] args) {

		C c = new C();
		Thread a = new Thread(new A(c));
		Thread b = new Thread(new B(c));
		a.start();
		b.start();
	}

}
