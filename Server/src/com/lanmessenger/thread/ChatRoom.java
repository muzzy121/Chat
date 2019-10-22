package com.lanmessenger.thread;

import com.lanmessenger.messages.Message;
import com.lanmessenger.users.User;

import java.net.Socket;
import java.util.Arrays;
import java.util.LinkedList;
import java.util.List;
import java.util.Map;

public class ChatRoom implements Chatable {
    private List<Message> messageList = new LinkedList<>();
    private Map<User, Socket> userSocketMap;
    private List<Sendable> sendableList = new LinkedList<>();
    private boolean state = false;

    public ChatRoom(){

    }
    public ChatRoom setState(boolean state) {
        this.state = state;
        return this;
    }
    public void addMessage(Message message){
        messageList.add(message);
    }
    public void displayMessages() {
        System.out.println(Arrays.toString(messageList.toArray()));
    }

    @Override
    public void addUser(Sendable sender) {
        sendableList.add(sender);
    }

    @Override
    public void removeUser(Sendable sender) {
        sendableList.remove(sender);
    }

    @Override
    public void update() {
        for (Sendable sender : sendableList) {
            sender.update();
        }
    }


}
