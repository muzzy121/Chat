package com.lanmessenger.messages;

import com.lanmessenger.thread.*;
import com.lanmessenger.users.User;

import java.net.Socket;
import java.util.Collection;

public class Hello extends Command {

    public Hello(User user) {
        super(user);
    }

    @Override
    public void phrase(Listener listener) {
        User user = getUser();
        Chatable chatRoom = listener.getChatRoom();
        Socket socket = listener.getSocket();

        if (!chatRoom.getUsersFromSendable().isEmpty()) {
            for (User u : chatRoom.getUsersFromSendable()) {
                if (!user.equals(u)) {
                    Sendable sender = new Sender(chatRoom, socket, user);
                    chatRoom.addObserver(user, sender);
                } else {
                    System.out.println("User already exist!");
                    return;
                }
            }
        } else {
            Sendable sender = new Sender(chatRoom, socket, user);
            chatRoom.addObserver(user, sender);
            System.out.println("New user connected: " + user.getUsername());
        }
    }
}



























