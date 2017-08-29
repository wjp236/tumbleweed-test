package com.tumbleweed.test.enn.model;

import java.io.Serializable;

/**
 * Created by mylover on 7/27/16.
 */
public class XinyipayPo implements Serializable {

    private String requestId;
    private String appId;
    private String accountDate;
    private String currency;
    private double tradeAmount;
    private double privilegePrice;
    private double xinyiShell;
    private double cash;
    private String payChannel;
    private String type;// 1 收款 2 退款

    public String getAppId() {
        return appId;
    }

    public void setAppId(String appId) {
        this.appId = appId;
    }

    public String getAccountDate() {
        return accountDate;
    }

    public void setAccountDate(String accountDate) {
        this.accountDate = accountDate;
    }

    public String getCurrency() {
        return currency;
    }

    public void setCurrency(String currency) {
        this.currency = currency;
    }

    public double getTradeAmount() {
        return tradeAmount;
    }

    public void setTradeAmount(double tradeAmount) {
        this.tradeAmount = tradeAmount;
    }

    public double getPrivilegePrice() {
        return privilegePrice;
    }

    public void setPrivilegePrice(double privilegePrice) {
        this.privilegePrice = privilegePrice;
    }

    public double getXinyiShell() {
        return xinyiShell;
    }

    public void setXinyiShell(double xinyiShell) {
        this.xinyiShell = xinyiShell;
    }

    public double getCash() {
        return cash;
    }

    public void setCash(double cash) {
        this.cash = cash;
    }

    public String getPayChannel() {
        return payChannel;
    }

    public void setPayChannel(String payChannel) {
        this.payChannel = payChannel;
    }

    public String getType() {
        return type;
    }

    public void setType(String type) {
        this.type = type;
    }

    public String getRequestId() {
        return requestId;
    }

    public void setRequestId(String requestId) {
        this.requestId = requestId;
    }

    @Override
    public String toString() {
        return "XinyipayPo{" +
                "requestId='" + requestId + '\'' +
                ", appId='" + appId + '\'' +
                ", accountDate='" + accountDate + '\'' +
                ", currency='" + currency + '\'' +
                ", tradeAmount=" + tradeAmount +
                ", privilegePrice=" + privilegePrice +
                ", xinyiShell=" + xinyiShell +
                ", cash=" + cash +
                ", payChannel='" + payChannel + '\'' +
                ", type='" + type + '\'' +
                '}';
    }
}
