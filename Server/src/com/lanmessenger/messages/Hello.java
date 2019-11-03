package com.lanmessenger.messages;

import com.lanmessenger.thread.*;
import com.lanmessenger.users.User;

import java.net.Socket;
import java.util.Arrays;
import java.util.Collection;

public class Hello extends Command {

    @Override
    public void phrase(Listener listener) {
        User user = getUser();
        Chatable chatRoom = listener.getChatRoom();
        Socket socket = listener.getSocket();

        if (!chatRoom.getUsersFromSendable().isEmpty()) {
            for (User u : chatRoom.getUsersFromSendable()) {
                if (!user.equals(u)) {
                    Sendable sender = new UpdatedSender(chatRoom, socket, user);
                    chatRoom.addNewObserver(user, sender);
                } else {
                    System.out.println(u.getUsername());
                    System.out.println(getUser().getUsername());
                    System.out.println("User already exist!");
                    return;
                }
            }
        } else {
            Sendable sender = new UpdatedSender(chatRoom, socket, user);
            chatRoom.addNewObserver(user, sender);
            System.out.println("New user has Arrived: " + user.getUsername());
        }
        System.out.println(Arrays.toString(chatRoom.getUsersFromSendable().toArray()));
    }

    @Override
    public Collection<Sendable> getUsersToSend(User user, Chatable chatRoom) {
        return null;
    }

    public Hello(User user) {
        super(user);
    }
}



























