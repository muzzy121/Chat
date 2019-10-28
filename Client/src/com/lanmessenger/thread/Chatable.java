package com.lanmessenger.thread;

import com.lanmessenger.messages.Messaging;

import java.net.Socket;
import java.util.List;

public interface Chatable {
    public void addObserver(Sendable sender);
    public void removeObserver(Sendable sender);
    public void setSocket(Socket socket);
    public void update();

    Socket getSocket();

    List<Messaging> getMessageToSend();

    void moveMessageToList();

    void addMessage(Messaging message);
}
