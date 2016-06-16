package priv.resource.io.aio;

import java.net.InetSocketAddress;
import java.nio.ByteBuffer;
import java.nio.channels.AsynchronousSocketChannel;

public class AIOClient {

	public static void main(String[] args) throws Exception {
		AsynchronousSocketChannel client = AsynchronousSocketChannel.open();
		client.connect(new InetSocketAddress("localhost", 19888));
		client.write(ByteBuffer.wrap("hello world!".getBytes())).get();
	}
}