package com.lanmessenger.thread;

import java.net.Socket;

public interface Chatable {
    public void addObserver(Sendable sender);
    public void removeObserver(Sendable sender);
    public void setSocket(Socket socket);
    public void update();

}
