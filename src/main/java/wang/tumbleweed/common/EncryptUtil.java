package wang.tumbleweed.common;

import sun.misc.BASE64Decoder;
import sun.misc.BASE64Encoder;

import java.io.UnsupportedEncodingException;
import java.security.MessageDigest;
import java.security.NoSuchAlgorithmException;

/**
 * <p>Title: EncryptUtil</p>
 * <p>Description: 加解密工具类</p>
 * <p>Copyright: Copyright (c) 2010</p>
 * <p>Company: hisunsray</p>
 * <p>Date:2012-08-20</p>
 * @author tanglujun
 * @version 1.0
 */
public class EncryptUtil {  

    private static final String UTF8 = "utf-8";

    /**
     * MD5数字签名
     *
     * @param src
     * @return
     * @throws Exception
     */
    public static String md5(String src) {
        // 定义数字签名方法, 可用：MD5, SHA-1
        try {
            MessageDigest md = MessageDigest.getInstance("MD5");
            byte[] b = md.digest(src.getBytes(UTF8));
            return byte2HexStr(b);
        } catch (NoSuchAlgorithmException e) {
            e.printStackTrace();
        } catch (UnsupportedEncodingException e) {
            e.printStackTrace();
        }
        return null;
    }

//    /**
//     * 字节数组转化为大写16进制字符串
//     *
//     * @param b
//     * @return
//     */
//    private static String byte2HexStr(byte[] b) {
//        StringBuilder sb = new StringBuilder();
//        for (int i = 0; i < b.length; i++) {
//            String s = Integer.toHexString(b[i] & 0xFF);
//            if (s.length() == 1) {
//                sb.append("0");
//            }
//            sb.append(s.toUpperCase());
//        }
//        return sb.toString();
//    }

    /** 
     * MD5数字签名 
     *  
     * @param src 
     * @return 
     * @throws Exception
     */  
    public String md5Digest(String src) throws Exception {
       // 定义数字签名方法, 可用：MD5, SHA-1  
       MessageDigest md = MessageDigest.getInstance("MD5");
       byte[] b = md.digest(src.getBytes(UTF8));  
       return this.byte2HexStr(b);  
    }  
      
    /** 
     * BASE64编码
     *  
     * @param src 
     * @return 
     * @throws Exception
     */  
    public static String base64Encoder(String src) throws Exception {
        BASE64Encoder encoder = new BASE64Encoder();
        return encoder.encode(src.getBytes(UTF8));  
    }  
      
    /** 
     * BASE64解码
     *  
     * @param dest 
     * @return 
     * @throws Exception
     */  
    public static String base64Decoder(String dest) throws Exception {
        BASE64Decoder decoder = new BASE64Decoder();
        return new String(decoder.decodeBuffer(dest), UTF8);
    }  
      
    /** 
     * 字节数组转化为大写16进制字符串 
     *  
     * @param b 
     * @return 
     */  
    private static String byte2HexStr(byte[] b) {
        StringBuilder sb = new StringBuilder();
        for (int i = 0; i < b.length; i++) {  
            String s = Integer.toHexString(b[i] & 0xFF);
            if (s.length() == 1) {  
                sb.append("0");  
            }  
            sb.append(s.toUpperCase());  
        }  
        return sb.toString();  
    }  
}  
