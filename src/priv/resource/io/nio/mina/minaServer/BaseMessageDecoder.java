package priv.resource.io.nio.mina.minaServer;

import org.apache.mina.core.buffer.IoBuffer;
import org.apache.mina.core.session.AttributeKey;
import org.apache.mina.core.session.IoSession;
import org.apache.mina.filter.codec.ProtocolDecoderOutput;
import org.apache.mina.filter.codec.demux.MessageDecoder;
import org.apache.mina.filter.codec.demux.MessageDecoderResult;

import priv.utils.LoggerUtil;

public class BaseMessageDecoder implements MessageDecoder {
	AttributeKey CONTEXT = new AttributeKey(getClass(), "context");

	/** �Ƿ��ʺϽ��� */
	public MessageDecoderResult decodable(IoSession session, IoBuffer in) {
		return MessageDecoderResult.OK;
	}

	/** ���ݽ��� */
	public MessageDecoderResult decode(IoSession session, IoBuffer in, ProtocolDecoderOutput outPut) throws Exception {
		Context context = (Context) session.getAttribute(CONTEXT);
		IoBuffer buffer = in;
		if (context == null || !context.init) {
			IoBuffer b = buffer.get(new byte[4]);
			byte[] intByte = b.array();
			int num = b2i(intByte);
			System.out.println("���ȣ�" + num);
			context = new Context();
			context.number = num;
			context.position = 0;
			context.byteArray = new byte[num];
			context.init = true;
			while (buffer.hasRemaining()) {
				byte c = buffer.get();
				context.byteArray[context.position] = c;
				context.position++;
			}
			// �������㹻�����ַ�
			if (num <= 2048-4) {
				IoBuffer ioBuffer = IoBuffer.allocate(1024).setAutoExpand(true);
				ioBuffer.put(i2b(context.number));
				ioBuffer.put(context.byteArray);
				ioBuffer.flip();
				outPut.write(ioBuffer);
				return MessageDecoderResult.OK;
			}
		} else { // ����������ֶ�η���
			while (buffer.hasRemaining()) {
				byte c = buffer.get();
				context.byteArray[context.position] = c;
				if (context.position == context.number - 1) {
					System.out.println(new String(context.byteArray, "utf-8"));
					IoBuffer buff = IoBuffer.allocate(1024).setAutoExpand(true);
					buff.put(i2b(context.number));
					buff.put(context.byteArray);
					buff.flip();
					outPut.write(buff);
					//
					Context cx = null;
					session.setAttribute(CONTEXT, cx);
					return MessageDecoderResult.OK;
				}
				context.position++;
			}
			
			
		}
		session.setAttribute(CONTEXT, context);

		LoggerUtil.info(getClass(), "number:" + context.number + ",position:" + context.position);

		return MessageDecoderResult.NEED_DATA;
	}

	/**
	 * �������
	 * */
	public void finishDecode(IoSession session, ProtocolDecoderOutput outPut) throws Exception {
		LoggerUtil.info(getClass(), "�������!");
	}

	public static int b2i(byte[] b) {
		int value = 0;
		for (int i = 0; i < 4; i++) {
			int shift = (4 - 1 - i) * 8;
			value += (b[i] & 0x000000FF) << shift;
		}
		return value;
	}

	public static byte[] i2b(int i) {
		return new byte[] { (byte) ((i >> 24) & 0xFF), (byte) ((i >> 16) & 0xFF), (byte) ((i >> 8) & 0xFF),
				(byte) (i & 0xFF) };
	}

	class Context {
		public Integer number;
		public byte[] byteArray;
		public Integer position;
		public boolean init;
		
		public Context() {
			LoggerUtil.info(getClass(), "Context initialize");
		}
	}
}
