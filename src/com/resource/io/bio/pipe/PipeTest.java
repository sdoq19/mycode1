package com.resource.io.bio.pipe;

import java.io.IOException;
import java.io.PipedInputStream;
import java.io.PipedOutputStream;

/**
 * 管道通信demo
 * ①若将第46行和第52行注释掉的代码加上，将会抛出“java - IOException: Read end dead”。
 * ②若将第66行和第72行注释掉的代码加上，将会抛出“java - IOException: Write end dead”。
 * 原因都是一样的：在利用管道读写数据时，必须保证利用管道读写数据的线程都不能退出。
 * 针对上面的程序，如果是第①种情况，是因为Consumer(消费者)线程在读出管道中的数据后，线程就运行结束退出了。
 * 这时再向建立链接管道的线程Producer中写入数据时就会抛出异常。
 * 同样，如果是第②种情况就，是因为Producer(生产者)线程在写入管道中的数据后，线程就运行结束退出了。这时再由建立链接管道的线程Consumer中读出数据时就会抛出异常。
 * @author csc
 * 
 */
public class PipeTest {

	public static void main(String[] args) {

		// 创建管道输出流
		PipedOutputStream pos = new PipedOutputStream();
		// 创建管道输入流
		PipedInputStream pis = new PipedInputStream();
		try {
			// 将管道输入流与输出流连接 此过程也可通过重载的构造函数来实现
			pos.connect(pis);
		} catch (IOException e) {
			e.printStackTrace();
		}
		// 创建生产者线程
		Producer p = new PipeTest().new Producer(pos);
		// 创建消费者线程
		Consumer c = new Consumer(pis);
		// 启动线程
		p.start();
		c.start();
	}

	// 生产者线程(与一个管道输入流相关联)
	private class Producer extends Thread {
		private PipedOutputStream pos;

		public Producer(PipedOutputStream pos) {
			this.pos = pos;
		}

		public void run() {
			int i = 8;
			// while (true) {//加入此句将出现“java - IOException: Read end dead”异常
			try {
				pos.write(i);
			} catch (IOException e) {
				e.printStackTrace();
			}
			// }

		}
	}

	// 消费者线程(与一个管道输入流相关联)
	private static class Consumer extends Thread {
		private PipedInputStream pis;

		public Consumer(PipedInputStream pis) {
			this.pis = pis;
		}

		public void run() {
			// while(true){//加入此句将出现“java - IOException: Write end dead”异常
			try {
				System.out.println(pis.read());
			} catch (IOException e) {
				e.printStackTrace();
			}
			// }

		}
	}
}