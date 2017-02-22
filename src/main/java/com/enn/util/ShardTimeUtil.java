package com.enn.util;

import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.Date;

/**
 * @Description: 根据ID获取Mycat分片时间串
 * @author tlla
 * @date 2016/12/5 16:32
 */
public class ShardTimeUtil {

    private static final String format = "yyyyMMdd";
    private static final int DATE_LENGTH = format.length();
    private static final int TRANS_ID_DATE_INDEX = 3;
    private static final int CHANNEL_ID_DATE_INDEX = 0;

    public static String fromTradeNo(String id) throws Exception {
        if (id == null) {
            return null;
        }

        String dateStr = id.substring(TRANS_ID_DATE_INDEX, TRANS_ID_DATE_INDEX + DATE_LENGTH);
        dateStr = formatShardingTime(dateStr);
        return dateStr;
    }

    /**
     * 将yyyyMMdd格式的日期转换为yyyy-MM-dd格式的日期
     * author zhangding<zhangdingb@enn.com>
     *
     * @param shardingTime
     * @return
     * @throws Exception 
     */
    public static String formatShardingTime(String shardingTime) throws Exception {
    	
    	SimpleDateFormat dfParse = new SimpleDateFormat("yyyyMMdd");
		SimpleDateFormat dfFormat = new SimpleDateFormat("yyyy-MM-dd");
		Date parseDate = dfParse.parse(shardingTime);
		String formatDate = dfFormat.format(parseDate);
		return formatDate;
		
    }
    
    /**
     * 将yyyy-MM-dd格式的日期转换为yyyyMMdd格式的日期
     * author zhangding<zhangdingb@enn.com>
     *
     * @param shardingTime
     * @return
     * @throws Exception 
     */
    public static String formatShardingTimeToCurrent(String shardingTime) throws Exception {
    	
    	SimpleDateFormat dfParse = new SimpleDateFormat("yyyy-MM-dd");
		SimpleDateFormat dfFormat = new SimpleDateFormat("yyyyMMdd");
		Date parseDate = dfParse.parse(shardingTime);
		String formatDate = dfFormat.format(parseDate);
		return formatDate;
		
    }

    public static boolean isShardingTime() {

        Calendar c = Calendar.getInstance();
        c.set(Calendar.HOUR_OF_DAY, 1);
        c.set(Calendar.MINUTE, 0);
        c.set(Calendar.SECOND, 0);
        long t = c.getTimeInMillis();
        long now = System.currentTimeMillis();
        if ((t - now) >= 0) {
            return true;
        }
        return false;
    }

}
