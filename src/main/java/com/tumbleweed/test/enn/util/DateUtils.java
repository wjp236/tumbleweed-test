package com.tumbleweed.test.enn.util;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.text.DateFormat;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.Date;
import java.util.Locale;

/**
 * 日期操作工具类
 * 
 * @author xiaojie.zhang
 * @since 2013年9月23日
 */
public class DateUtils extends org.apache.commons.lang3.time.DateUtils {
	private static Logger log = LoggerFactory.getLogger(DateUtils.class);
	public static final long SECONDS_PER_DAY = 86400;
	public static final Locale DEFAUTL_LOCALE = Locale.ENGLISH;

	@Deprecated
	public static final SimpleDateFormat FORMAT_SPLIT = new SimpleDateFormat(
			"yyyyMM", DEFAUTL_LOCALE);
	@Deprecated
	public static final SimpleDateFormat FORMAT_LNG = new SimpleDateFormat(
			"yyyyMMdd", DEFAUTL_LOCALE);
	@Deprecated
	public static final SimpleDateFormat FORMAT_MESSAGE = new SimpleDateFormat(
			"yyyy-MM-dd", DEFAUTL_LOCALE);
	@Deprecated
	public static final SimpleDateFormat FORMAT_STD = new SimpleDateFormat(
			"yyyy/MM/dd", DEFAUTL_LOCALE);
	@Deprecated
	public static final SimpleDateFormat FORMAT_YCLOANS = new SimpleDateFormat(
			"yyyy-MM-dd", DEFAUTL_LOCALE);
	@Deprecated
	public static final SimpleDateFormat FORMAT_BJTRANS = new SimpleDateFormat(
			"yyyyMMdd", DEFAUTL_LOCALE);
	@Deprecated
	public static final SimpleDateFormat FORMAT_BILL = new SimpleDateFormat(
			"yyyy年MM月dd日", DEFAUTL_LOCALE);
	// SystemInfo
	// .getSystemInfo().getSystemParameters().getDateFormat();
	@Deprecated
	public static final SimpleDateFormat FORMAT_YCLOANS_TIMES = new SimpleDateFormat(
			"HH:mm:ss", DEFAUTL_LOCALE);
	@Deprecated
	public static final SimpleDateFormat FORMAT_FULL_TIMES = new SimpleDateFormat(
			"yyyy-MM-dd HH:mm:ss", DEFAUTL_LOCALE);
	@Deprecated
	public static final SimpleDateFormat FORMAT_PAY = new SimpleDateFormat(
			"yyyyMMddHHmmss", DEFAUTL_LOCALE);

	private DateUtils() {
	}

	/**
	 * 字符串转为日期
	 * 
	 * @param str
	 *            字符串日期
	 * @param parsePatterns
	 *            转化格式
	 * @return Date 日期
	 */
	public static Date parseDate(final String str,
			final String... parsePatterns) {
		try {
			return parseDate(str, null, parsePatterns);
		} catch (ParseException e) {
			throw new RuntimeException(e);
		}
	}

	/**
	 * 获取一天的最小时间
	 * 
	 * @param date
	 *            指定日期
	 * @return Date 返回指定日期的最小时间
	 */
	public static Date getMinTime(Date date) {
		return truncate(date, Calendar.DATE);
	}

	/**
	 * 获取一天的最大时间
	 * 
	 * @param date
	 *            指定日期
	 * @return Date 返回指定日期的最大时间
	 */
	public static Date getMaxTime(Date date) {
		return addMilliseconds(addDays(truncate(date, Calendar.DATE), 1), -1);
	}

	/**
	 * 把数据库的字符串转化成Date型
	 * 
	 * @param dateString
	 *            字符串格式(yyyy-MM-dd)
	 * @return Date
	 */
	public static Date getDateFromDbString(String dateString) {
		try {
			return new SimpleDateFormat("yyyy-MM-dd", DEFAUTL_LOCALE)
					.parse(dateString);
		} catch (ParseException e) {
			throw new RuntimeException("string format error!!");
		}
	}

	/**
	 * @param date1
	 *            ,被减数
	 * @param date2
	 *            ， 减数 比较两个日期的大小
	 * @return int 两个日期相隔的天数
	 */
	public static int compareDates(Date date1, Date date2) {
		long days = 0;
		try {
			days = (date1.getTime() - date2.getTime()) / 60 / 60 / 1000 / 24;
			return (int) days;
		} catch (Exception e) {
			throw new RuntimeException("get failed", e);
		}
	}

	/**
	 * 
	 * 比较两个 YYYYMMDD 格式的日期 (作者：tianlele<tianlelea@enn.com>)
	 * 
	 * @param date1
	 *            输入日期
	 * @param date2
	 *            要比较的日期
	 * @return int
	 * @throws ParseException
	 */
	public static int compareDates(String date1, String date2)
			throws ParseException {
		SimpleDateFormat format = new SimpleDateFormat("yyyyMMdd");
		return format.parse(date1).compareTo(format.parse(date2));

	}

	/**
	 * 获取指定日期的前一天
	 * 
	 * @param specifiedDay
	 *            20150101
	 * @param dateFormateTemple
	 *            yyyyMMdd
	 * @return String
	 */
	public static String getSpecifiedDayBefore(String specifiedDay,
			String dateFormateTemple) {
		Calendar c = Calendar.getInstance();
		Date date = null;
		try {
			date = new SimpleDateFormat(dateFormateTemple).parse(specifiedDay);
		} catch (ParseException e) {
			log.error("获取指定日期的前一天getSpecifiedDayBefore()方法出错", e);
		}
		c.setTime(date);
		int day = c.get(Calendar.DATE);
		c.set(Calendar.DATE, day - 1);

		String dayBefore = new SimpleDateFormat(dateFormateTemple).format(c
				.getTime());
		return dayBefore;
	}

	/**
	 * 获取Date类型的 当前日期的前一天 （作者：peijiaqi<peijiaqi@ennew.com>）
	 * 
	 * @return
	 */
	public static Date getBeforeDate() {
		Date date = new Date();
		Calendar calendar = Calendar.getInstance();
		calendar.setTime(date);
		calendar.add(Calendar.DAY_OF_MONTH, -1);
		date = calendar.getTime();
		return date;
	}

	/**
	 * 获取Date类型的 指定日期的前一天 （作者：peijiaqi<peijiaqi@ennew.com>）
	 *
	 * @param date
	 * @return
	 */
	public static Date getBeforeDateSpecified(Date date) {
		Calendar calendar = Calendar.getInstance();
		calendar.setTime(date);
		calendar.add(Calendar.DAY_OF_MONTH, -1);
		date = calendar.getTime();
		return date;
	}

	/**
	 * 获取指定日期的后一天
	 * 
	 * @param specifiedDay
	 * @param dateFormateTemple
	 * @return String
	 */
	public static String getSpecifiedDayAfter(String specifiedDay,
			String dateFormateTemple) {
		Calendar c = Calendar.getInstance();
		Date date = null;
		try {
			date = new SimpleDateFormat(dateFormateTemple).parse(specifiedDay);
		} catch (ParseException e) {
			log.error("获取指定日期的后一天getSpecifiedDayAfter()方法出错", e);
		}
		c.setTime(date);
		int day = c.get(Calendar.DATE);
		c.set(Calendar.DATE, day + 1);

		String dayAfter = new SimpleDateFormat(dateFormateTemple).format(c
				.getTime());
		return dayAfter;
	}

	/**
	 * 
	 * getCurrentDate 获取当前日期 yyyyMMdd
	 * 
	 * @return String
	 */
	public static String getCurrentDate() {
		Date now = new Date();
		return org.apache.commons.lang3.time.DateFormatUtils.format(now,
				"yyyyMMdd");
	}

	/**
	 * 
	 * getCurrentTime 获取当前时间 HHmmss
	 * 
	 * @return String
	 */
	public static String getCurrentTime() {
		Date now = new Date();
		return org.apache.commons.lang3.time.DateFormatUtils.format(now,
				"HHmmss");
	}

	/**
	 * 
	 * getCurrentTime 获取当前时间 yyyyMMddHHmmss
	 * 
	 * @return String
	 */
	public static String getCurrentDateTime() {
		Date now = new Date();
		return org.apache.commons.lang3.time.DateFormatUtils.format(now,
				"yyyyMMddHHmmss");
	}

	/**
	 * beforeOneHourToNowDate 获取前几小时的时间
	 * 
	 * @return void
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
	 * 
	 * beforeNMinuteToNowDate 获取前几分钟的时间
	 * 
	 * @param minutes
	 * @return String
	 */
	public static Date beforeNMinuteToNowDate(int minutes) {
		Calendar calendar = Calendar.getInstance();
		calendar.add(Calendar.MINUTE, -minutes);
		return calendar.getTime();
	}

	/**
	 * 
	 * beforeMonth 获取上个月的月份
	 * 
	 * @return String 格式yyyyMM
	 */
	public static String beforeMonth(int i) {
		Calendar c = Calendar.getInstance();
		c.add(Calendar.MONTH, -i);
		SimpleDateFormat format = new SimpleDateFormat("yyyyMM");
		String time = format.format(c.getTime());
		return time;
	}

	/**
	 * 
	 * beforeMonth 获取上个月的同一天
	 * 
	 * @return String 格式yyyyMM
	 */
	public static String beforeMonthDay(int i) {
		Calendar c = Calendar.getInstance();
		c.add(Calendar.MONTH, -i);
		SimpleDateFormat format = new SimpleDateFormat("yyyyMMdd");
		String time = format.format(c.getTime());
		return time;
	}

	/**
	 * 
	 * nextMonthDay 获取下一个月的同一天，传入格式为yyyyMMdd
	 * 
	 * @param currentDay
	 * @return String 格式yyyyMMdd
	 */
	public static String nextMonthDay(String currentDay) {
		SimpleDateFormat format = new SimpleDateFormat("yyyyMMdd");
		Calendar c = Calendar.getInstance();
		try {
			c.setTime(format.parse(currentDay));
		} catch (ParseException e) {
			e.printStackTrace();
		}
		c.add(Calendar.MONTH, 1);
		String time = format.format(c.getTime());
		return time;
	}

	/**
	 * 
	 * beforeMonth 获取上年年份
	 * 
	 * @return String 格式yyyyMM
	 */
	public static String beforeYear(int i) {
		Calendar c = Calendar.getInstance();
		c.add(Calendar.YEAR, -i);
		SimpleDateFormat format = new SimpleDateFormat("yyyy");
		String time = format.format(c.getTime());
		return time;
	}

	/**
	 * 
	 * beforeMonth 获取相隔N日的日期
	 * 
	 * @return String 格式yyyyMM
	 */
	public static String intervalDays(int i) {
		Calendar c = Calendar.getInstance();
		c.add(Calendar.DATE, i);
		SimpleDateFormat format = new SimpleDateFormat("yyyyMMdd");
		String time = format.format(c.getTime());
		return time;
	}

	/**
	 * 
	 * intervalDays 获取传入日期相隔N日的日期,日期格式为yyyyMMdd
	 * 
	 * @param currentDay
	 * @return String 格式yyyyMMdd
	 */
	public static String intervalDays(String currentDay, int i) {
		SimpleDateFormat format = new SimpleDateFormat("yyyyMMdd");
		Calendar c = Calendar.getInstance();
		try {
			c.setTime(format.parse(currentDay));
		} catch (ParseException e) {
			e.printStackTrace();
		}
		c.add(Calendar.DATE, i);
		String time = format.format(c.getTime());
		return time;
	}

	/**
	 * @throws ParseException
	 * 
	 *             getNextWorkDay 获取给定日期下一个工作日的日期
	 * @param now
	 *            YYYYMMDD
	 * @return String 返回类型
	 */
	public static String getNextWorkDay(String now) throws ParseException {
		String ret = now;
		DateFormat format1 = new SimpleDateFormat("yyyyMMdd");
		Date bdate = format1.parse(now);
		Calendar cal = Calendar.getInstance();
		cal.setTime(bdate);
		// TODO 设计法定假日录入表
		if ("该日期是法定假日".equals(now)) {
			ret = now;
		} else if (cal.get(Calendar.DAY_OF_WEEK) == Calendar.SUNDAY) {
			cal.add(Calendar.DATE, 1);
			ret = format1.format(cal.getTime());
		} else if (cal.get(Calendar.DAY_OF_WEEK) == Calendar.SATURDAY) {
			cal.add(Calendar.DATE, 2);
			ret = format1.format(cal.getTime());
		}
		return ret;
	}

	/**
	 * 
	 * 获取对应的日期是星期几，星期日为0 (作者：tianlele<tianlelea@enn.com>)
	 * 
	 * @param text
	 *            格式为YYYYMMDD的日期
	 * @return int
	 * @throws ParseException
	 */
	public static int getDayOfWeek(String text) throws ParseException {
		Calendar calendar = Calendar.getInstance();
		if (text != null) {
			Date date = new SimpleDateFormat("yyyyMMdd").parse(text);
			calendar.setTime(date);
		}
		return calendar.get(Calendar.DAY_OF_WEEK) - 1;
	}

}
