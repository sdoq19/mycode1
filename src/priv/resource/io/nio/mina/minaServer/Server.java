package priv.resource.io.nio.mina.minaServer;

import java.net.InetSocketAddress;
import java.util.concurrent.Executors;

import org.apache.mina.core.session.IdleStatus;
import org.apache.mina.filter.codec.ProtocolCodecFilter;
import org.apache.mina.filter.executor.ExecutorFilter;
import org.apache.mina.transport.socket.SocketAcceptor;
import org.apache.mina.transport.socket.nio.NioSocketAcceptor;

import priv.utils.LoggerUtil;

public class Server {
	public static void main(String[] args) {
		new Server().newNioSocket();
	}

	public void newNioSocket() {
		SocketAcceptor acceptor = new NioSocketAcceptor(Runtime.getRuntime().availableProcessors() + 1);
		acceptor.getSessionConfig().setReadBufferSize(1024 * 2); // ���ö�ȡ����Ĵ�С,����Ϣ���Ȳ���,�򲻽��в���
		acceptor.getSessionConfig().setIdleTime(IdleStatus.BOTH_IDLE, 10);
		acceptor.getSessionConfig().setReuseAddress(true);
		acceptor.getSessionConfig().setTcpNoDelay(true);
		acceptor.getFilterChain().addLast("codec", new ProtocolCodecFilter(new MathProtocolCodecFactory(true)));
		acceptor.getFilterChain().addLast("threadPool", new ExecutorFilter(Executors.newCachedThreadPool()));
		acceptor.setHandler(new ServerHandler());
		try {
			acceptor.bind(new InetSocketAddress(22222));
			LoggerUtil.info(Server.class, "mina���������ɹ���");
		} catch (Exception e) {
			LoggerUtil.error(Server.class, e);
		}
	}
}
