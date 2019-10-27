package com.lanmessenger.thread;

import com.lanmessenger.messages.Message;
import com.lanmessenger.users.User;

import java.net.Socket;
import java.util.*;
import java.util.stream.Collectors;

public class ChatRoom implements Chatable {
    private List<Message> messageList = new LinkedList<>();
    private List<Message> toSendMesseges = new LinkedList<>();
    private Map<User,Socket> userSocketMap = new HashMap<>();
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

//    public Socket getSocket() {
//        return socket;
//    }
    public void addUserAndSocketToMap(User user, Socket socket){
        userSocketMap.put(user,socket);
    }
//    public void addUser(User user){
//        userList.add(user);
//    }
    public Collection<User> getUserList() {
         return userSocketMap.keySet();
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
    public Collection<Socket> getUsersToSend(User user) {
        Set<Socket> result = new HashSet<>();
        Set<Map.Entry<User,Socket>> entrySet = userSocketMap.entrySet();
        result = entrySet.stream()
                .filter(x -> !user.equals(x.getKey()))
                .map(y -> y.getValue())
                .collect(Collectors.toSet());


//        for (Map.Entry<Socket, User> entry : entrySet) {
//            message.send(entry.getValue().getSocket(), message.buildMessage(line, App.USER));
//        }
//        Collection<Socket>
//        for (User userFromMap: userSocketMap.keySet()
//             ) {
//            if(user.equals(userFromMap)) {
//
//            }
//        }
//        userSocketMap.
        System.out.println(result);
        return null;
    }

/*
    @Override
    public void setSocket(Socket socket) {
        this.socket = socket;
    }
*/

    @Override
    public void update() {
        for (Sendable sender : sendableList) {
            sender.update();
        }
    }


}
