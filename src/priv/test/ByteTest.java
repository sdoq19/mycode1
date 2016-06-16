package priv.test;

import java.io.UnsupportedEncodingException;

public class ByteTest {
	
	public static void main(String[] args) {
		ByteTest bt = new ByteTest();
		bt.bytesToInt();
		
	}
	
	public void bytesToInt() {
		String bracket = "{}1234567890";
		try {
			byte[] bts = bracket.getBytes("utf-8");
			for (int i = 0; i < bts.length; i++) {
				byte b = bts[i];
				System.out.println(b);
			}
		} catch (UnsupportedEncodingException e) {
			e.printStackTrace();
		}
	}
}
