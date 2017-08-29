/**
 * 
 */
package com.tumbleweed.test.yuntongxun.model;

import org.ming.sample.util.ProtocolUtil;

import javax.xml.bind.annotation.XmlRootElement;

/**
 * @author chao
 */
@XmlRootElement(name = "Response")
public class Response {

	private String statusCode;
	private String statusMsg;

	public Response() {
	}

	public Response(String statuscode, String statusmsg) {
		super();
		this.statusCode = statuscode;
		this.statusMsg = statusmsg;
	}

	public boolean isSuccess() {
		return "000000".equals(this.statusCode);
	}

	public String getBody() {
		return ProtocolUtil.buildSimpleBody(this.statusCode, this.statusMsg);
	}
	
	/**
	 * @return the statusCode
	 */
	public String getStatusCode() {
		return statusCode;
	}

	/**
	 * @param statusCode the statusCode to set
	 */
	public void setStatusCode(String statusCode) {
		this.statusCode = statusCode;
	}

	/**
	 * @return the statusMsg
	 */
	public String getStatusMsg() {
		return statusMsg;
	}

	/**
	 * @param statusMsg the statusMsg to set
	 */
	public void setStatusMsg(String statusMsg) {
		this.statusMsg = statusMsg;
	}

}
