package com.tumbleweed.test.base.xyHsmAPI;


public class Test {

    public static void main(String[] args) {

        UnionHsmAPI tt = new UnionHsmAPI("172.16.254.227", 1818);

        String myStr;

        byte[] rs = new byte[100];
        try {

            //最终用户使用方法：
            tt.UnionConnect();
            String zak = tt.UnionGenZAK("1212EB806FDC387E765CA61B54C72CD8", 32);
            System.out.println(zak);

//            String myzak = tt.UnionResetZAK("03F79C8C1FEB888D", "DB65FD3DB6FC0968");
//            System.out.println(myzak);
//
//            String mac = tt.UnionGenMAC("9DD537C3AD382337", 8, "我爱我家123456".getBytes());
//            System.out.println(mac);

            tt.UnionDisconnect();
//
        } catch (Exception e) {
            //System.out.println(e.getErrorCode());
            e.printStackTrace();
        }

    }


}