package com.lanmessenger.thread;

import com.lanmessenger.messages.Messaging;
import com.lanmessenger.users.User;

import java.net.InetAddress;
import java.net.Socket;
import java.util.List;

public interface Chatable {
    void addObserver(Sendable sender);

    void removeObserver(Sendable sender);

    void setSocket(Socket socket);

    void update();

    void printSendedMessages();

    void moveMessageToList();

    void addMessage(Messaging message);

    void connect(InetAddress inetAddress);

    User getUser();

    Socket getSocket();

    List<Messaging> getMessageToSend();
}
