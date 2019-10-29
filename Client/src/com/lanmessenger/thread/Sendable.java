package com.lanmessenger.thread;

import com.lanmessenger.users.User;

import java.net.Socket;

public interface Sendable {
    public void update();
    Socket getSocket();
    User getUser();

}
