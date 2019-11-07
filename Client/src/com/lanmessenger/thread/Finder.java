package com.lanmessenger.thread;

public class Finder implements Runnable{
    private boolean isStart = true;

    public void stop() {
        this.isStart = false;
    }
    @Override
    public void run() {

    }


    // TODO: 2019-11-07 Class which allows me to find server in LAN Network
}
