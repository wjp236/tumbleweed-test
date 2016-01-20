package wang.tumbleweed.model;

import java.util.List;

public class PushMessage {

	private Integer pushType = 1;// 1:个人，2：群组，3：应用
	private String appId;// 应用Id
	private String sender;// 发送者账号
	private List<String> receiver;// 接收者（个人或者群组）
	private String groupId;
	private Integer msgType;// 消息类型，1:文本消息，2：语音消息，3：视频消息，4：图片消息，5：位置消息，6：文件
	private String msgContent;
	private String msgDomain;
	private String msgFileName;
	private String msgFileUrl;
	private String senderNickName;

	/**
	 * @return the senderNickName
	 */
	public String getSenderNickName() {
		return senderNickName;
	}

	/**
	 * @param senderNickName
	 *            the senderNickName to set
	 */
	public void setSenderNickName(String senderNickName) {
		this.senderNickName = senderNickName;
	}

	public Integer getPushType() {
		return pushType;
	}

	public void setPushType(Integer pushType) {
		this.pushType = pushType;
	}

	public String getAppId() {
		return appId;
	}

	public void setAppId(String appId) {
		this.appId = appId;
	}

	public String getSender() {
		return sender;
	}

	public void setSender(String sender) {
		this.sender = sender;
	}

	public List<String> getReceiver() {
		return receiver;
	}

	public void setReceiver(List<String> receiver) {
		this.receiver = receiver;
	}

	public String getGroupId() {
		return groupId;
	}

	public void setGroupId(String groupId) {
		this.groupId = groupId;
	}

	public Integer getMsgType() {
		return msgType;
	}

	public void setMsgType(Integer msgType) {
		this.msgType = msgType;
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

	public String getMsgFileUrl() {
		return msgFileUrl;
	}

	public void setMsgFileUrl(String msgFileUrl) {
		this.msgFileUrl = msgFileUrl;
	}

}
