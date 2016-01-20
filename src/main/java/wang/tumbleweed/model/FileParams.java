package wang.tumbleweed.model;

/**
 * Created by mylover on 9/9/15.
 */
public class FileParams {

    private String appId;
    private String msgSid;
    private String msgType;
    private String msgReceiver;
    private String msgSender;
    private String msgFileName;
    private String chanType;
    private String deviceNo;
    private String msgDomain;

    public FileParams() {
    }

    public FileParams(String appId, String msgSid, String msgType, String msgReceiver, String msgSender, String msgFileName, String chanType) {
        this.appId = appId;
        this.msgSid = msgSid;
        this.msgType = msgType;
        this.msgReceiver = msgReceiver;
        this.msgSender = msgSender;
        this.msgFileName = msgFileName;
        this.chanType = chanType;
    }

    public String getAppId() {
        return appId;
    }

    public void setAppId(String appId) {
        this.appId = appId;
    }

    public String getMsgSid() {
        return msgSid;
    }

    public void setMsgSid(String msgSid) {
        this.msgSid = msgSid;
    }

    public String getMsgType() {
        return msgType;
    }

    public void setMsgType(String msgType) {
        this.msgType = msgType;
    }

    public String getMsgReceiver() {
        return msgReceiver;
    }

    public void setMsgReceiver(String msgReceiver) {
        this.msgReceiver = msgReceiver;
    }

    public String getMsgSender() {
        return msgSender;
    }

    public void setMsgSender(String msgSender) {
        this.msgSender = msgSender;
    }

    public String getMsgFileName() {
        return msgFileName;
    }

    public void setMsgFileName(String msgFileName) {
        this.msgFileName = msgFileName;
    }

    public String getChanType() {
        return chanType;
    }

    public void setChanType(String chanType) {
        this.chanType = chanType;
    }

    public String getDeviceNo() {
        return deviceNo;
    }

    public void setDeviceNo(String deviceNo) {
        this.deviceNo = deviceNo;
    }

    public String getMsgDomain() {
        return msgDomain;
    }

    public void setMsgDomain(String msgDomain) {
        this.msgDomain = msgDomain;
    }
}
