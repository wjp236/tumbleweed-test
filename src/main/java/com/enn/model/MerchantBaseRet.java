/**
 * CopyRight 2015 必拓电子商务有限公司
 */
package com.enn.model;

import com.google.gson.annotations.SerializedName;

import java.io.Serializable;

/**
 * 账户对外接口统一返回模板类
 * 
 * @author wupeng<wupengg@enn.com>
 *
 * @param <T>
 *            T为返回结果类型
 */
public class MerchantBaseRet<T> implements Serializable {
	private static final long serialVersionUID = 1L;
	/** 返回编码 */
	@SerializedName("ret_code")
	private String retCode;
	/** 返回消息 */
	@SerializedName("ret_msg")
	private String retMsg;
	/** 商户号 */
	@SerializedName("merc_id")
	private String merchantId;
	/** 随机字符串 */
	@SerializedName("salt")
	private String salt;
	/** 签名 */
	@SerializedName("sign")
	private String sign;
	/** 返回结果 */
	@SerializedName("result")
	private T result;

	public MerchantBaseRet() {
		super();
	}

	public MerchantBaseRet(String retCode, String retMsg) {
		super();
		this.retCode = retCode;
		this.retMsg = retMsg;
	}

	public MerchantBaseRet(String retCode, String retMsg, T result) {
		super();
		this.retCode = retCode;
		this.retMsg = retMsg;
		this.result = result;
	}

	public String getRetCode() {
		return retCode;
	}

	public void setRetCode(String retCode) {
		this.retCode = retCode;
	}

	public String getRetMsg() {
		return retMsg;
	}

	public void setRetMsg(String retMsg) {
		this.retMsg = retMsg;
	}

	public T getResult() {
		return result;
	}

	public void setResult(T result) {
		this.result = result;
	}

	/**
	 * @return the merchantId
	 */
	public String getMerchantId() {
		return merchantId;
	}

	/**
	 * @param merchantId
	 *            the merchantId to set
	 */
	public void setMerchantId(String merchantId) {
		this.merchantId = merchantId;
	}

	/**
	 * @return the salt
	 */
	public String getSalt() {
		return salt;
	}

	/**
	 * @param salt
	 *            the salt to set
	 */
	public void setSalt(String salt) {
		this.salt = salt;
	}

	/**
	 * @return the sign
	 */
	public String getSign() {
		return sign;
	}

	/**
	 * @param sign
	 *            the sign to set
	 */
	public void setSign(String sign) {
		this.sign = sign;
	}

	/*
	 * (non-Javadoc)
	 * 
	 * @see java.lang.Object#toString()
	 */
	@Override
	public String toString() {
		return "MerchantBaseRet [retCode=" + retCode + ", retMsg=" + retMsg
				+ ", merchantId=" + merchantId + ", salt=" + salt + ", sign="
				+ sign + ", result=" + result + "]";
	}

}
