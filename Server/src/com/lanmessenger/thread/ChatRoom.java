package com.lanmessenger.thread;

import com.lanmessenger.messages.Message;
import com.lanmessenger.messages.Messaging;
import com.lanmessenger.users.User;

import java.net.Socket;
import java.util.*;
import java.util.stream.Collectors;

public class ChatRoom implements Chatable {
    private List<Messaging> messageList = new LinkedList<>();
    private List<Messaging> toSendMesseges = new LinkedList<>();
    private Map<User, Sendable> userSendableMap = new HashMap<>();
    private boolean state = false;

    public ChatRoom() {

    }

    public ChatRoom setState(boolean state) {
        this.state = state;
        return this;
    }

    @Override
    public void addMessage(Messaging message) {
        getMessageToSend().add(message);
    }

    @Override
    public void printSendedMessages() {
        for (Messaging message : messageList
        ) {
            message.printMessage();
        }
    }

    @Override
    public Collection<Messaging> getMessageToSend() {
        return toSendMesseges;
    }

    @Override
    public void removeSendedMessages() {
        messageList.addAll(getMessageToSend());
        toSendMesseges.clear();
    }

    @Override
    public Map<User, Sendable> getSendableMap() {
        return userSendableMap;
    }

    @Override
    public Collection<User> getUsersFromSendable() {
        return userSendableMap.keySet();
    }

    @Override
    public void addObserver(User user, Sendable sender) {
        userSendableMap.put(user, sender);
    }

    @Override
    public void removeObserver(User sender) {
        userSendableMap.remove(sender);
    }

    @Override
    public void update() {
        for (Sendable sender : userSendableMap.values()) {
            sender.update();
        }
        removeSendedMessages();
    }

    public User getUserBySocket(Socket socket) {
        Optional<User> result;
        Set<Map.Entry<User, Sendable>> entrySet = userSendableMap.entrySet();
        result = entrySet.stream()
                .filter(x -> socket.equals(x.getValue().getSocket()))
                .findFirst()
                .map(z -> z.getKey());

        if (result.isPresent()) {
            return result.get();
        } else {
            return null;
        }
    }
}
