package com.tumbleweed.test.base.proxy.spring;

/**
 * 描述:售票服务
 *
 * @author: mylover
 * @Time: 08/11/2017.
 */
public interface TicketService {

    //售票
    public void sellTicket();

    //问询
    public void inquire();

    //退票
    public void withdraw();

}
