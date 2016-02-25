package com.resource.io.nio.mina.minaServer;

import java.net.InetSocketAddress;
import java.util.concurrent.Executors;

import org.apache.mina.core.session.IdleStatus;
import org.apache.mina.filter.codec.ProtocolCodecFilter;
import org.apache.mina.filter.executor.ExecutorFilter;
import org.apache.mina.transport.socket.SocketAcceptor;
import org.apache.mina.transport.socket.nio.NioSocketAcceptor;

import com.utils.LoggerUtil;

public class Server {
	public static void main(String[] args) {
		new Server().newNioSocket();
	}

	public void newNioSocket() {
		SocketAcceptor acceptor = new NioSocketAcceptor(Runtime.getRuntime().availableProcessors() + 1);
		acceptor.getSessionConfig().setReadBufferSize(1024 * 2); // 设置读取缓冲的大小,若消息长度不足,则不进行操作
		acceptor.getSessionConfig().setIdleTime(IdleStatus.BOTH_IDLE, 10);
		acceptor.getSessionConfig().setReuseAddress(true);
		acceptor.getSessionConfig().setTcpNoDelay(true);
		acceptor.getFilterChain().addLast("codec", new ProtocolCodecFilter(new MathProtocolCodecFactory(true)));
		acceptor.getFilterChain().addLast("threadPool", new ExecutorFilter(Executors.newCachedThreadPool()));
		acceptor.setHandler(new ServerHandler());
		try {
			acceptor.bind(new InetSocketAddress(22222));
			LoggerUtil.info(Server.class, "mina服务启动成功！");
		} catch (Exception e) {
			LoggerUtil.error(Server.class, e);
		}
	}
}
