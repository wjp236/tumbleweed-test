package wang.tumbleweed.model;

import javax.xml.bind.annotation.XmlElement;

/**
 * Created by mylover on 1/28/16.
 */
public class XmlModelHead {

    private String statuscode;
    private CallBack callBack;

    public String getStatuscode() {
        return statuscode;
    }

    public void setStatuscode(String statuscode) {
        this.statuscode = statuscode;
    }

    @XmlElement(name = "CallBack")
    public CallBack getCallBack() {
        return callBack;
    }

    public void setCallBack(CallBack callBack) {
        this.callBack = callBack;
    }
}
