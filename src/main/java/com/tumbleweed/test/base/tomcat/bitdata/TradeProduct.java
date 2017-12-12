package com.tumbleweed.test.base.tomcat.bitdata;

/**
 * 描述:
 *
 * @author: mylover
 * @Time: 07/12/2017.
 */
public class TradeProduct {

    private int id;
    private String name;
    private String code;
    private int price;


    public TradeProduct(int id, String name, String code, int price) {
        this.id = id;
        this.name = name;
        this.code = code;
        this.price = price;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getCode() {
        return code;
    }

    public void setCode(String code) {
        this.code = code;
    }

    public int getPrice() {
        return price;
    }

    public void setPrice(int price) {
        this.price = price;
    }
}
