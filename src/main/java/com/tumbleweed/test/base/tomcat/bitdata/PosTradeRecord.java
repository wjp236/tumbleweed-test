package com.tumbleweed.test.base.tomcat.bitdata;

import java.util.List;

/**
 * 描述:
 *
 * @author: mylover
 * @Time: 07/12/2017.
 */
public class PosTradeRecord {


    private String orderId;
    private Long orderMoney;
    private long posNumber;
    private String shopName;
    private long tradeDate;
    private List<TradeProduct> products;

    public String getOrderId() {
        return orderId;
    }

    public void setOrderId(String orderId) {
        this.orderId = orderId;
    }

    public Long getOrderMoney() {
        return orderMoney;
    }

    public void setOrderMoney(Long orderMoney) {
        this.orderMoney = orderMoney;
    }

    public long getPosNumber() {
        return posNumber;
    }

    public void setPosNumber(long posNumber) {
        this.posNumber = posNumber;
    }

    public String getShopName() {
        return shopName;
    }

    public void setShopName(String shopName) {
        this.shopName = shopName;
    }

    public long getTradeDate() {
        return tradeDate;
    }

    public void setTradeDate(long tradeDate) {
        this.tradeDate = tradeDate;
    }

    public List<TradeProduct> getProducts() {
        return products;
    }

    public void setProducts(List<TradeProduct> products) {
        this.products = products;
    }
}
