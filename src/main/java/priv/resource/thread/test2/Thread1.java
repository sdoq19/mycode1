package priv.resource.thread.test2;

public class Thread1 implements Runnable {
	
	Service service;
	
	public Thread1(Service service) {
		this.service = service;
	}
	
	public void run() {
		service.fun1();
	}
}
