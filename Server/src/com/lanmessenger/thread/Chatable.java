package com.lanmessenger.thread;

import com.lanmessenger.messages.Message;
import com.lanmessenger.users.User;

import java.net.Socket;
import java.util.Collection;
import java.util.List;

public interface Chatable {
    void addObserver(Sendable sender);
    void addMessage(Message message);
    void removeObserver(Sendable sender);
    void addUserAndSocketToMap(User user, Socket socket);
    void removeSendedMessages();
    void update();

    Collection<Message> getMessageToSend();
    Collection<Socket> getUsersToSend(User user);
    Collection<User> getUserList();
    Collection<Sendable> getSendable();
}
