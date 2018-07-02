package priv.resource.thread.test3;

import java.io.File;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.PrintWriter;


public class StringBufferTest extends Thread {

	String message;
	Contents buffer;

	public StringBufferTest(Contents buffer, String message) {
		this.buffer = buffer;
		this.message = message;
	}

	public void run() {
		while (true) {
			buffer.log(message);
			buffer.getContents();
		}
	}

	public static void main(String[] args) throws IOException {
		//Contents ss = new Contents();
		//new StringBufferTest(ss, "you").start();
		//new StringBufferTest(ss, "me").start();
		//new StringBufferTest(ss, "she").start();
		
		StringBuffer sb = new StringBuffer();
		sb.append("lalalalala...");
		File file = new File("C://Users//lenovo//Desktop//aa.txt");
		FileOutputStream fo = new FileOutputStream(file);
		PrintWriter pw = new PrintWriter(fo);
		pw.write(sb.toString().toCharArray());	
		pw.close();
		fo.close();
	}
}

class Contents {
	private StringBuffer contents = new StringBuffer();

	public void log(String message) {
		contents.append(System.currentTimeMillis());
		contents.append("; ");
		contents.append(Thread.currentThread().getName());
		for (int i = 0; i < 500; i++) {
			contents.append(i);
			contents.append(message); // append本身是线程安全的，修改contents时，其它线程无法访问
			contents.append("\n");
		}
		contents.append("\n\n");
	}

	public void getContents() {
		System.out.println(contents);
	}
}