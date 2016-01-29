package wang.tumbleweed.model;

import javax.xml.bind.annotation.XmlElement;

/**
 * Created by mylover on 1/28/16.
 */
public class CallBack {
    private String callSid;
    private String dateCreated;

    @XmlElement(name = "callSid")
    public String getCallSid() {
        return callSid;
    }

    public void setCallSid(String callSid) {
        this.callSid = callSid;
    }

    @XmlElement(name = "dateCreated")
    public String getDateCreated() {
        return dateCreated;
    }

    public void setDateCreated(String dateCreated) {
        this.dateCreated = dateCreated;
    }
}
