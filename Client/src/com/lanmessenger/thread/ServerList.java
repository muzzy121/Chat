package com.lanmessenger.thread;

import java.net.InetAddress;
import java.util.ArrayList;
import java.util.List;

public class ServerList {
    private List<InetAddress> serverList = new ArrayList<>();


    public List<InetAddress> getServerList() {
        return serverList;
    }

    public void add(InetAddress inetAddress) {
        serverList.add(inetAddress);
    }
}
