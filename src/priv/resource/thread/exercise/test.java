package priv.resource.thread.exercise;

import java.util.concurrent.atomic.AtomicInteger;

/**
 * 实现功能：
 * id name date
 * id为主键
 * 现在要求用10个线程向这张表中插入10000条数据  
 */
public class test {
	
	/**
	private static int id = 0;
	
	public synchronized static int getId() {
		return id++;
	}
	*/
	
	private static AtomicInteger id = new AtomicInteger(0);
	
	public static int getId() {
		return id.getAndIncrement();
	}
	
	public static void main(String[] args) {
		for (int i=0; i<10; i++) {
			Thread thread = new Thread(new Runnable() {
				
				@Override
				public void run() {
					int j = 0;
					while (j<100) {
						System.out.println("当前线程" + Thread.currentThread().getName() + ":插入数据主键" + getId());
						j ++;
					}
				}
			});
			thread.start();
		}
	}
}
