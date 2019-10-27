package com.lanmessenger.thread;

import com.lanmessenger.users.User;

import java.net.Socket;
import java.util.Collection;

public interface Chatable {
    void addObserver(Sendable sender);
    void removeObserver(Sendable sender);
//    public void setSocket(Socket socket);
    Collection<Socket> getUsersToSend(User user);
    void update();

}
