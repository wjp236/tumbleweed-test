package com.yuntongxun.model;

import javax.xml.bind.annotation.XmlAttribute;
import javax.xml.bind.annotation.XmlElement;
import javax.xml.bind.annotation.XmlElementWrapper;
import javax.xml.bind.annotation.XmlRootElement;
import java.util.List;

/**
 * Created by mylover on 1/13/16.
 */
@XmlRootElement(name = "ServerAddr")
public class ServerAddrs {
    private String version;
    private List<ServerAddrModel> Connector;
    private List<ServerAddrModel> LVS;
    private List<ServerAddrModel> FileServer;

    @XmlAttribute()
    public String getVersion() {
        return version;
    }

    public void setVersion(String version) {
        this.version = version;
    }

    @XmlElementWrapper(name = "Connector")
    @XmlElement(name = "server")
    public List<ServerAddrModel> getConnector() {
        return Connector;
    }

    public void setConnector(List<ServerAddrModel> connector) {
        Connector = connector;
    }

    @XmlElementWrapper(name = "LVS")
    @XmlElement(name = "server")
    public List<ServerAddrModel> getLVS() {
        return LVS;
    }

    public void setLVS(List<ServerAddrModel> LVS) {
        this.LVS = LVS;
    }

    @XmlElementWrapper(name = "FileServer")
    @XmlElement(name = "server")
    public List<ServerAddrModel> getFileServer() {
        return FileServer;
    }

    public void setFileServer(List<ServerAddrModel> fileServer) {
        FileServer = fileServer;
    }
}
