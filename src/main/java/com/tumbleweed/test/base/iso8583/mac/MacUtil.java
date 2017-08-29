package com.tumbleweed.test.base.iso8583.mac;


/**
 * 验证MAC相关工具类
 * 
 * @Description:TODO
 * @author: XB
 * @date: 2014年3月28日
 */
public class MacUtil {

	protected final static int SUCC_FLG = 0;

	public static String macCal(int dataLen, String data) {

		byte[] buf = new byte[8];
		int len = dataLen;
		int l = len / 8 + 1;

		int filLen = l * 16 - data.length();

		String tmpData = fullZero(data, filLen);
		byte[] inbuf = ConvHelper.hexToByteArray(tmpData);

		for (int i = 0; i < l; i++) {
			for (int k = 0; k < 8; k++) {
				buf[k] = (byte) (buf[k] ^ inbuf[i * 8 + k]);
			}
		}

		String makCal = ConvHelper.ascii2Str(ConvHelper.bcd2Ascii(buf));
		return makCal;
	}

	public String bcd2asc(String MacValue) throws Exception {

		String data = MacValue;
		if (data == null) {
			data = "";
		}

		String macValue = ConvHelper.bcd2AscStr(data.getBytes());
		return macValue.substring(0, 16);
	}

	private static String fullZero(String para, int filLen) {

		StringBuffer sbff = new StringBuffer();

		for (int i = 0; i < filLen; i++) {
			sbff.append("0");
		}
		return para + sbff.toString();

	}
}
