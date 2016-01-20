package wang.tumbleweed.util;

import org.apache.commons.lang.time.DateFormatUtils;
import org.apache.commons.lang.time.DateUtils;
import org.apache.commons.lang.time.FastDateFormat;

import java.sql.Timestamp;
import java.text.ParseException;
import java.util.Calendar;
import java.util.Date;
import java.util.GregorianCalendar;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

public class DateUtil {
	public static final FastDateFormat simpleDateFormat_default = FastDateFormat.getInstance("yyyy/MM/dd");
	public static final FastDateFormat simpleDateFormat_ym = FastDateFormat.getInstance("yyyy/MM");
	public static final FastDateFormat simpleDateFormat_no_slash = FastDateFormat.getInstance("yyyyMMdd");
	public static final FastDateFormat simpleDateFormat_ymr_slash = FastDateFormat.getInstance("yyyy-MM-dd");
	public static final FastDateFormat simpleDateFormat_ym_no_slash = FastDateFormat.getInstance("yyyyMM");
	public static final FastDateFormat simpleDateFormat_date_time = FastDateFormat.getInstance("yyyy/MM/dd HH:mm:ss");
	public static final FastDateFormat simpleDateFormat_date_time_no_slash = FastDateFormat
			.getInstance("yyyyMMddHHmmss");
	public static final FastDateFormat simpleDateFormat_date_hm = FastDateFormat.getInstance("yyyy/MM/dd HH:mm");
	public static final FastDateFormat simpleDateFormat_time = FastDateFormat.getInstance("HH:mm:ss");
	public static final FastDateFormat simpleDateFormat_hm = FastDateFormat.getInstance("HH:mm");
	public static final FastDateFormat simpleDateFormat_long_time = FastDateFormat.getInstance("HHmmss");
	public static final FastDateFormat simpleDateFormat_short_time = FastDateFormat.getInstance("HHmm");
	public static final FastDateFormat simpleDateFormat_date_time_line = FastDateFormat
			.getInstance("yyyy-MM-dd HH:mm:ss");

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
	 * 变量：日期格式化类型 - 格式:yyyy-MM-dd HH:mm:ss
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

	/**
	 * 星期常量
	 */
	public static final String[] WEEK = { "Sunday", "Monday", "Tuesday", "Wednesday", "Thursday", "Friday", "Saturday" };

	/**
	 * 常用日期格式化
	 */
	public static final String[] DATE_PATTERN = new String[] { "yyyyMMddHHmmss", "yyyy-MM-dd HH:mm:ss",
			"yyyy-MM-dd HH:mm:ss.S" };

	public static final String[] DATE_PATTERN2 = new String[] { "yyyyMMddHHmmss" };
	
	
	public static final String[] DATE_PATTERN3 = new String[] { "yyyy-MM-dd HH:mm:ss" };
	
	/**
	 * 判断是否符合日期格式 yyyy-MM-dd HH:mm:ss
	 * 
	 * @param time
	 * @return
	 */
	public static boolean isFormatTime2(String time) {
		try {
			Date d = DateUtils.parseDate(time, DATE_PATTERN3);
			return d != null ? true : false;
		} catch (ParseException e) {
			return false;
		}
	}

	/**
	 * 判断是否符合日期格式 yyyyMMddHHmmss
	 * 
	 * @param time
	 * @return
	 */
	public static boolean isFormatTime(String time) {
		try {
			Date d = DateUtils.parseDate(time, DATE_PATTERN2);
			return d != null ? true : false;
		} catch (ParseException e) {
			return false;
		}
	}

	/**
	 * 取得时间 yyyyMMddHHmmss
	 * 
	 * @param time
	 * @return
	 */
	public static String formatTimestamp(long time) {
		return DateFormatUtils.format(time, "yyyyMMddHHmmss");
	}

	/**
	 * 获取年和月
	 * 
	 * @param date
	 * @return
	 */
	public static String getYearMonth(Date date) {
		Calendar c = Calendar.getInstance();
		c.setTime(date);
		int year = c.get(Calendar.YEAR);
		int month = c.get(Calendar.MONTH) + 1;
		String monthStr = month < 10 ? "0" + month : "" + month;
		return year + monthStr;
	}

	/**
	 * 取得时间 yyyy-MM-dd HH:mm:ss
	 * 
	 * @return 时间字符串
	 */
	public static String getDateTime(Date date) {
		return DateFormatUtils.format(date, "yyyy-MM-dd HH:mm:ss");
	}

	public static String getTextReceiveDate(String time) {
		// 2012 05 17 18 49 03
		StringBuffer sbBuffer = new StringBuffer();
		sbBuffer.append(time.substring(0, 4) + "-");
		sbBuffer.append(time.substring(4, 6) + "-");
		sbBuffer.append(time.substring(6, 8) + " ");

		sbBuffer.append(time.substring(8, 10) + ":");
		sbBuffer.append(time.substring(10, 12) + ":");
		sbBuffer.append(time.substring(12, time.length()));

		return sbBuffer.toString();
	}

	/**
	 * @param date
	 * @param type
	 * @return
	 */
	public static Date strToDate(String date, int type) {
		try {
			Date d = DateUtils.parseDate(date, DATE_PATTERN);
			return d;
		} catch (ParseException e) {
			return null;
		}
	}

	/**
	 * @param date
	 * @param type
	 * @return
	 */
	public static String dateToStr(Date date, int type) {
		switch (type) {
		case DEFAULT:
			return simpleDateFormat_default.format(date);
		case YM:
			return simpleDateFormat_ym.format(date);
		case NO_SLASH:
			return simpleDateFormat_no_slash.format(date);
		case YMR_SLASH:
			return simpleDateFormat_ymr_slash.format(date);
		case YM_NO_SLASH:
			return simpleDateFormat_ym_no_slash.format(date);
		case DATE_TIME:
			return simpleDateFormat_date_time.format(date);
		case DATE_TIME_NO_SLASH:
			return simpleDateFormat_date_time_no_slash.format(date);
		case DATE_HM:
			return simpleDateFormat_date_hm.format(date);
		case TIME:
			return simpleDateFormat_time.format(date);
		case HM:
			return simpleDateFormat_hm.format(date);
		case LONG_TIME:
			return simpleDateFormat_long_time.format(date);
		case SHORT_TIME:
			return simpleDateFormat_short_time.format(date);
		case DATE_TIME_LINE:
			return simpleDateFormat_date_time_line.format(date);
		default:
			throw new IllegalArgumentException("Type undefined: " + type);
		}
	}

	/**
	 * 判定给定的年是否为闰年
	 * 
	 * @param year
	 * @return
	 */
	private static boolean isLeapYear(int year) {
		return (year % 4 == 0 && year % 100 != 0) || (year % 400 == 0);
	}

	/**
	 * 取得给定的月份的天数
	 * 
	 * @param year
	 * @param month
	 * @return
	 */
	public static int getDaysOfMonth(int year, int month) {
		switch (month) {
		case 1:
		case 3:
		case 5:
		case 7:
		case 8:
		case 10:
		case 12:
			return 31;
		case 4:
		case 6:
		case 9:
		case 11:
			return 30;
		case 2:
			if (isLeapYear(year)) {
				return 29;
			}
			return 28;
		default:
			return -1;
		}
	}

	/**
	 * 获取Timestamp格式的当前系统时间
	 * 
	 * @return
	 */
	public static Timestamp getDateOfTimestamp() {
		Timestamp timeStamp = null;
		String dateTime = DateUtil.dateToStr(new Date(), DateUtil.DATE_TIME_LINE);
		if (!"".equals(dateTime) && dateTime != null) {
			timeStamp = Timestamp.valueOf(dateTime);
		}
		return timeStamp;
	}

	/**
	 * 获取当前系统时间 格式: yyyy-MM-dd
	 * 
	 * @return
	 */
	public static String getDateOfString(Timestamp date, int i) {
		String dateTime = dateToStr(date, i);
		return dateTime;
	}

	/**
	 * 获取年月日，格式：yyyy-MM-dd HH:mm:ss
	 * 
	 * @return
	 */
	public static String getDate() {
		String dateTime = dateToStr(new Date(), DateUtil.DATE_TIME_LINE);
		return dateTime;
	}

	/**
	 * 获取年月日时分秒，格式：yyyyMMddHHmmss
	 * 
	 * @return
	 */
	public static String getTimeOfToday() {
		String dateTime = dateToStr(new Date(), DateUtil.DATE_TIME_NO_SLASH);
		return dateTime;
	}

	/**
	 * 获取本月第一天
	 * 
	 * @return now 当前日期
	 */
	public static String getFristMonth() {
		Calendar ca = Calendar.getInstance();
		ca.setTime(new Date());
		ca.set(Calendar.DAY_OF_MONTH, 1);
		String now = simpleDateFormat_ymr_slash.format(ca.getTime());
		return now;
	}

	/**
	 * 获取本周第一天
	 * 
	 * @return firstMonday
	 */
	public static String getFristWeek() {
		int mondayPlus;
		Calendar cd = Calendar.getInstance();
		int dayOfWeek = cd.get(Calendar.DAY_OF_WEEK) - 1;
		if (dayOfWeek == 1) {
			mondayPlus = 0;
		} else {
			mondayPlus = 1 - dayOfWeek;
		}
		GregorianCalendar currentDate = new GregorianCalendar();
		currentDate.add(GregorianCalendar.DATE, mondayPlus);
		Date monday = currentDate.getTime();
		String firstMonday = simpleDateFormat_ymr_slash.format(monday);
		return firstMonday;
	}
	
	/**
     * 获取上周一的日期，
     * 格式：yyyy-MM-dd
     * @return
     */
    public static String getLastWeekMonday(){

    	Calendar cal = Calendar.getInstance();
    	cal.setFirstDayOfWeek(Calendar.MONDAY);// 将每周第一天设为星期一，默认是星期天
    	cal.add(Calendar.WEEK_OF_MONTH,-1);// 周数减一，即上周
    	cal.set(Calendar.DAY_OF_WEEK, Calendar.MONDAY);// 日子设为星期一
    	Date d = cal.getTime();
    	String day = dateToStr(d, YMR_SLASH);
        return day;
    }

	/**
	 * 获取当前日期 yyyy-MM-dd
	 * 
	 * @return
	 */
	public static String getNowDay() {
		return DateFormatUtils.format(new Date(), "yyyy-MM-dd");
	}

	/**
	 * 呼叫开始的时间，单位秒，用1970/1/1 0:00:00以来的秒数来表示，类型为long
	 * @param nowDay
	 * @return
	 */
	public static long getTimestamp(String nowDay) {
		try {
			Date d = DateUtils.parseDate(nowDay, DATE_PATTERN2);
			return d.getTime();
		} catch (ParseException e) {
			e.printStackTrace();
		}
		return 0;
	}

	/**
	 * 获取当前时间的时间戳
	 * 
	 * @return
	 */
	public static long getTimestamp() {
		long time = System.currentTimeMillis();
		return time;
	}

	/**
	 * 获取下一天的时间戳
	 * 
	 * @return
	 */
	public static long getNextDayTimestamp(String nowDay) {
		try {
			Date d = DateUtils.addDays(DateUtils.parseDate(nowDay, new String[]{"yyyy-MM-dd"}), 1);
			return d.getTime();
		} catch (ParseException e) {
			return System.currentTimeMillis();
		}
	}

	/**
	 * 获取前一天的日期
	 * 
	 * @return
	 */
	public static String getPrevDate() {
		Calendar calendar = Calendar.getInstance();// 此时打印它获取的是系统当前时间
		calendar.add(Calendar.DATE, -1); // 得到前一天
		String dayBefore = simpleDateFormat_ymr_slash.format(calendar.getTime());
		return dayBefore;
	}
	
	/**
	 * 获取当前yyyy-MM-dd-HH格式日期
	 * 
	 * @return 
	 */
	public static String getPrevHour() {
		return DateFormatUtils.format(new Date(), "yyyy-MM-dd-HH");
	}
	
	/**
	 * 判断输入的字符串是否满足时间格式 ： yyyy-MM-dd HH:mm:ss
	 * @param patternString 需要验证的字符串
	 * @return 合法返回 true ; 不合法返回false
	 */
	public static boolean isTimeLegal(String patternString) {
		
		Pattern a=Pattern.compile("^((((1[6-9]|[2-9]\\d)\\d{2})-(0?[13578]|1[02])-(0?[1-9]|[12]\\d|3[01]))|(((1[6-9]|[2-9]\\d)\\d{2})-(0?[13456789]|1[012])-(0?[1-9]|[12]\\d|30))|(((1[6-9]|[2-9]\\d)\\d{2})-0?2-(0?[1-9]|1\\d|2[0-8]))|(((1[6-9]|[2-9]\\d)(0[48]|[2468][048]|[13579][26])|((16|[2468][048]|[3579][26])00))-0?2-29-)) (20|21|22|23|[0-1]?\\d):[0-5]?\\d:[0-5]?\\d$"); 
		
		Matcher b=a.matcher(patternString); 
		if(b.matches()) {
			return true;
		} else {
			return false;
		}
	}
}
