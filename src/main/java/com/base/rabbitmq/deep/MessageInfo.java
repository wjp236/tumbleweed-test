package com.base.rabbitmq.deep;

import java.io.Serializable;

/**
 * Created by mylover on 29/11/2016.
 */
public class MessageInfo implements Serializable {

    //渠道
    private String channel;
    //来源
    private String content;

    public String getChannel() {
        return channel;
    }
    public void setChannel(String channel) {
        this.channel = channel;
    }
    public String getContent() {
        return content;
    }
    public void setContent(String content) {
        this.content = content;
    }
}
