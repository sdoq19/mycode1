package priv.resource.thread.test3;

public class ThreadSafe implements Runnable {
	
	Object obj = new Object();
	
	@Override
	public void run() {
		Singleton singleton = Singleton.getSingleton();
		synchronized (obj) {
			for (int i=0; i<50; i++) {
				singleton.addCount(Thread.currentThread().getName());
			}
		}
	}
	
	public static void main(String[] args) {
		for (int i=0; i<2; i++) {
			new Thread(new ThreadSafe(), "Thread-"+i).start();
		}
	}
}

class Singleton {
	private static Singleton singleton = null;
	
	private int count;
	
	private static Object object = new Object();
	
	private Singleton() {
	}
	
	public static Singleton getSingleton() {
		if (singleton == null) {
			synchronized(object) {
				if (singleton == null) {
					singleton = new Singleton();
				}
			}
		}
		return singleton;
	}
	
	public void addCount(String threadName) {
		System.out.println(threadName + ":" + count++);
	}
}