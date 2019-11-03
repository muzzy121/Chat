package com.lanmessenger.thread;

import com.lanmessenger.messages.Messaging;
import com.lanmessenger.users.User;

import java.net.Socket;
import java.util.Collection;

public interface Sendable {
    void update();
    void send(Messaging message);
    Socket getSocket();
    User getUser();


}
