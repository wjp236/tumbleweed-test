package com.yuntongxun.util;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.apache.logging.log4j.ThreadContext;
import com.yuntongxun.util.statsd.NonBlockingStatsDClient;
import com.yuntongxun.util.statsd.StatsDClient;

/**
* Created by wjp on 10/26/15.
*/
public class StatsdUtil {

    private static final Logger logger = LogManager.getLogger(StatsdUtil.class.getName());

    private static StatsDClient statsd = null;

    private static final String serialValueHeader = "rest.";
    private static final String serialValueModelNumber = "._t_moduleNumber.";
    private static final String serialValueMiddle = "._t_requestType.";
    private static final String serialValueView = "._t_account.";
    private static final String serialValueFail = "._t_failType.";

    /**
     * 获取statsd客户端
     * @return
     */
    public static StatsDClient getStatsdClient() {
        try {
            if (statsd == null) {
                String host = "192.168.21.51";
                String port = "8125";
                statsd = new NonBlockingStatsDClient("", host, Integer.parseInt(port));
            }
        } catch (Exception e) {
            logger.info("监控配置错误");
        }
        return statsd;
    }

    /**
     * 获取请求书指标名
     * @param requestType
     * @param appId
     * @return
     */
    public static String getRequestNumber(String requestType, String appId) {

        String restSerial = "8564";

        StringBuffer serialValue = new StringBuffer(serialValueHeader);
        serialValue.append("requestNumber");
        serialValue.append(serialValueModelNumber);
        serialValue.append(restSerial);
        serialValue.append(serialValueMiddle);
        serialValue.append(requestType);
        serialValue.append(serialValueView);
        serialValue.append(appId);

        logger.info("aspect: " + serialValue.toString());

        return serialValue.toString();
    }

    /**
     * 获取活跃数指标名
     * @param requestType
     * @param appId
     * @return
     */
    public static String getActiveRequest(String requestType, String appId) {

        String restSerial = "8564";

        StringBuffer serialValue = new StringBuffer(serialValueHeader);
        serialValue.append("activeRequest");
        serialValue.append(serialValueModelNumber);
        serialValue.append(restSerial);
        serialValue.append(serialValueMiddle);
        serialValue.append(requestType);
        serialValue.append(serialValueView);
        serialValue.append(appId);

        logger.info("aspect: " + serialValue.toString());

        return serialValue.toString();
    }

    /**
     * 获取请求其他模块时延指标名
     * @param model1
     * @param requestType
     * @return
     */
    public static String getOtherModelTimeCost(String model1, String requestType) {

        String restSerial = "8564";

        StringBuffer serialValue = new StringBuffer(serialValueHeader);
        serialValue.append(model1);
        serialValue.append(".timecost");
        serialValue.append(serialValueModelNumber);
        serialValue.append(restSerial);
        serialValue.append(serialValueMiddle);
        serialValue.append(requestType);

        logger.info("aspect: " + serialValue.toString());

        return serialValue.toString();
    }

    /**
     * 获取rest处理时延指标名
     * @param requestType
     * @param accountSid
     * @return
     */
    public static String getTimeCost(String requestType, String accountSid) {

        String restSerial = "8564";

        StringBuffer serialValue = new StringBuffer(serialValueHeader);
        serialValue.append("timecost");
        serialValue.append(serialValueModelNumber);
        serialValue.append(restSerial);
        serialValue.append(serialValueMiddle);
        serialValue.append(requestType);
        serialValue.append(serialValueView);
        serialValue.append(accountSid);

        logger.info("aspect: " + serialValue.toString());

        return serialValue.toString();
    }

    /**
     * 获取处理失败时延指标名
     * @param model1
     * @param requestType
     * @param failType
     * @return
     */
    public static String getOtherFail(String model1, String requestType, String failType) {

        String restSerial = "8564";

        StringBuffer serialValue = new StringBuffer(serialValueHeader);
        serialValue.append(model1);
        serialValue.append(".fail");
        serialValue.append(serialValueModelNumber);
        serialValue.append(restSerial);
        serialValue.append(serialValueMiddle);
        serialValue.append(requestType);
        serialValue.append(serialValueFail);
        serialValue.append(failType);
        logger.info("aspect: " + serialValue.toString());

        return serialValue.toString();
    }

    /**
     * 获取异常指标名
     * @param requestType
     * @param accountSid
     * @param failType
     * @return
     */
    public static String getHandleRequestFail(String requestType, String accountSid, String failType) {

        String restSerial = "8564";

        StringBuffer serialValue = new StringBuffer(serialValueHeader);
        serialValue.append("handleRequest.fail");
        serialValue.append(serialValueModelNumber);
        serialValue.append(restSerial);
        serialValue.append(serialValueMiddle);
        serialValue.append(requestType);
        serialValue.append(serialValueView);
        serialValue.append(accountSid);
        serialValue.append(serialValueFail);
        serialValue.append(failType);


        logger.info("aspect: " + serialValue.toString());

        return serialValue.toString();
    }

    /**
     * 截取业务类型
     * @param uri
     * @return
     */
    private static String getRequestTypeFromUri(String uri) {

        String requestType = null;

        if (uri != null) {
            requestType = uri.substring(uri.lastIndexOf("/") + 1);
        }
        return requestType;
    }

    /**
     * 发送请求数
     * @param uri
     * @param type
     * @param accountSid
     */
    public static void sendRequestNumber(String uri, String type, String accountSid) {
        try {
            String requestType = null;
            if ((type != null && type.equals("Switchs")) || uri == null || uri.lastIndexOf("/") < 0) {
                requestType = type;
            } else {
                requestType = StatsdUtil.getRequestTypeFromUri(uri);
            }
            ThreadContext.put("requestType", requestType);
            ThreadContext.put("accountSid", accountSid);
            ThreadContext.put("beginTime", String.valueOf(System.currentTimeMillis()));
            if (requestType != null && accountSid != null) {
                StatsdUtil.getStatsdClient().incrementCounter(StatsdUtil.getRequestNumber(requestType, accountSid));
                StatsdUtil.getStatsdClient().recordGaugeDelta(StatsdUtil.getActiveRequest(requestType, accountSid), 1);
            }
        } catch (Exception e) {
            logger.info("监控异常" + e.getMessage());
        }
    }

    /**
     * 发送正在处理的请求数
     * @return
     */
    public static void sendActiveRequest() {
        try {
            String requestType = ThreadContext.get("requestType");
            String accountSid = ThreadContext.get("accountSid");

            String itemName = StatsdUtil.getActiveRequest(requestType, accountSid);

            if (requestType != null && accountSid != null && itemName != null) {
                StatsdUtil.getStatsdClient().recordGaugeDelta(itemName, -1);
            }
        } catch (Exception e) {
            logger.info("监控异常");
        }
    }

    /**
     * 发送rest时延
     */
    public static void sendTimeCost() {
        try {
            String requestType = ThreadContext.get("requestType");
            String accountSid = ThreadContext.get("accountSid");

            String itemName = StatsdUtil.getTimeCost(requestType, accountSid);

            if (requestType != null && accountSid != null && ThreadContext.get("beginTime") != null) {

                long costTime = System.currentTimeMillis() - Long.parseLong(ThreadContext.get("beginTime"));

                StatsdUtil.getStatsdClient().recordExecutionTime(itemName, costTime);

            }
        } catch (Exception e) {
            logger.info("监控异常");
        }
    }

    /**
     * 发送其他模块时延
     * @param model1
     * @param costTime
     */
    public static void sendOtherCostTime(String model1, long costTime) {
        try {
            String requestType = ThreadContext.get("requestType");
            String itemName = StatsdUtil.getOtherModelTimeCost(model1, requestType);

            if (requestType != null && itemName != null) {
                StatsdUtil.getStatsdClient().recordExecutionTime(itemName, costTime);
            }
        } catch (Exception e) {
            logger.info("监控异常");
        }
    }

    /**
     * 发送rest失败
     * @param errorCode
     */
    public static void sendHandleRequestFail(String errorCode) {
        try {
            String requestType = ThreadContext.get("requestType");
            String accountSid = ThreadContext.get("accountSid");

            String itemName = StatsdUtil.getHandleRequestFail(requestType, accountSid, errorCode);
            if (requestType != null && accountSid != null && itemName != null) {
                StatsdUtil.getStatsdClient().incrementCounter(itemName);
            }
        } catch (Exception e) {
            logger.info("监控异常");
        }
    }
}
