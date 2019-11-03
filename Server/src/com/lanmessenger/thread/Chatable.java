package com.lanmessenger.thread;

import com.lanmessenger.messages.Message;
import com.lanmessenger.messages.Messaging;
import com.lanmessenger.users.User;

import java.net.Socket;
import java.util.Collection;
import java.util.Map;

public interface Chatable {
    void addNewObserver(User user,Sendable sender);
    void addObserver(Sendable sender);
    void addMessage(Messaging message);
    void removeObserver(User sender);
    void addUserAndSocketToMap(User user, Socket socket);
    void removeSendedMessages();
    void update();

    Collection<Messaging> getMessageToSend();
    Collection<Socket> getUsersToSend(User user);
    Collection<User> getUserList();
    Collection<User> getUsersFromSendable();
    Map<User, Sendable> getSendableMap();
    Collection<Sendable> getSendable();
}
