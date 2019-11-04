package com.lanmessenger.thread;

import com.lanmessenger.messages.Messaging;
import com.lanmessenger.users.User;

import java.net.Socket;
import java.util.Collection;
import java.util.Map;

public interface Chatable {
    void addObserver(User user, Sendable sender);
    void removeObserver(User sender);
    void addMessage(Messaging message);
    void printSendedMessages();
    void removeSendedMessages();
    void update();

    Collection<Messaging> getMessageToSend();
    Collection<User> getUsersFromSendable();
    Map<User, Sendable> getSendableMap();
    User getUserBySocket(Socket socket);
}
