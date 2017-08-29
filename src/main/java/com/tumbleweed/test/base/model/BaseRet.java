/**
 * CopyRight 2015 必拓电子商务有限公司
 */
package com.tumbleweed.test.base.model;

import java.io.Serializable;

/**
 *  基本返回对象
 *  BaseRet 此处填写需要参考的类
 * @version 2015年8月20日 下午4:06:11
 * @author wupeng<wupengg@enn.com>
 */
public class BaseRet<T> implements Serializable {
	private static final long serialVersionUID = 1L;
	/** 返回编码 */
	private String retCode;
	/** 返回消息 */
	private String retMsg;
	/** 返回结果 */
	private T result;

	public BaseRet() {
		super();
	}

	public BaseRet(String retCode, String retMsg) {
		super();
		this.retCode = retCode;
		this.retMsg = retMsg;
	}

	public BaseRet(String retCode, String retMsg, T result) {
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

	@Override
	public String toString() {
		StringBuilder builder = new StringBuilder();
		builder.append("BaseRet [retCode=");
		builder.append(retCode);
		builder.append(", retMsg=");
		builder.append(retMsg);
		builder.append(", result=");
		builder.append(result);
		builder.append("]");
		return builder.toString();
	}

}
