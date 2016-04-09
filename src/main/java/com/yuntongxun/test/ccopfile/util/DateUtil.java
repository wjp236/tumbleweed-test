package com.yuntongxun.test.ccopfile.util;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;

/**
 * <p>Title: DateUtil</p>
 * <p>Description: 时间工具类</p>
 * <p>Copyright: Copyright (c) 2010</p>
 * <p>Company: hisunsray</p>
 * <p>Date:2012-08-20</p>
 * @author tanglujun
 * @version 1.0
 */
public class DateUtil {
    /**
     * 变量：日期格式化类型 - 格式:yyyy/MM/dd
     * 
     * @since 1.0
     */
    public static final int DEFAULT = 0;

    /**
     * 变量：日期格式化类型 - 格式:yyyy/MM
     * 
     * @since 1.0
     */
    public static final int YM = 1;

    /**
     * 变量：日期格式化类型 - 格式:yyyy-MM-dd
     * 
     * @since 1.0
     */
    public static final int YMR_SLASH = 11;

    /**
     * 变量：日期格式化类型 - 格式:yyyyMMdd
     * 
     * @since 1.0
     */
    public static final int NO_SLASH = 2;

    /**
     * 变量：日期格式化类型 - 格式:yyyyMM
     * 
     * @since 1.0
     */
    public static final int YM_NO_SLASH = 3;

    /**
     * 变量：日期格式化类型 - 格式:yyyy/MM/dd HH:mm:ss
     * 
     * @since 1.0
     */
    public static final int DATE_TIME = 4;

    /**
     * 变量：日期格式化类型 - 格式:yyyyMMddHHmmss
     * 
     * @since 1.0
     */
    public static final int DATE_TIME_NO_SLASH = 5;

    /**
     * 变量：日期格式化类型 - 格式:yyyy/MM/dd HH:mm
     * 
     * @since 1.0
     */
    public static final int DATE_HM = 6;

    /**
     * 变量：日期格式化类型 - 格式:HH:mm:ss
     * 
     * @since 1.0
     */
    public static final int TIME = 7;

    /**
     * 变量：日期格式化类型 - 格式:HH:mm
     * 
     * @since 1.0
     */
    public static final int HM = 8;
    
    /**
     * 变量：日期格式化类型 - 格式:HHmmss
     * 
     * @since 1.0
     */
    public static final int LONG_TIME = 9;
    /**
     * 变量：日期格式化类型 - 格式:HHmm
     * 
     * @since 1.0
     */
    
    public static final int SHORT_TIME = 10;

    /**
     * 变量：日期格式化类型 - 格式:yyyy-MM-dd HH:mm:ss
     * 
     * @since 1.0
     */
    public static final int DATE_TIME_LINE = 12;
    
    /**
     * 变量：1小时的毫秒数
     * 
     * @since 1.0
     */
    public static final double HOUR_MILLISECOND = 1000 * 60 * 60;

    public static final String[] WEEK = { "Sunday", "Monday", "Tuesday",
            "Wednesday", "Thursday", "Friday", "Saturday" };

    public static Date strToDate(String date, String pattern) {
        if (date == null || date.equals(""))
            return null;
        SimpleDateFormat fmt = new SimpleDateFormat(pattern);
        try {
            return fmt.parse(date);
        } catch (ParseException e) {
            throw new RuntimeException(e.toString());
        }
    }

    public static String dateToStr(Date date,String pattern) {
     if (date == null || date.equals(""))
    	return null;
     SimpleDateFormat formatter = new SimpleDateFormat(pattern);
     return formatter.format(date);
    } 

    public static Date strToDate(String date) {
        return strToDate(date, "yyyy/MM/dd");
    }

    public static String dateToStr(Date date) {
        return dateToStr(date, "yyyy/MM/dd");
    }
    
    public static Date strToDate(String date, int type) {
        switch (type) {
        case DEFAULT:
            return strToDate(date);
        case YM:
            return strToDate(date, "yyyy/MM");
        case NO_SLASH:
            return strToDate(date, "yyyyMMdd");
        case YMR_SLASH:
        	return strToDate(date, "yyyy-MM-dd");
        case YM_NO_SLASH:
            return strToDate(date, "yyyyMM");
        case DATE_TIME:
            return strToDate(date, "yyyy/MM/dd HH:mm:ss");
        case DATE_TIME_NO_SLASH:
            return strToDate(date, "yyyyMMddHHmmss");
        case DATE_HM:
            return strToDate(date, "yyyy/MM/dd HH:mm");
        case TIME:
            return strToDate(date, "HH:mm:ss");
        case HM:
            return strToDate(date, "HH:mm");
        case DATE_TIME_LINE:
        	return strToDate(date,"yyyy-MM-dd HH:mm:ss");
        default:
            throw new IllegalArgumentException("Type undefined : " + type);
        }
    }

    public static String dateToStr(Date date, int type) {
        switch (type) {
        case DEFAULT:
            return dateToStr(date);
        case YM:
            return dateToStr(date, "yyyy/MM");
        case NO_SLASH:
            return dateToStr(date, "yyyyMMdd");
        case YMR_SLASH:
        	return dateToStr(date, "yyyy-MM-dd");
        case YM_NO_SLASH:
            return dateToStr(date, "yyyyMM");
        case DATE_TIME:
            return dateToStr(date, "yyyy/MM/dd HH:mm:ss");
        case DATE_TIME_NO_SLASH:
            return dateToStr(date, "yyyyMMddHHmmss");
        case DATE_HM:
            return dateToStr(date, "yyyy/MM/dd HH:mm");
        case TIME:
            return dateToStr(date, "HH:mm:ss");
        case HM:
            return dateToStr(date, "HH:mm");
        case LONG_TIME:
            return dateToStr(date, "HHmmss");
        case SHORT_TIME:
            return dateToStr(date, "HHmm");
        case DATE_TIME_LINE:
            return dateToStr(date, "yyyy-MM-dd HH:mm:ss");
        default:
            throw new IllegalArgumentException("Type undefined : " + type);
        }
    }
    
    public static double subOfTwoTime(String time1, String time2) {
        Date d1 = strToDate(time1, DATE_TIME);
        Date d2 = strToDate(time2, DATE_TIME);
        return subOfTwoTime(d1.getTime(), d2.getTime());
    }

    public static double subOfTwoTime(double time1, double time2) {
        return ArithUtil.div(time2 - time1, HOUR_MILLISECOND, 2);
    }

    public static double subOfTwoTime(String time1, double time2) {
        Date d1 = strToDate(time1, DATE_TIME);
        return subOfTwoTime(d1.getTime(), time2);
    }

    public static double subOfTwoTime(double time1, String time2) {
        Date d2 = strToDate(time2, DATE_TIME);
        return subOfTwoTime(time1, d2.getTime());
    }

}
