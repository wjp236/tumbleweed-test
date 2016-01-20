package wang.tumbleweed.model;

import javax.xml.bind.annotation.XmlRootElement;

@XmlRootElement(name = "MultiPartyCall")
public class MultiPartyCallForm {

    String appId;
    String joinurl;
    String quiturl;
    String dtmfreporturl;
    String notifymember;
    String callId;
    String maxMember;
    int shortConf;
    MultiPartyCallMemberListForm members;

    public String getAppId() {
        return appId;
    }

    public void setAppId(String appId) {
        this.appId = appId;
    }

    public String getJoinurl() {
        return joinurl;
    }

    public void setJoinurl(String joinurl) {
        this.joinurl = joinurl;
    }

    public String getQuiturl() {
        return quiturl;
    }

    public void setQuiturl(String quiturl) {
        this.quiturl = quiturl;
    }

    public String getDtmfreporturl() {
        return dtmfreporturl;
    }

    public void setDtmfreporturl(String dtmfreporturl) {
        this.dtmfreporturl = dtmfreporturl;
    }

    public String getNotifymember() {
        return notifymember;
    }

    public void setNotifymember(String notifymember) {
        this.notifymember = notifymember;
    }

    public String getCallId() {
        return callId;
    }

    public void setCallId(String callId) {
        this.callId = callId;
    }

    public String getMaxMember() {
        return maxMember;
    }

    public void setMaxMember(String maxMember) {
        this.maxMember = maxMember;
    }

    public int getShortConf() {
        return shortConf;
    }

    public void setShortConf(int shortConf) {
        this.shortConf = shortConf;
    }

    public MultiPartyCallMemberListForm getMembers() {
        return members;
    }

    public void setMembers(MultiPartyCallMemberListForm members) {
        this.members = members;
    }


}
