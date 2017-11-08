package com.tumbleweed.test.base.proxy.spring;

/**
 * 描述: RailwayStation 实现 TicketService
 *
 * @author: mylover
 * @Time: 08/11/2017.
 */
public class RailwayStation implements TicketService {

    public void sellTicket(){
        System.out.println("售票............");
    }

    public void inquire() {
        System.out.println("问询.............");
    }

    public void withdraw() {
        System.out.println("退票.............");
    }
}
