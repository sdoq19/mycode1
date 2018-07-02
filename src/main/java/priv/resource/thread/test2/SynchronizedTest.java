package priv.resource.thread.test2;

public class SynchronizedTest {
	
	public static void main (String args[]) {
		Service service = new Service();
		
		Thread t1 = new Thread(new Thread1(service));
		Thread t2 = new Thread(new Thread2(service));
		t1.start();
		t2.start();
	}

}
