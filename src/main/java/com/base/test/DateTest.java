package com.base.test;

import org.apache.commons.lang3.time.DateFormatUtils;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.junit.Test;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.Date;

/**
 * Created by mylover on 10/30/15.
 */
public class DateTest {

    public Logger log = LogManager.getLogger(DateTest.class);

    @Test
    public void test11() {
        Calendar curr = Calendar.getInstance();
        curr.set(Calendar.YEAR,curr.get(Calendar.YEAR)-1);
        Date date=curr.getTime();
        SimpleDateFormat dateformat=new SimpleDateFormat("yyyyMMdd");
        String lastToday = dateformat.format(date);
        int date3 = Integer.parseInt(lastToday);
        log.info(date3);
    }

    @Test
    public void testTime() {
        Calendar c = Calendar.getInstance();
        c.set(Calendar.HOUR_OF_DAY, 0);
        c.set(Calendar.MINUTE, 0);
        c.set(Calendar.SECOND, 0);

        long t = c.getTimeInMillis();
        log.info(DateFormatUtils.format(c, "yyyyMMddHHmmss"));

        Calendar c1 = Calendar.getInstance();
        c1.set(Calendar.HOUR_OF_DAY, 1);
        c1.set(Calendar.MINUTE, 0);
        c1.set(Calendar.SECOND, 0);

        long t1 = c1.getTimeInMillis();
        log.info(DateFormatUtils.format(c1, "yyyyMMddHHmmss"));

        log.info((t1 - t) / 1000);

        long t2 = System.currentTimeMillis();

        log.info((((t2 - t)/1000)/60)/60);

    }

    @Test
    public void testSharding() throws ParseException {

        Calendar c = Calendar.getInstance();
        c.set(Calendar.HOUR_OF_DAY, 1);
        c.set(Calendar.MINUTE, 0);
        c.set(Calendar.SECOND, 0);
        long t = c.getTimeInMillis();
        long now = System.currentTimeMillis();
        if ((now - t) > 0) {
            log.info(now -t);
            log.info("true");
        }
        SimpleDateFormat dfParse = new SimpleDateFormat("yyyy-MM-dd");

        log.info(dfParse.format(new Date()));

    }



    @Test
    public void test1() {
        log.info(new Date().toString());
    }

    @Test
    public void test2() {
        log.info(String.valueOf(System.currentTimeMillis()).length());
    }

    @Test
    public void test3() {
        log.info(beforeNHourToNowDate(-1));
    }

    @Test
    public void test4() throws ParseException {

        log.info(getShardTime());
    }

    @Test
    public void test5() {
        log.info("date:" + new java.sql.Date(System.currentTimeMillis()));
    }

    /**
     * 获取前N小时的时间
     * （作者：zhangding<zhangding@enn.com>）
     * @param hours
     * @return
     */
    public static String beforeNHourToNowDate(int hours) {
        Calendar calendar = Calendar.getInstance();
		/* HOUR_OF_DAY 指示一天中的小时 */
        calendar.set(Calendar.HOUR_OF_DAY, calendar.get(Calendar.HOUR_OF_DAY)
                - hours);
        SimpleDateFormat df = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
        return df.format(calendar.getTime());
    }


    /**
     * 获取当前时间
     * @return
     */
    public static String getCreateTime() {
        Calendar calendar = Calendar.getInstance();
        SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
        Date date = calendar.getTime();
        String timeCreate  = sdf.format(date);
        return timeCreate;
    }




    public static java.sql.Date getShardTime() throws ParseException {
        SimpleDateFormat df = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
        Date parsed = df.parse(getCreateTime());
        return new java.sql.Date(parsed.getTime());
    }

}
