package com.lanmessenger.thread;

import com.lanmessenger.Main;
import com.lanmessenger.messages.Message;
import com.lanmessenger.messages.Messaging;
import com.lanmessenger.users.User;
import com.sun.istack.internal.NotNull;

import java.awt.*;
import java.io.IOException;
import java.net.InetAddress;
import java.net.InetSocketAddress;
import java.net.Socket;
import java.util.Arrays;
import java.util.LinkedList;
import java.util.List;
import java.util.Map;

public class ChatRoom implements Chatable {
    private List<Messaging> messageList = new LinkedList<>();
    private List<Messaging> toSendMesseges = new LinkedList<>();
    private Socket socket = new Socket();
    private List<Sendable> sendableList = new LinkedList<>();
    private boolean state = false;
    private User user;

    public ChatRoom(User user) {
        this.user = user;
//        this.socket = socket;
    }

    public ChatRoom setState(boolean state) {
        this.state = state;
        return this;
    }

    @Override
    public void connect(InetAddress inetAddress) {
        try {
            InetSocketAddress inetSocketAddress = new InetSocketAddress(inetAddress, Main.PORT);
            //System.out.println(Main.serverAddress);
            if (socket.isClosed()) {
                this.socket = new Socket();
            }
            if (!socket.isConnected() || socket.isClosed()) {
                socket.connect(inetSocketAddress);
                System.out.println("Connected to: " + socket.getInetAddress().getHostName());
                Listener listener = new Listener(socket, this);
                new Thread(listener).start();
            } else {
                System.out.println("Already connected!");
            }
        } catch (IOException e) {
            e.printStackTrace();
            System.out.println("Unable to connect to " + inetAddress.getHostName());
        }
    }

    public void addMessage(Messaging message) {
        toSendMesseges.add(message);
    }


    public List<Messaging> getMessageToSend() {
        return toSendMesseges;
    }

    public void moveMessageToList() {
        messageList.addAll(getMessageToSend());
        toSendMesseges.clear();
    }

    public Socket getSocket() {
        return socket;
    }

    @Override
    public void printSendedMessages() {
        for (Messaging message : messageList
        ) {
            message.printMessage();
        }
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
