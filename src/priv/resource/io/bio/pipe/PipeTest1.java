package priv.resource.io.bio.pipe;

import java.io.IOException;
import java.io.PipedInputStream;
import java.io.PipedOutputStream;

public class PipeTest1 {

	public static void main(String[] args) throws IOException {

		final PipedOutputStream pOutputStream = new PipedOutputStream();
		final PipedInputStream pInputStream = new PipedInputStream(pOutputStream);

		Thread t1 = new Thread(new Runnable() {

			@Override
			public void run() {
				try {
					pOutputStream.write("hi~ chenting!".getBytes());
					pOutputStream.close();
				} catch (IOException e) {
					e.printStackTrace();
				}
			}
		});

		Thread t2 = new Thread(new Runnable() {

			@Override
			public void run() {
				try {
					StringBuffer sb = new StringBuffer();
					byte[] bb = new byte[13];
					int data = pInputStream.read(bb);
					// while (data != -1) { // 此种方法只适合读取一个字符？否则将会抛出“java - IOException: Write end dead”
					// sb.append((char)data);
					// data = pInputStream.read();
					// }
					System.out.println(new String(bb));
					pInputStream.close();
				} catch (IOException e) {
					e.printStackTrace();
				}
			}
		});

		t1.start();
		t2.start();
	}
}
