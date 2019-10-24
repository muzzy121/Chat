package com.lanmessenger.thread;

import com.lanmessenger.messages.Message;
import com.lanmessenger.users.User;

import java.net.Socket;
import java.util.*;

public class ChatRoom implements Chatable {
    private List<Message> messageList = new LinkedList<>();
    private List<Message> toSendMesseges = new LinkedList<>();
    private Map<User, Socket> userSocketMap = new HashMap<>();

    private Socket socket;
    private List<Sendable> sendableList = new LinkedList<>();
    private boolean state = false;

    public ChatRoom(){

    }
    public ChatRoom setState(boolean state) {
        this.state = state;
        return this;
    }
    public void addMessage(Message message){
        getMessageToSend().add(message);
    }
    public List<Message> getMessageToSend(){
        return toSendMesseges;
    }
    public void moveMessageToList(){
        messageList.addAll(getMessageToSend());
        toSendMesseges.clear();
    }
    public void displayMessages() {
        System.out.println(Arrays.toString(messageList.toArray()));
    }

    public Socket getSocket() {
        return socket;
    }
    public void addUserAndSocketToMap(Socket socket, User user){
        userSocketMap.put(user,socket);
    }
//    public void addUser(User user){
//        userList.add(user);
//    }
    public Collection<Socket> getUserList() {
         return userSocketMap.values();
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


}
