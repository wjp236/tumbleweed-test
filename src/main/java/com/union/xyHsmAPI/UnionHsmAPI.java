package com.union.xyHsmAPI;

import java.io.*;
import java.net.SocketException;

import org.bouncycastle.crypto.engines.DESEngine;
import org.bouncycastle.crypto.params.KeyParameter;
import org.bouncycastle.crypto.BufferedBlockCipher;
import org.bouncycastle.crypto.CryptoException;
import org.bouncycastle.util.encoders.Hex;
import org.bouncycastle.crypto.Digest;
import org.bouncycastle.crypto.digests.*;
import org.bouncycastle.crypto.digests.MD5Digest;

import com.union.xyHsmAPI.SocketIO;
import com.union.xyHsmAPI.HSMException;

public class UnionHsmAPI {


    static String Path = null;
    private SocketIO hsm = new SocketIO();

    public String hsmip;
    public int hsmport;

    /*
                            byte[] DataByte;
								byte[] DigMsg = new byte[ 20 ];
								DigMsg = SHA1Algo( DataByte );
                in = new byte[ len ];
                System.arraycopy( DigMsg, 0, in, 0, DigMsg.length );
   */

    public UnionHsmAPI(String ip, int port) {
        hsmip = ip;
        hsmport = port;
    }


    public void UnionConnect() throws Exception {
        try {
            if (!hsm.connectHSM(hsmip, hsmport)) {
                throw new Exception("建立连接失败");
            }
        } catch (Exception e) {
            throw new Exception("建立连接失败");
        }
    }

    public void UnionDisconnect() throws Exception {

        try {
            hsm.allClose();
        } catch (Exception e) {
            throw new Exception("关闭连接失败");
        }

    }

    public String UnionGenZAK(String zmk, int zaklen) throws Exception {
        String dPinByPik = null, buf = null;
        String tmp, outstr;
        int len, alllen;
        byte[] b = {0};
        Integer h1, h2;
        byte head[] = {0, 0};
        int zaklenflag = 0;
        int zmklenflag = 0;

        if ((zmk.length() != 16) && (zmk.length() != 32) && (zmk.length() != 48)) {
            throw new Exception("ZMK参数长度错误.");
        }
        if ((zaklen != 16) && (zaklen != 32) && (zaklen != 48)) {
            throw new Exception("ZAKLEN参数长度错误.");
        }
        if (zmk.length() != 16)
            zmklenflag = 1;
        if (zaklen != 16)
            zaklenflag = 1;

        alllen = 2 + 8 + 2 + 1 + zmklenflag + zmk.length() + 3;


        h1 = new Integer((alllen - 2) / 256);
        h2 = new Integer((alllen - 2) % 256);
        head[0] = h1.byteValue();
        head[1] = h2.byteValue();
        buf = new String(head, "ISO-8859-1");
        buf += "00000000FI1";

        if (zmk.length() == 16)
            buf += zmk;
        else if (zmk.length() == 32) {
            buf += "X";
            buf += zmk;
        } else if (zmk.length() == 48) {
            buf += "Y";
            buf += zmk;
        }

        if (zaklen == 16)
            buf += "ZZ0";
        else if (zaklen == 32) {
            buf += "XX0";
        } else if (zaklen == 48) {
            buf += "YY0";
        }


         System.out.println(buf);
        try {
            outstr = hsm.HSMCmd(buf);
            System.out.println(outstr);
        } catch (SocketException e) {
            throw e;
        }


        // 判断返回长度
        if (outstr.length() < 8 + 2 + 2)
            throw new SocketException("接收数据失败.");

        if (outstr.substring(8, 12).equals("FJ00")) //成功 12345678FJ00
            return new String(outstr.substring(12, outstr.length()));//keybyzmk+keybylmk+checkvalue
        else
            throw new Exception("返回错误码：" + outstr.substring(8, 12));

        //return new String("");
    }

    public String UnionResetZAK(String zmk, String zpk) throws Exception {
        String dPinByPik = null, buf = null;
        String tmp, outstr;
        int len, alllen;
        byte[] b = {0};
        Integer h1, h2;
        byte head[] = {0, 0};
        int zmklenflag = 0;
        int zpklenflag = 0;


        if ((zmk.length() != 16) && (zmk.length() != 32) && (zmk.length() != 48)) {
            throw new Exception("ZMK参数长度错误.");
        }
        if ((zpk.length() != 16) && (zpk.length() != 32) && (zpk.length() != 48)) {
            throw new Exception("ZAK参数长度错误.");
        }
		/*if( (macdatalen<0)||(macdatalen>800))
		{
  		throw new Exception( "MAC数据长度错误." );
    	}*/
        if (zmk.length() != 16)
            zmklenflag = 1;
        if (zpk.length() != 16)
            zpklenflag = 1;

        alllen = 2 + 8 + 2 + 1 + zmklenflag + zmk.length() + zpklenflag + zpk.length() + 1 + 3;

//System.out.println(Integer.toString(alllen-2,16));

        h1 = new Integer((alllen - 2) / 256);
        h2 = new Integer((alllen - 2) % 256);
        head[0] = h1.byteValue();
        head[1] = h2.byteValue();
        buf = new String(head, "ISO-8859-1");
        //buf += "00000000FK";

        String zmkxy = null;

        if (zmk.length() == 16) {
            buf += "00000000FK1";
            zmkxy = "Z";
        } else if (zmk.length() == 32) {
            buf += "00000000FK1X";
            zmkxy = "X";
        } else if (zmk.length() == 48) {
            buf += "00000000FK1Y";
            zmkxy = "Y";
        }

        buf += zmk;

        if (zpk.length() == 32) {
            buf += "X";
        } else if (zpk.length() == 48) {
            buf += "Y";
        }

        buf += zpk;
        buf += ";";
        buf += zmkxy;
        buf += zmkxy;
        buf += "0";

        String zakchk = null;
        String zakbylmk = null;

//System.out.println("zaktolmk  "+buf.substring(2));

        outstr = null;
        try {
            outstr = hsm.HSMCmd(buf);
            //hsm.allClose() ;
        } catch (SocketException e) {
            throw e;
        }

        // 判断返回长度
        if (outstr.length() < 8 + 2 + 2)
            throw new SocketException("接收数据失败.");

        if (outstr.substring(8, 12).equals("FL00")) //成功 12345678FJ00
        {//	return new String(outstr.substring(12,outstr.length()));//keybyzmk+keybylmk+checkvalue
            //System.out.println(outstr);
            if (zpk.length() == 16) {
                zakbylmk = new String(outstr.substring(12, 12 + 16));
                //System.out.println(zpkbylmk);
                zakchk = new String(outstr.substring(12 + 16, 12 + 16 + 16));
                //System.out.println("chk  :"+zpkchk);
            } else if (zpk.length() == 32) {
                zakbylmk = new String(outstr.substring(13, 13 + 32));
                zakchk = new String(outstr.substring(13 + 32, 13 + 32 + 16));
            } else if (zpk.length() == 48) {
                zakbylmk = new String(outstr.substring(13, 13 + 48));
                zakchk = new String(outstr.substring(13 + 48, 13 + 48 + 16));
            }
        } else
            throw new Exception("返回错误码：" + outstr.substring(8, 12));

        //String mac=null;

	/*try
	{
//System.out.println(zpkbylmk+" "+macdatalen+" "+macdata);
  	mac = UnionGenMAC(zakbylmk,macdatalen,macdata);
  	//mac = UnionGenMAC("B334D2BCA6E79459",15,"988776665555454".getBytes());
	}
	catch(SocketException e)
 	{throw e;}
 	catch(Exception e)
  {throw e;}*/

        //mac+chk+keybylmk
        return (zakbylmk);


    }

    public String UnionGenMAC(String mak, int macdatalen, byte[] macdata) throws Exception {
        ByteArrayOutputStream bout = new ByteArrayOutputStream();

        String dPinByPik = null, buf = null;
        String tmp = null, outstr;
        int len, alllen;

        byte[] b = {0};
        Integer h1, h2;
        byte head[] = {0, 0};
        byte[] DigMsg = new byte[20];


        if ((mak.length() != 16) && (mak.length() != 32) && (mak.length() != 48)) {
            throw new Exception("MAK参数长度错误.");
        }
        if ((macdatalen < 0) || (macdatalen > 800)) {
            throw new Exception("MAC数据长度错误.超800字节");
        }

        len = 0;
        if (mak.length() != 16)
            len = 1;
        alllen = 2 + 8 + 2 + 4 + len + mak.length() + 4 + macdatalen;
//System.out.println(Integer.toString(alllen-2,16));
        h1 = new Integer((alllen - 2) / 256);
        h2 = new Integer((alllen - 2) % 256);
        head[0] = h1.byteValue();
        head[1] = h2.byteValue();

        bout.write(head);

        String zakxy = null;
        int flag;
        if (mak.length() == 16) {
            tmp = "00000000MS0100";
            zakxy = "Z";
        } else if (mak.length() == 32) {
            tmp = "00000000MS0110X";
            zakxy = "X";
            flag = 1;
        }

        bout.write(tmp.getBytes());

        bout.write(mak.getBytes());

        String hexstr = Integer.toString(macdatalen, 16).toUpperCase();
        if (hexstr.length() == 1)
            bout.write(("000" + hexstr).getBytes());
        if (hexstr.length() == 2)
            bout.write(("00" + hexstr).getBytes());
        if (hexstr.length() == 3)
            bout.write(("0" + hexstr).getBytes());
        if (hexstr.length() == 4)
            bout.write((hexstr).getBytes());

        bout.write(macdata);

//System.out.println(new String(bout.toByteArray()).substring(2));

        outstr = null;
        try {
            outstr = hsm.HSMCmd(new String(bout.toByteArray()));
            //hsm.allClose() ;
        } catch (SocketException e) {
            throw e;
        }

        // 判断返回长度
        if (outstr.length() < 8 + 2 + 2)
            throw new SocketException("接收数据失败.");

        if (outstr.substring(8, 12).equals("MT00")) //成功 12345678FJ00
            return new String(outstr.substring(12, outstr.length()));//keybyzmk+keybylmk+checkvalue
        else
            throw new Exception("返回错误码：" + outstr.substring(8, 12));


        //return new String("");
    }

    public static void main(String[] args) {

        UnionHsmAPI tt = new UnionHsmAPI("172.16.254.227", 1818);
        //String myStr;
        // String aaa="313233";

        //byte []rs = new byte[20];
        try {


            tt.UnionConnect();

            String myzak = tt.UnionResetZAK("DA05B7A979CBD9A1","56CC09E7CFDC4CEF")  ;
            System.out.println(myzak);

            tt.UnionDisconnect();

        } catch (HSMException e) {
            e.printStackTrace();
        } catch (Exception e) {
            e.printStackTrace();
        }


    }

}

