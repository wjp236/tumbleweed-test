package com.tumbleweed.test.yuntongxun.model;

import java.util.List;

public class Connector {

	private List<ServerAddr> server;
	private String dc;
	private String connectorId;
	private String connectorAddr;
	private String remoteAddr;
	private String deviceNo;
	private String deviceType;

	/**
	 * @return the server
	 */
	public List<ServerAddr> getServer() {
		return server;
	}

	/**
	 * @param server the server to set
	 */
	public void setServer(List<ServerAddr> server) {
		this.server = server;
	}

	public String getDc() {
		return dc;
	}

	public void setDc(String dc) {
		this.dc = dc;
	}

	public String getConnectorId() {
		return connectorId;
	}

	public void setConnectorId(String connectorId) {
		this.connectorId = connectorId;
	}

	public String getConnectorAddr() {
		return connectorAddr;
	}

	public void setConnectorAddr(String connectorAddr) {
		this.connectorAddr = connectorAddr;
	}

	public String getRemoteAddr() {
		return remoteAddr;
	}

	public void setRemoteAddr(String remoteAddr) {
		this.remoteAddr = remoteAddr;
	}

	public String getDeviceNo() {
		return deviceNo;
	}

	public void setDeviceNo(String deviceNo) {
		this.deviceNo = deviceNo;
	}

	public String getDeviceType() {
		return deviceType;
	}

	public void setDeviceType(String deviceType) {
		this.deviceType = deviceType;
	}
}
