package com.lanmessenger.thread;

import java.net.InetAddress;
import java.util.HashMap;
import java.util.Map;

public class ServerList {
    private Map<String, InetAddress> serverList = new HashMap<>();

    public Map<String, InetAddress> getServerList() {
        return serverList;
    }

    public ServerList setServerList(Map<String, InetAddress> serverList) {
        this.serverList = serverList;
        return this;
    }

    public void put(String string, InetAddress inetAddress) {
        serverList.put(string,inetAddress);
    }
}
