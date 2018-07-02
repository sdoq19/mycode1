package priv.resource.io.nio.mina.minaServer;

import org.apache.mina.core.session.IoSession;
import org.apache.mina.filter.codec.ProtocolEncoderOutput;
import org.apache.mina.filter.codec.demux.MessageEncoder;

import priv.utils.LoggerUtil;

public class BaseMessageEncoder implements MessageEncoder<Object> {

	/** ������Ϣ���� */
	public void encode(IoSession session, Object message, ProtocolEncoderOutput outPut) throws Exception {
		// IoBuffer buffer = IoBuffer.allocate(1024*1024*50);
		// buffer.flip();
		outPut.write(message);
		LoggerUtil.info(getClass(), "������ɣ�");
	}
}
