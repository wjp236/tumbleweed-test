package com.yuntongxun.model;

import java.math.BigDecimal;
import java.util.Date;

public class CCPAccount {

	private String accountSid;
	private String friendlyName;
	private int type;
	private int status;
	private String authToken;
	private String uri;
	private BigDecimal balance;
	private BigDecimal subbalance;
	private Date dateCreated;
	private Date dateUpdated;
	private int rest_call_num;
	private String channel_id;
	private int con_nums;
	
	//语音验证码，0：无限制，1：此功能已下线
	private int voiceVerifyState;
	
	//0：无限制，1：双向回呼已下线
	private int callbackState;
	
	//0:无限制,1：营销外呼已下线
	private int landingCallsState;
	
	//0:无限制，1：发送短信（下行）已经下线
	private int messagesState;
	
	//0:无限制，1：ivr外呼功能已下线
	private int ivrDialState;
	
	//0:无限制，1：发送模板短信已下线
	private int templateSmsState;

	private String appId; //兼容redis命名

	private String appStatus;

	private String voipId;

	private String thrTestNum;//兼容redis命名

	private BigDecimal totalRecharge;

	private int identityType;

	private int ratetype;

	public CCPAccount() {

	}

	public String getChannel_id() {
		return channel_id;
	}

	public void setChannel_id(String channelId) {
		channel_id = channelId;
	}

	public String getAccountSid() {
		return accountSid;
	}

	public void setAccountSid(String accountSid) {
		this.accountSid = accountSid;
	}

	public String getFriendlyName() {
		return friendlyName;
	}

	public void setFriendlyName(String friendlyName) {
		this.friendlyName = friendlyName;
	}

	public int getType() {
		return type;
	}

	public void setType(int type) {
		this.type = type;
	}

	public int getStatus() {
		return status;
	}

	public void setStatus(int status) {
		this.status = status;
	}

	public String getAuthToken() {
		return authToken;
	}

	public void setAuthToken(String authToken) {
		this.authToken = authToken;
	}

	public String getUri() {
		return uri;
	}

	public void setUri(String uri) {
		this.uri = uri;
	}

	public BigDecimal getBalance() {
		return balance;
	}

	public void setBalance(BigDecimal balance) {
		this.balance = balance;
	}

	public BigDecimal getSubbalance() {
		return subbalance;
	}

	public void setSubbalance(BigDecimal subbalance) {
		this.subbalance = subbalance;
	}

	public Date getDateCreated() {
		return dateCreated;
	}

	public void setDateCreated(Date dateCreated) {
		this.dateCreated = dateCreated;
	}

	public Date getDateUpdated() {
		return dateUpdated;
	}

	public void setDateUpdated(Date dateUpdated) {
		this.dateUpdated = dateUpdated;
	}

	public int getRest_call_num() {
		return rest_call_num;
	}

	public void setRest_call_num(int rest_call_num) {
		this.rest_call_num = rest_call_num;
	}

	public int getCon_nums() {
		return con_nums;
	}

	public void setCon_nums(int con_nums) {
		this.con_nums = con_nums;
	}

	public int getVoiceVerifyState() {
		return voiceVerifyState;
	}

	public void setVoiceVerifyState(int voiceVerifyState) {
		this.voiceVerifyState = voiceVerifyState;
	}

	
	public int getCallbackState() {
		return callbackState;
	}

	public void setCallbackState(int callbackState) {
		this.callbackState = callbackState;
	}

	public int getLandingCallsState() {
		return landingCallsState;
	}

	public void setLandingCallsState(int landingCallsState) {
		this.landingCallsState = landingCallsState;
	}

	public int getMessagesState() {
		return messagesState;
	}

	public void setMessagesState(int messagesState) {
		this.messagesState = messagesState;
	}

	public int getIvrDialState() {
		return ivrDialState;
	}

	public void setIvrDialState(int ivrDialState) {
		this.ivrDialState = ivrDialState;
	}

	public int getTemplateSmsState() {
		return templateSmsState;
	}

	public void setTemplateSmsState(int templateSmsState) {
		this.templateSmsState = templateSmsState;
	}

	public String getAppId() {
		return appId;
	}

	public void setAppId(String appId) {
		this.appId = appId;
	}

	public String getAppStatus() {
		return appStatus;
	}

	public void setAppStatus(String appStatus) {
		this.appStatus = appStatus;
	}

	public String getVoipId() {
		return voipId;
	}

	public void setVoipId(String voipId) {
		this.voipId = voipId;
	}

	public String getThrTestNum() {
		return thrTestNum;
	}

	public void setThrTestNum(String thrTestNum) {
		this.thrTestNum = thrTestNum;
	}

	public BigDecimal getTotalRecharge() {
		return totalRecharge;
	}

	public void setTotalRecharge(BigDecimal totalRecharge) {
		this.totalRecharge = totalRecharge;
	}

	public int getIdentityType() {
		return identityType;
	}

	public void setIdentityType(int identityType) {
		this.identityType = identityType;
	}

	public int getRatetype() {
		return ratetype;
	}

	public void setRatetype(int ratetype) {
		this.ratetype = ratetype;
	}
}
