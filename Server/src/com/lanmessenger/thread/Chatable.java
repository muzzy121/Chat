package com.lanmessenger.thread;

import java.net.Socket;

public interface Chatable {
    public void addUser(Sendable sender);
    public void removeUser(Sendable sender);
    public void addSocket(Socket socket);
    public void update();

}
