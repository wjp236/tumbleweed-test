package com.base.test;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.junit.Test;

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
        SimpleDateFormat df = new SimpleDateFormat("yyyyMMddHHmmss");
        return df.format(calendar.getTime());
    }
}
