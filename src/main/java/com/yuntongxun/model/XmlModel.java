package com.yuntongxun.model;

import javax.xml.bind.annotation.XmlRootElement;

/**
 * Created by mylover on 1/28/16.
 */
@XmlRootElement(name = "Response")
public class XmlModel {

    private XmlModelHead head;

    public XmlModelHead getHead() {
        return head;
    }

    public void setHead(XmlModelHead head) {
        this.head = head;
    }
}
