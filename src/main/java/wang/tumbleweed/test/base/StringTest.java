package wang.tumbleweed.test.base;

import net.sf.json.JSONObject;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.junit.Test;
import wang.tumbleweed.common.MD5;

import java.io.UnsupportedEncodingException;
import java.net.URLEncoder;
import java.security.NoSuchAlgorithmException;
import java.text.SimpleDateFormat;
import java.util.*;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

/**
 * Created by mylover on 10/30/15.
 */
public class StringTest {

    public Logger log = LogManager.getLogger(StringTest.class);

    @Test
    public void test4() {
        for (long i = 800000001; i < 800000301;i++){
            System.out.println("<mobile>"+i+"</mobile>");
        }
    }

    @Test
    public void test5() {
        String s1 = "s1";
        List<String> list1 = new ArrayList<String>();
        list1.add(s1);

        List<String> list2 = new ArrayList<String>();
        list2.add(list1.get(0));

        String supdate = list2.get(0);
        supdate = supdate + "update";
        log.info(supdate);

        log.info(list1.get(0));

    }

    @Test
    public void testNum() {
        List<String> list = new ArrayList<String>();
        list.add("1");
        list.add("2");
        list.add("3");

        List<String> list1 = new ArrayList<String>();
        list1.add("1");
        list1.add("3");

        if (list.contains(list1.get(0))) {
            log.info("true");
            list.remove(list1.get(0));
        }
        for (String string : list) {
            log.info(string);
        }
    }

    @Test
    public void testString() {

        String uri = null;

        int index = uri.lastIndexOf("/");

        String lastUrl = uri.substring(uri.lastIndexOf("/") + 1);

        log.info(index);

    }

    @Test
    public void appendPrifix() {

        String uri = "1234567890";

//        uri = uri.substring(1);
        uri = uri.substring(7,9);


        log.info(uri);

    }


    /**
     * 去掉换行符
     *
     * @param str
     * @return
     */
    public static String replaceBlank(String str) {
        String dest = "";
        if (str!=null) {
            Pattern p = Pattern.compile("\t|\r|\n");
            Matcher m = p.matcher(str);
            dest = m.replaceAll("");
        }
        return dest;
    }

    /**
     * 去掉换行符
     *
     * @param str
     * @return
     */
    public static String replaceBlank2(String str) {
        String dest = "";
        if (str != null) {
            dest = str.replace("\\n", "");
            dest = dest.replace("\\t", "");
            dest = dest.replace("\\r", "");
        }
        return dest;
    }


    @Test
    public void getString() {

        StringBuffer groupIdStr = new StringBuffer();

        List<String> groupIds = new ArrayList<String>();

        groupIds.add("1");
        groupIds.add("2");

        for (String groupId : groupIds) {
            groupIdStr.append("'").append(groupId).append("',");
        }

        groupIdStr.deleteCharAt(groupIdStr.length() - 1);

        log.info(groupIdStr.toString());

    }

    @Test
    public void getTime() {
        long time = Long.valueOf("1356486746000"); //long类型的字符串转换
        SimpleDateFormat sdf= new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
        log.info(sdf.format(new Date(time)));
    }


    @Test
    public void getString2() {
        String to = "123456  ,   ";
        String[] tos = to.split(",");

        for (String number : tos) {
            log.info(number.toString());
        }
    }

    @Test
    public void getFileNameSuffix() {
        String fileName = "1.2.3";
        int pos = fileName.lastIndexOf(".");
        log.info(fileName.substring(pos));
    }

    @Test
    public void encode() throws UnsupportedEncodingException {
        String ccpUserData = null;
        JSONObject json= new JSONObject();

        log.info(json.toString());

        ccpUserData = URLEncoder.encode(json.toString(), "utf-8");

        log.info(ccpUserData);

    }

    @Test
    public void trimTest() {
        String fileName = "     1 2 3     ";
        log.info(StringTest.replaceSpace(fileName));
    }

    /**
     * 去掉 空格字符
     *
     * @return
     */
    public static String replaceSpace(String value) {
        if (value != null) {
            value = value.replace(" ", "");
            value = value.trim();
        }
        return value;
    }

    @Test
    public void makeSig() throws NoSuchAlgorithmException {
        String accountId = "ff808181478fb4640147901ee3290003";
        String token = "970a7bfc32fe45c5b54f1f4650b2ca9a";

        String curr_date = new SimpleDateFormat("yyyyMMddHHmmss").format(new Date());
        String sig = accountId + token + curr_date;
        log.info("sig: " + MD5.md5(sig));

        //A671F8A4598FF695DD1C089DD0156D49
    }


    @Test
    public void test1 () {
        String friendName = "12345678910";
        log.info(friendName.indexOf("#"));
        String s1 = friendName.substring(0, friendName.indexOf("#"));
        String s2 = friendName.substring(friendName.indexOf("#") + 1);

        log.info(s1);
        log.info(s2);
    }

    /**
     * 转换list to String
     * @param lists
     * @return
     */
    private String changeListToString(Collection<String> lists) {

        StringBuffer values = new StringBuffer();

        for (String groupId : lists) {
            values.append("'").append(groupId).append("',");
        }

        values.deleteCharAt(values.length() - 1);

        return values.toString();
    }

    @Test
    public void test() {
        String filename = "test.zip";

        if (!filename.endsWith(".zip")) {
            log.info("true");
        }

        if (!filename.endsWith(".wav") && !filename.endsWith(".zip")) {
            log.info(filename);
        }
    }

    @Test
    public void test2() {
        String filename = "'dsafsd'";
        filename = filename.replaceAll("'","---");
        log.info("filename {}",filename);
    }

    @Test
    public void test3() {
        String fileName = "123.jpg";
        String suffix = "jpg";
        if (fileName != null && fileName.trim().length() > 0) {
            char dot = '.';
            int beginIndex = fileName.lastIndexOf(dot) + 1;
            suffix = fileName.substring(beginIndex);
        }
        log.info(suffix);
    }

}
