package com.tumbleweed.test.base.xyHsmAPI;

import java.io.*;
import java.net.*;
import java.lang.*;
import java.net.SocketException;

import org.bouncycastle.util.encoders.Hex;

public class SocketIO {
    private Socket h;
    private InetAddress hsmip;
    private BufferedReader is;
    private PrintWriter os;

    public boolean ok = false;
    public String conerrmsg = null;

    public SocketIO() {

    }

    public boolean connectHSM(String ip, int port) throws Exception {
        try {
            h = new Socket(ip, port);
            h.setSoLinger(true, 0);

            is = new BufferedReader(new InputStreamReader(h.getInputStream(), "ISO-8859-1"));
            os = new PrintWriter(new OutputStreamWriter(h.getOutputStream(), "ISO-8859-1"));
            ok = true;
        } catch (SocketException e) {
            String tempstr = null;
            ok = false;
            conerrmsg = "Possible Reason：" + e.getMessage();
            throw e;
        } finally {
            if (!ok) {
                allClose();
                return false;
            }


        }
        return true;
    }

    public String HSMCmd(String in) throws Exception {
        String outstr = null;

        SendToHSM(in);
        try {
            outstr = new String(ReceFromHSM().getBytes("ISO-8859-1"), "ISO-8859-1");
            // System.out.println("3333  "+new String( Hex.encode(outstr.getBytes("ISO-8859-1")),"ISO-8859-1").toUpperCase() );
        } catch (SocketException e) {
            throw e;
        } catch (Exception e) {
            throw new SocketException("通讯失败!");
        }
        return outstr;
    }

    private void SendToHSM(String str) {
// System.out.println(str);
        os.write(str);
        os.flush();
    }

    private String ReceFromHSM() throws Exception {
        String out = "";
        String tmpstr = "";
        int i = 0;

        try {
            char[] out1 = new char[2048];
            i = is.read(out1); //read(out1,0,1000) ;
            //byte[] tmpb = new byte[ i ];
            //System.out.println("len->>>:"+i);
            for (int ii = 2; ii < i; ii++) {
                tmpstr = Integer.toHexString(out1[ii]);
                if (tmpstr.length() == 1)
                    tmpstr = "0" + tmpstr;

                out += tmpstr;
            }
            //System.out.println("2222  "+out.toUpperCase());
        } catch (Exception e) {
            throw new SocketException("接收数据失败!");

        }
        return new String(Hex.decode(out.getBytes()), "ISO-8859-1");

    }

    public void allClose() {
        try {
            is.close();
            os.close();
            h.close();
        } catch (Exception e) {
            ;
        }
    }

}
//SocketException
/*
 public HsmSocketException extends Exception
 {
		public HsmSocketException()
		{
			super();
		}
 		
		public void printStacklTrace()
		{
			printStackTrace(System.err);
		}

		public void printStackTrace(PrintStream outSrm)
		{
			printStackTrace(new PrintStream(outSrm));
		}
		
		public void printStackTrace(PrintWriter writer)
		{
			super.printStackTrace(writer);
			if(this.getRootCause()!=null)
			{
					this.getRootCause().printStackTrace(writer);
			}
			
			writer.flush();

		}
 		
 }
*/