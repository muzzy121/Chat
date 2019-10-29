package com.lanmessenger.thread;

import com.lanmessenger.messages.Messaging;
import com.lanmessenger.users.User;

import java.net.Socket;

public interface Sendable {
    void update();
    void send(Messaging message);
    Socket getSocket();
    User getUser();

}
