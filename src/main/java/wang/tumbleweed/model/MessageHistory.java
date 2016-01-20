package wang.tumbleweed.model;

public class MessageHistory {

	public static final String TAB_NAME = "ytx_history_message";

	private String channel;
	private String appIdSender;

	private String dateCreated; // 创建时间（传入）
	private long expired = 604800000; // 消息过期时间(ms)

	private String fileUrl;
	private String groupId;
	private String localFileName;
	private int mcmEvent; // 是否是多渠道离线消息 1:多渠道消息 else:原消息
	private String msgCompressLen;;

	private String msgContent;
	private String msgDomain;
	private String msgFileName;
	private String msgFileSize;

	private String msgId;
	private String msgLength;
	private String msgReceiver;
	private String msgSender;
	private String msgType;
	private String version;

	public String getChannel() {
		return channel;
	}

	public void setChannel(String channel) {
		this.channel = channel;
	}

	public String getAppIdSender() {
		return appIdSender;
	}

	public void setAppIdSender(String appIdSender) {
		this.appIdSender = appIdSender;
	}

	public String getDateCreated() {
		return dateCreated;
	}

	public void setDateCreated(String dateCreated) {
		this.dateCreated = dateCreated;
	}

	public long getExpired() {
		return expired;
	}

	public void setExpired(long expired) {
		this.expired = expired;
	}

	public String getFileUrl() {
		return fileUrl;
	}

	public void setFileUrl(String fileUrl) {
		this.fileUrl = fileUrl;
	}

	public String getGroupId() {
		return groupId;
	}

	public void setGroupId(String groupId) {
		this.groupId = groupId;
	}

	public String getLocalFileName() {
		return localFileName;
	}

	public void setLocalFileName(String localFileName) {
		this.localFileName = localFileName;
	}

	public int getMcmEvent() {
		return mcmEvent;
	}

	public void setMcmEvent(int mcmEvent) {
		this.mcmEvent = mcmEvent;
	}

	public String getMsgCompressLen() {
		return msgCompressLen;
	}

	public void setMsgCompressLen(String msgCompressLen) {
		this.msgCompressLen = msgCompressLen;
	}

	public String getMsgContent() {
		return msgContent;
	}

	public void setMsgContent(String msgContent) {
		this.msgContent = msgContent;
	}

	public String getMsgDomain() {
		return msgDomain;
	}

	public void setMsgDomain(String msgDomain) {
		this.msgDomain = msgDomain;
	}

	public String getMsgFileName() {
		return msgFileName;
	}

	public void setMsgFileName(String msgFileName) {
		this.msgFileName = msgFileName;
	}

	public String getMsgFileSize() {
		return msgFileSize;
	}

	public void setMsgFileSize(String msgFileSize) {
		this.msgFileSize = msgFileSize;
	}

	public String getMsgId() {
		return msgId;
	}

	public void setMsgId(String msgId) {
		this.msgId = msgId;
	}

	public String getMsgLength() {
		return msgLength;
	}

	public void setMsgLength(String msgLength) {
		this.msgLength = msgLength;
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

	public String getMsgType() {
		return msgType;
	}

	public void setMsgType(String msgType) {
		this.msgType = msgType;
	}

	public String getVersion() {
		return version;
	}

	public void setVersion(String version) {
		this.version = version;
	}
}
