package priv.resource.io.nio.mina.socketClient;

import java.io.InputStream;
import java.io.OutputStream;
import java.net.Socket;

import priv.utils.LoggerUtil;

public class Client {

	public static void main(String[] args) throws Exception {
		Socket socket = new Socket("127.0.0.1", 22222);
		OutputStream os = socket.getOutputStream();
		StringBuffer str = new StringBuffer();
		for (int i = 0; i < 100; i++) {
			str = str.append("�ร���ٶร���ٶร����");
		}
		byte[] bytes = str.toString().getBytes("utf-8");
		LoggerUtil.info(Client.class, "����byte���ȣ�" + bytes.length);
		os.write(i2b(bytes.length));
		os.write(bytes);
		os.flush();

		InputStream is = null;

		is = socket.getInputStream();
		byte[] intByte = new byte[4];
		is.read(intByte);
		int msgLength = b2i(intByte);
		LoggerUtil.info(Client.class, "int:" + msgLength);
		int readPosition = 0;
		byte[] byteArray = new byte[msgLength];
		while (readPosition < msgLength) {
			int value = is.read(byteArray, readPosition, msgLength - readPosition);
			if (value == -1) {
				break;
			}
			readPosition += value;

		}
		LoggerUtil.info(Client.class, new String(byteArray, "utf-8"));
		LoggerUtil.info(Client.class, "byteArray Length:" + byteArray.length + " string Length:"
				+ new String(byteArray, "utf-8").length());

		
		// �ر�ͨ��
		StringBuffer sb = new StringBuffer();
		sb.append("%bye%");
		byte[] byeBytes = sb.toString().getBytes("utf-8");

		os.write(i2b(byeBytes.length));
		os.write(byeBytes);
		os.flush();
		
		if (os != null) {
			os.close();
		}
		if (is != null) {
			is.close();
		}
	}

	// ���ϳ����ģ��� int ת���ֽ�
	public static byte[] i2b(int i) {
		return new byte[] { (byte) ((i >> 24) & 0xFF), (byte) ((i >> 16) & 0xFF), (byte) ((i >> 8) & 0xFF),
				(byte) (i & 0xFF) };
	}

	public static int b2i(byte[] b) {
		int value = 0;
		for (int i = 0; i < 4; i++) {
			int shift = (4 - 1 - i) * 8;
			value += (b[i] & 0x000000FF) << shift;
		}
		return value;
	}

}
