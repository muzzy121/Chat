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
    private Map<User, Socket> userSocketMap = new HashMap<>();
    private Map<User, Sendable> userSendableMap = new HashMap<>();
    private List<Sendable> sendableList = new LinkedList<>();
    private boolean state = false;

    public ChatRoom() {

    }

    public ChatRoom setState(boolean state) {
        this.state = state;
        return this;
    }

    public void addMessage(Messaging message) {
        getMessageToSend().add(message);
    }

    public Collection<Messaging> getMessageToSend() {
        return toSendMesseges;
    }

    public void removeSendedMessages() {
        messageList.addAll(getMessageToSend());
        toSendMesseges.clear();
    }

    public void addUserAndSocketToMap(User user, Socket socket) {
        userSocketMap.put(user, socket);
    }

    public Collection<User> getUserList() {
        return userSocketMap.keySet();
    }

    @Override
    public Collection<User> getUsersFromSendable() { return userSendableMap.keySet();}

    @Override
    public Collection<Sendable> getSendable() {
        return sendableList;
    }

    @Override
    public void addNewObserver(User user, Sendable sender) {
       userSendableMap.put(user, sender);
    }

    @Override
    public void addObserver(Sendable sender) {
        sendableList.add(sender);
    }

    @Override
    public void removeObserver(User sender) {
        userSendableMap.remove(sender);
        System.out.println("ilu: " + userSendableMap.size());
    }

    @Override
    public Collection<Socket> getUsersToSend(User user) {
        Set<Socket> result;
        Set<Map.Entry<User, Socket>> entrySet = userSocketMap.entrySet();
        result = entrySet.stream()
                .filter(x -> !user.equals(x.getKey()))
                .map(y -> y.getValue())
                .collect(Collectors.toSet());
        return result;
    }

    public Map<User, Sendable> getSendableMap() {
        return userSendableMap;
    }


    @Override
    public void update() {

        for (Sendable sender : userSendableMap.values()) {
//            System.out.println("Test");

            sender.update();
        }
        removeSendedMessages();
    }


}
