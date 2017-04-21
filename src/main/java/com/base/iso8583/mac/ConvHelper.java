package com.base.iso8583.mac;

import org.apache.commons.codec.DecoderException;
import org.apache.commons.codec.binary.Hex;
import org.apache.commons.lang3.StringUtils;

import java.io.UnsupportedEncodingException;
import java.security.InvalidParameterException;

/**
 * Copyright (C),1998 -- 2006 Hisuntech Co..Ld
 * 
 * HiConvHelper.java 2006-10-20
 * 
 */

public class ConvHelper {

	private final static char[] mChars = "0123456789ABCDEF".toCharArray();

	/**
	 * 把二进制格式转为十进制整数
	 * 
	 * @param bin
	 *            二进制形式的字符串,eg: "10101010"
	 * @return 十进制整数
	 */
	public static int binary2Int(String bin) {
		return Integer.valueOf(bin, 2).intValue();
	}

	/**
	 * 把十进制整数转化为二进制格式
	 * 
	 * @param val
	 * @return 二进制形式的字符串,eg: "10101010"
	 */
	public static String int2Binary(int val) {
		return Integer.toBinaryString(val);
	}

	/**
	 * 把十六进制整数转化为二进制格式
	 * 
	 * @param val
	 * @return 二进制形式的字符串,eg: "10101010"
	 */
	public static String hex2Binary(String val) throws Exception {
		StringBuffer ret = new StringBuffer();

		for (int i = 0; i < val.length(); i++) {
			ret.append(hex2binary(val.charAt(i)));
		}
		return ret.toString();
	}

	public static String hex2binary(char hex) throws Exception {
		switch (hex) {
		case '0':
			return "0000";
		case '1':
			return "0001";
		case '2':
			return "0010";
		case '3':
			return "0011";
		case '4':
			return "0100";
		case '5':
			return "0101";
		case '6':
			return "0110";
		case '7':
			return "0111";
		case '8':
			return "1000";
		case '9':
			return "1001";
		case 'a':
		case 'A':
			return "1010";
		case 'b':
		case 'B':
			return "1011";
		case 'c':
		case 'C':
			return "1100";
		case 'd':
		case 'D':
			return "1101";
		case 'e':
		case 'E':
			return "1110";
		case 'f':
		case 'F':
			return "1111";
		default:
			throw new Exception("非法字符");
		}
	}

	/**
	 * 将一串二进制转为十六进制
	 * 
	 * @param binary
	 * @return
	 */
	public static String binary2hex(String binary) {
		String hexString = "";
		int binLen = binary.length();
		if (binLen % 4 != 0) {
			binary = StringUtils.repeat("0", 4 - binLen % 4) + binary;
			binLen = binary.length();
		}
		for (int i = 0; i < binLen; i = i + 4) {
			hexString += Integer.toHexString(Integer.valueOf(
					binary.substring(i, i + 4), 2).intValue());
		}
		return hexString;
	}

	public static String byte2String(byte val) throws Exception {
		byte[] arrVal = { val };
		return byte2String(arrVal, "ISO-8859-1");
	}

	public static String byte2String(byte[] val, String charset)
			throws Exception {
        return new String(val, charset);

	}

	/**
	 * bin转为对应的Ascii 形式.
	 * 
	 * @param binStr
	 * @return
	 */
	public static String binToAscStr(String binStr) throws Exception {
		/**
		 * byte[] chrBytes = binStr.getBytes(); return
		 * String.valueOf(byte2uint(chrBytes, 0));
		 */

		return binToAscStr(binStr.getBytes());
	}

	/**
	 * bin转为对应的Ascii 形式.
	 * 
	 * @param binBuf
	 * @return
	 */
	public static String binToAscStr(byte[] binBuf) {
		long ascVal = 0;
		for (int i = 0; i < binBuf.length; i++) {
			ascVal = (ascVal << 8) + (long) (binBuf[i] & 0xff);
		}

		return String.valueOf(ascVal);
	}

	/**
	 * 将一个ASCII值转为对应的字符
	 * 
	 * @param strAsc
	 *            ASCII值
	 * @return 字符
	 */

	public static String asc2bin(String strAsc) throws Exception {
        Integer deliInt = Integer.valueOf(strAsc);

        byte[] asc = { deliInt.byteValue() };
        return new String(asc, "ISO-8859-1");

	}

	/**
	 * public static byte asc2bin(String strAsc) throws BaseException { try {
	 * return Integer.valueOf(strAsc).byteValue(); } catch(NumberFormatException
	 * e) { // TODO log asc error throw new
	 * BaseException("","asc2bin执行出错, 一个ASCII值 + [" + strAsc+
	 * "],转为对应的字符时失败.",e); } }
	 */
	public static String asc2bin(int intAsc) throws Exception {
		byte[] aryAsc = new byte[4];

		int2byte(aryAsc, 0, intAsc);
		/**
		 * String retStr = ""; boolean start = true; for (int i = 0; i < 4; i++)
		 * { if (aryAsc[i] == 0x00 && start) { continue; } start = false; retStr
		 * += (char)aryAsc[i]; }
		 **/
		// return new String(aryAsc);
		return byte2String(aryAsc, "ISO-8859-1");
	}

	/**
	 * 将ascii串转为对应的bin; binLen限制转换后的宽度, 若binLen为0,则不限制宽度;若超过,则抛异常,否则前置加0x00
	 * 
	 * @param intAsc
	 * @param binLen
	 * @return
	 */
	public static String asc2bin(int intAsc, int binLen) throws Exception {
		byte[] aryAsc = new byte[4];

		int2byte(aryAsc, 0, intAsc);
		String retStr = "";

		boolean start = true;
		for (int i = 0; i < 4; i++) {
			if (aryAsc[i] == 0x00 && start) {
				continue;
			}
			start = false;
			retStr += (char) aryAsc[i];
		}

		if (retStr.length() < binLen) {
			char fill_asc = (byte) 0x00;
			String fill_str = String.valueOf(fill_asc);

			retStr = StringUtils.repeat(fill_str, binLen - retStr.length())
					+ retStr;
		} else if (retStr.length() > binLen) {
			throw new Exception("error");
		}

		return retStr;
	}

	// --------------------------------------------------------------------
	// bcd ascii convHelper
	public static String bcd2AscStr(byte[] bytes) {
		return ascii2Str(bcd2Ascii(bytes));
	}

	public static byte[] ascStr2Bcd(String s) {
		return ascii2Bcd(str2Ascii(s));
	}

	final static char[] ascii = "0123456789ABCDEF".toCharArray();

	public static byte[] bcd2Ascii(byte[] bytes) {
		byte[] temp = new byte[bytes.length * 2];

		for (int i = 0; i < bytes.length; i++) {
			temp[i * 2] = (byte) ((bytes[i] >> 4) & 0x0f);
			temp[i * 2 + 1] = (byte) (bytes[i] & 0x0f);

		}
		return temp;
	}

	public static byte[] str2Ascii(String s) {
		byte[] str = s.toUpperCase().getBytes();
		byte[] ascii = new byte[str.length];
		for (int i = 0; i < ascii.length; i++) {
			ascii[i] = (byte) asciiValue(str[i]);
		}
		return ascii;
	}

	public static String ascii2Str(byte[] ascii) {
		StringBuffer res = new StringBuffer();
		for (int i = 0; i < ascii.length; i++) {
			res.append(strValue(ascii[i]));
		}
		return res.toString();
	}

	private static char strValue(byte asc) {
		if (asc < 0 || asc > 15)
			throw new InvalidParameterException();
		return ascii[asc];
	}

	public static byte[] ascii2Bcd(byte[] asc) {
		int len = asc.length / 2;
		byte[] bcd = new byte[len];
		for (int i = 0; i < len; i++) {
			bcd[i] = (byte) ((asc[2 * i] << 4) | asc[2 * i + 1]);
		}
		return bcd;
	}

	private static int asciiValue(byte b) {
		if ((b >= '0') && (b <= '9')) {
			return (b - '0');
		}
		if ((b >= 'a') && (b <= 'f')) {
			return (b - 'a') + 0x0a;
		}
		if ((b >= 'A') && (b <= 'F')) {
			return (b - 'A') + 0x0a;
		}

		throw new InvalidParameterException();
	}

	public static void printByte(byte[] b) {
		for (int i = 0; i < b.length; i++) {
			System.out.print(b[i] + " ");
		}
		System.out.println();
	}

	// ---------------------------------------------------------------------
	/**
	 * Converts two bytes into a short.
	 * 
	 * Note: 字节数组bp,长度必须不能大于index+2
	 * 
	 * The short should be interpreted as an unsigned short and if you want to
	 * add it to an int then do something like the code fragment below.
	 * 
	 * <PRE>
	 * int sum = 0;
	 * sum += byte2short(buf, index) &amp; 0xffff;
	 * </PRE>
	 */
	public static short byte2short(byte[] bp, int index) {
		return (short) (((bp[index] & 0xff) << 8) + (bp[index + 1] & 0xff));
	}

	/**
	 * Converts four bytes into an int.
	 * 
	 * Note: 字节数组bp的长度必须不能大于index+4
	 */
	public static int byte2int(byte[] bp, int index) {
		return (int) (((bp[index] & 0xff) << 24)
				+ ((bp[index + 1] & 0xff) << 16)
				+ ((bp[index + 2] & 0xff) << 8) + ((bp[index + 3] & 0xff)));
	}

	/**
	 * Convert short into two bytes in buffer.
	 **/
	public static void short2byte(byte[] bp, int index, short value) {
		bp[index] = (byte) ((value >> 8) & 0xff);
		bp[index + 1] = (byte) (value & 0xff);
	}

	/**
	 * Convert int into four bytes in buffer.
	 **/
	public static void int2byte(byte[] bp, int index, int value) {
		bp[index] = (byte) ((value >> 24) & 0xff);
		bp[index + 1] = (byte) ((value >> 16) & 0xff);
		bp[index + 2] = (byte) ((value >> 8) & 0xff);
		bp[index + 3] = (byte) (value & 0xff);
	}

	/**
	 * Convert an integer to an unsigned integer, represented by a long type.
	 **/
	public static long int2uint(int x) {
		return ((((long) x) << 32) >>> 32);
	}

	/**
	 * Convert an integer to an unsigned integer, represented by a long type.
	 **/
	public static long byte2uint(byte[] x, int offs) {
		long z = 0;
		for (int i = 0; i < 4; i++) {
			z = (z << 8) + (long) (x[offs + i] & 0xff);
		}
		return z;
	}

	/**
	 * Convert an array of two long integers to an byte array
	 **/
	public static byte[] uint2byte(long[] x) {
		byte[] res = new byte[8];
		int2byte(res, 0, (int) (x[0]));
		int2byte(res, 4, (int) (x[1]));
		return res;
	}

	/**
	 * Convert an array of a long integer to an byte array
	 **/
	public static byte[] long2byte(long x) {
		byte[] res = new byte[8];
		int2byte(res, 0, (int) ((x >> 32) & 0xffffffff));
		int2byte(res, 4, (int) (x & 0xffffffff));
		return res;
	}

	public static long byte2long(byte[] msg, int offs) {
		long high = byte2uint(msg, offs);
		offs += 4;
		long low = byte2uint(msg, offs);
		offs += 4;
		long ans = (high << 32) + low;
		return ans;
	}

	/*
	 * Make printable form for boolean array
	 */
	public static String boolean2String(boolean[] ba) {
		StringBuffer strb = new StringBuffer();
		int cnt = 0;

		if (ba == null || ba.length == 0) {
			return "(none)";
		}

		for (int i = 0; i < ba.length; i++) {
			if (ba[i]) {
				if (cnt++ != 0) {
					strb.append("+");
				}
				strb.append(i);
			}
		}
		return strb.toString();
	}

	public static String convFlags(String equiv, byte flags) {
		char chs[] = new char[8];
		StringBuffer strb = new StringBuffer(" ");
		int bit, i;

		if (equiv.length() > 8) {
			return (">8?");
		}
		equiv.getChars(0, equiv.length(), chs, 0);

		for (bit = 0x80, i = 0; bit != 0; bit >>= 1, i++) {
			if ((flags & bit) != 0) {
				strb.setCharAt(0, '*');
				strb.append(chs[i]);
			}
		}
		return strb.toString();
	}

	public static String timer2string(long time) {
		String timeString = null;

		long msec = time % 1000;
		String ms = String.valueOf(msec);
		ms = fill(ms, 3, "0");

		long rem = time / 1000; // in seconds
		int xsec = (int) (rem % 60);
		rem = (int) ((rem - xsec) / 60); // in minutes
		int xmin = (int) (rem % 60);
		rem = (int) ((rem - xmin) / 60); // in hours
		int xhour = (int) (rem % 24);
		int xday = (int) ((rem - xhour) / 24);

		String sday = String.valueOf(xday);
		String shour = String.valueOf(xhour);
		shour = fill(shour, 2, "0");
		String smin = String.valueOf(xmin);
		smin = fill(smin, 2, "0");
		String ssec = String.valueOf(xsec);
		ssec = fill(ssec, 2, "0");

		timeString = sday + " days, " + shour + ":" + smin + ":" + ssec + "."
				+ ms;
		return timeString;
	}

	private static String fill(String str, int sz, String cfill) {
		while (str.length() < sz) {
			str = cfill + str;
		}
		return str;
	}

	/**
	 * decode Asc to bcd
	 * 
	 * @param bytes
	 * @return
	 */
	public static byte[] ascByte2Bcd(byte[] bytes) throws Exception {
		Hex hex = new Hex();
		try {
			bytes = hex.decode(bytes);
			hex = null;
		} catch (DecoderException e) {

		}
		return bytes;
	}

	/**
	 * encode bcd to Asc
	 * 
	 * @param bytes
	 * @return
	 */
	public static byte[] bcd2AscByte(byte[] bytes) {
		Hex hex = new Hex();
		bytes = hex.encode(bytes);
		hex = null;
		return bytes;
	}

	// ---------------------------------------------------------------------

	/**
	 * Converte uma string preenchida com dgitos hexadecimais em um array de
	 * bytes. Cada byte do array corresponder a dois dgitos da String, desta
	 * forma, a String dever conter um nmero par de dgitos. Exemplo: a String
	 * "FFA3" ser convertida no array de bytes { 0xFF, 0xA3 }.
	 * 
	 * @param hexa
	 *            a string com os dgitos hexadecimais.
	 * 
	 * @return O array de bytes correspondente aos dgitos na string.
	 *
	 */

	public static byte[] hexToByteArray(String hexa) {
		if (hexa == null) {
			return null;
		}
		if ((hexa.length() % 2) != 0) {
			return null;
		}
		int tamArray = hexa.length() / 2;
		byte[] retorno = new byte[tamArray];
		for (int i = 0; i < tamArray; i++) {
			retorno[i] = hexToByte(hexa.substring(i * 2, i * 2 + 2));
		}
		return retorno;
	}

	/**
	 * Converte uma string preenchida com dgitos hexadecimais em um array de
	 * bytes. Cada byte do array corresponder a dois dgitos da String, desta
	 * forma, a String dever conter um nmero par de dgitos. Exemplo: a String
	 * "FFA3" ser convertida no array de bytes { 0xFF, 0xA3 }.
	 * 
	 * @param hexa
	 *            a string com os dgitos hexadecimais.
	 * 
	 * @return O array de bytes correspondente aos dgitos na string.

	 */

	public static byte hexToByte(String hexa) {
		if (hexa == null) {
			return 0;
		}
		if (hexa.length() != 2) {
			return 0;
		}
		byte[] b = hexa.getBytes();
		byte valor = (byte) (hexDigitValue((char) b[0]) * 16 + hexDigitValue((char) b[1]));
		return valor;
	}

	/**
	 * Retorna o valor decimal de um dgito hexadecimal.
	 * 
	 * @param c
	 *            o dgito hexadecimal ('0'-'9', 'a'-'f' ou 'A'-'F').
	 * 
	 * @return o valor do dgito hexadecimal.
	 *
	 */
	private static int hexDigitValue(char c) {
		int retorno = 0;
		if (c >= '0' && c <= '9') {
			retorno = (int) (((byte) c) - 48);
		} else if (c >= 'A' && c <= 'F') {
			retorno = (int) (((byte) c) - 55);
		} else if (c >= 'a' && c <= 'f') {
			retorno = (int) (((byte) c) - 87);
		} else {
			return 0;
		}
		return retorno;
	}

	public static String show(byte[] b) {
		char[] ch = { '0', '1', '2', '3', '4', '5', '6', '7', '8', '9', 'A',
				'B', 'C', 'D', 'E', 'F' };

		java.io.StringWriter stream = new java.io.StringWriter();

		int len = b.length;
		for (int i = 0; i < len; i++) {
			byte c = (byte) ((b[i] & 0xf0) >> 4);
			stream.write(ch[c]);
			c = (byte) (b[i] & 0x0f);
			stream.write(ch[c]);
		}
		return stream.toString();
	}

	/**
	 * @Description: 将byte数组转为String
	 * @param: @param bytes
	 * @param: @return
	 * @return: String
	 * @throws
	 */
	public static String byteArrayToHex(byte[] bytes) {
		String retorno = "";
		if (bytes == null || bytes.length == 0) {
			return retorno;
		}
		for (int i = 0; i < bytes.length; i++) {
			byte valor = bytes[i];
			int d1 = valor & 0xF;
			d1 += (d1 < 10) ? 48 : 55;
			int d2 = (valor & 0xF0) >> 4;
			d2 += (d2 < 10) ? 48 : 55;
			retorno = retorno + (char) d2 + (char) d1;
		}
		return retorno;
	}

	/**
	 * @函数功能: BCD码串转化为字符串
	 * @输入参数: BCD码
	 * @输出结果: 10进制串
	 */
	public static String bcd2Str(byte[] bytes) {
		char temp[] = new char[bytes.length * 2], val;

		for (int i = 0; i < bytes.length; i++) {
			val = (char) (((bytes[i] & 0xf0) >> 4) & 0x0f);
			temp[i * 2] = (char) (val > 9 ? val + 'A' - 10 : val + '0');

			val = (char) (bytes[i] & 0x0f);
			temp[i * 2 + 1] = (char) (val > 9 ? val + 'A' - 10 : val + '0');
		}
		return new String(temp);
	}

	/**
	 * 左补零
	 * 
	 * @param source 源字符串
	 * @param  width 补位长度
	 * 
	 * @return 补位后字符串
	 * */
	public static String AddLetfZero(String source, int width) {
		if (source.length() >= width) {
			return source;
		}
		char[] zero = new char[width - source.length()];
		for (int i = 0; i < zero.length; ++i) {
			zero[i] = '0';
		}
		String stemp = new String(zero) + source;
		return stemp;
	}

	/**
	 * 右补空格
	 * 
	 * @param  source 源字符串
	 * @param  width 补位长度
	 * 
	 * @return 补位后字符串
	 * */
	public static String AddRightBlank(String source, int width) {
		if (source.length() == width)
			return source;

		if (source.length() > width)
			return source.substring(source.length() - width);

		char[] blank = new char[width - source.length()];
		for (int i = 0; i < blank.length; i++) {
			blank[i] = (char) 0x20;
		}
		String stemp = source + new String(blank);
		return stemp;
	}

	/**
	 * str to hex
	 * 
	 * @param str
	 * @return
	 * @throws UnsupportedEncodingException
	 */
	public static String str2HexStr(String str) {
		StringBuilder sb = new StringBuilder();
		byte[] bs = str.getBytes();

		for (int i = 0; i < bs.length; i++) {
			sb.append(mChars[(bs[i] & 0xFF) >> 4]);
			sb.append(mChars[bs[i] & 0x0F]);
		}
		return sb.toString().trim();
	}

	/**
	 * byte to hex
	 * 
	 * @param bs
	 * @return
	 * @throws UnsupportedEncodingException
	 */
	public static String str2HexStr(byte[] bs) {
		StringBuilder sb = new StringBuilder();

		for (int i = 0; i < bs.length; i++) {
			sb.append(mChars[(bs[i] & 0xFF) >> 4]);
			sb.append(mChars[bs[i] & 0x0F]);
		}
		return sb.toString().trim();
	}

	public static String HEX2STR(String args) {

		try {
			args = new String(Hex.decodeHex(args.toCharArray()));
		} catch (DecoderException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		return args;

	}
}
