package com.base.util;

import org.apache.commons.codec.digest.DigestUtils;
import sun.misc.BASE64Decoder;
import sun.misc.BASE64Encoder;

import javax.crypto.Cipher;
import javax.crypto.KeyGenerator;
import javax.crypto.SecretKey;
import javax.crypto.SecretKeyFactory;
import javax.crypto.spec.DESedeKeySpec;
import java.security.Key;
import java.security.NoSuchAlgorithmException;

/**
 * 描述:3des
 *
 * @author: mylover
 * @Time: 12/04/2017.
 */
public class DESUtils {

    private final static BASE64Encoder base64encoder = new BASE64Encoder();
    private final static BASE64Decoder base64decoder = new BASE64Decoder();
    private final static String encoding = "UTF-8";

    public static void main(String[] args) throws Exception {
        byte[] key = base64decoder.decodeBuffer("1212EB806FDC387E765CA61B54C72CD8");
        byte[] data = base64decoder.decodeBuffer("0FC564967215DC32C98502981BC479C5");

        System.out.println("解密");
        byte[] result = des3DecodeECB(key, data);
        System.out.println(new String(result, "UTF-8"));
        System.out.println("-----------------------------");
    }

    public static byte[] hex(String key){
        String f = DigestUtils.md5Hex(key);
        byte[] bkeys = new String(f).getBytes();
        byte[] enk = new byte[24];
        for (int i=0;i<24;i++){
            enk[i] = bkeys[i];
        }
        return enk;
    }

    /**
     * 随机生成密钥
     * @return
     * @throws NoSuchAlgorithmException
     */
    public static byte[] createKey() throws NoSuchAlgorithmException {
        KeyGenerator keyGen = KeyGenerator.getInstance("DESede");//密钥生成器
        keyGen.init(168); //可指定密钥长度为112或168，默认为168
        SecretKey secretKey = keyGen.generateKey();//生成密钥
        byte[] key = secretKey.getEncoded();//密钥字节数组
        return key;
    }

    /**
     * ECB加密,不要IV
     * @param key 密钥
     * @param data 明文
     * @return Base64编码的密文
     * @throws Exception
     */
    public static byte[] des3EncodeECB(byte[] key, byte[] data)
            throws Exception {
//        SecretKey secretKey = new SecretKeySpec(key, "DESede");//恢复密钥
//        Cipher cipher = Cipher.getInstance("DESede");//Cipher完成加密或解密工作类
//        cipher.init(Cipher.ENCRYPT_MODE, secretKey);//对Cipher初始化，解密模式
//        byte[] cipherByte = cipher.doFinal(data);//加密data

        Key deskey = null;
        DESedeKeySpec spec = new DESedeKeySpec(key);
        SecretKeyFactory keyfactory = SecretKeyFactory.getInstance("desede");
        deskey = keyfactory.generateSecret(spec);
        Cipher cipher = Cipher.getInstance("Desede" + "/ECB/NoPadding");
        cipher.init(Cipher.ENCRYPT_MODE, deskey);
        byte[] cipherByte = cipher.doFinal(data);
        return cipherByte;
    }
    /**
     * ECB解密,不要IV
     * @param key 密钥
     * @param data Base64编码的密文
     * @return 明文
     * @throws Exception
     */
    public static byte[] des3DecodeECB(byte[] key, byte[] data)
            throws Exception {
//        SecretKey secretKey = new SecretKeySpec(key, "DESede");//恢复密钥
//        Cipher cipher = Cipher.getInstance("DESede/ECB/NoPadding");//Cipher完成加密或解密工作类
//        cipher.init(Cipher.DECRYPT_MODE, secretKey);//对Cipher初始化，解密模式
//        byte[] cipherByte = cipher.doFinal(data);//解密data

        Key deskey = null;
        DESedeKeySpec spec = new DESedeKeySpec(key);
        SecretKeyFactory keyfactory = SecretKeyFactory.getInstance("desede");
        deskey = keyfactory.generateSecret(spec);
        Cipher cipher = Cipher.getInstance("Desede" + "/ECB/NoPadding");
        cipher.init(Cipher.DECRYPT_MODE, deskey);
        byte[] cipherByte = cipher.doFinal(data);
        return cipherByte;
    }



//    /**
//     * CBC加密
//     * @param key 密钥
//     * @param keyiv IV
//     * @param data 明文
//     * @return Base64编码的密文
//     * @throws Exception
//     */
//    public static byte[] des3EncodeCBC(byte[] key, byte[] keyiv, byte[] data)
//            throws Exception {
//        Key deskey = null;
//        DESedeKeySpec spec = new DESedeKeySpec(key);
//        SecretKeyFactory keyfactory = SecretKeyFactory.getInstance("desede");
//        deskey = keyfactory.generateSecret(spec);
//        Cipher cipher = Cipher.getInstance("desede" + "/CBC/PKCS5Padding");
//        IvParameterSpec ips = new IvParameterSpec(keyiv);
//        cipher.init(Cipher.ENCRYPT_MODE, deskey, ips);
//        byte[] bOut = cipher.doFinal(data);
//        return bOut;
//    }
//    /**
//     * CBC解密
//     * @param key 密钥
//     * @param keyiv IV
//     * @param data Base64编码的密文
//     * @return 明文
//     * @throws Exception
//     */
//    public static byte[] des3DecodeCBC(byte[] key, byte[] keyiv, byte[] data)
//            throws Exception {
//        Key deskey = null;
//        DESedeKeySpec spec = new DESedeKeySpec(key);
//        SecretKeyFactory keyfactory = SecretKeyFactory.getInstance("desede");
//        deskey = keyfactory.generateSecret(spec);
//        Cipher cipher = Cipher.getInstance("desede" + "/CBC/PKCS5Padding");
//        IvParameterSpec ips = new IvParameterSpec(keyiv);
//        cipher.init(Cipher.DECRYPT_MODE, deskey, ips);
//        byte[] bOut = cipher.doFinal(data);
//        return bOut;
//    }

}
