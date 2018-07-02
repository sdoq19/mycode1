package priv.resource.io.nio.mina.minaServer;

import java.util.Date;

import org.apache.mina.core.buffer.IoBuffer;
import org.apache.mina.core.service.IoHandlerAdapter;
import org.apache.mina.core.session.IdleStatus;
import org.apache.mina.core.session.IoSession;

import priv.utils.LoggerUtil;

public class ServerHandler extends IoHandlerAdapter {

	@Override
	public void sessionCreated(IoSession session) throws Exception {
		LoggerUtil.info(getClass(), "ͨ������[sessionCreated]");
	}

	@Override
	public void sessionOpened(IoSession session) throws Exception {
		LoggerUtil.info(getClass(), "ͨ����[sessionOpened]");
	}

	@Override
	public void messageReceived(IoSession session, Object message) throws Exception {
		LoggerUtil.info(getClass(), "������Ϣʱ��: " + new Date(System.currentTimeMillis()).toString());
		// ��IoBufferת�����ַ���
		int length = ((IoBuffer)message).limit();
		byte[] bts = new byte[length];
		((IoBuffer)message).get(bts);
		String msg = new String(bts, "UTF-8").trim();

		if (msg.indexOf("%bye%") > 0) {
			LoggerUtil.info(getClass(), message.toString());
			session.write(message);
			session.close(true);
		}
		session.write(message);
	}

	@Override
	public void sessionClosed(IoSession session) throws Exception {
		LoggerUtil.info(getClass(), "ͨ���ر�[sessionClosed]");
	}

	@Override
	public void sessionIdle(IoSession session, IdleStatus is) throws Exception {
		LoggerUtil.info(getClass(), "ͨ������[sessionIdle]");
	}

	@Override
	public void exceptionCaught(IoSession session, Throwable cause) throws Exception {
		LoggerUtil.error(getClass(), ">>>>>>>>>>>>>�����쳣��\n" + cause.toString());
	}

}
