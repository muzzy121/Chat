package com.lanmessenger.thread;

import com.lanmessenger.messages.Message;
import com.lanmessenger.messages.Messaging;
import com.lanmessenger.users.User;

import java.net.Socket;
import java.util.Arrays;
import java.util.LinkedList;
import java.util.List;
import java.util.Map;

public class ChatRoom implements Chatable {
    private List<Messaging> messageList = new LinkedList<>();
    private List<Messaging> toSendMesseges = new LinkedList<>();
    private Map<User, Socket> userSocketMap;
    private Socket socket;
    private List<Sendable> sendableList = new LinkedList<>();
    private boolean state = false;
    private User user;

    public ChatRoom(User user){
        this.user = user;
    }
    public ChatRoom setState(boolean state) {
        this.state = state;
        return this;
    }
    public void addMessage(Messaging message){
        toSendMesseges.add(message);
    }

    public void displayMessages() {
        System.out.println(Arrays.toString(messageList.toArray()));
    }
    public List<Messaging> getMessageToSend(){
            return toSendMesseges;
        }
    public void moveMessageToList(){
        messageList.addAll(getMessageToSend());
        toSendMesseges.clear();
    }

    public Socket getSocket() {
        return socket;
    }

    @Override
    public void addObserver(Sendable sender) {
        sendableList.add(sender);
    }

    @Override
    public void removeObserver(Sendable sender) {
        sendableList.remove(sender);
    }

    @Override
    public void setSocket(Socket socket) {
        this.socket = socket;
    }

    @Override
    public void update() {
        for (Sendable sender : sendableList) {
            sender.update();
        }
    }

    @Override
    public User getUser() {
        return this.user;
    }


}
