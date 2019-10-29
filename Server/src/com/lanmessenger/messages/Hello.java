package com.lanmessenger.messages;

import com.lanmessenger.thread.Chatable;
import com.lanmessenger.thread.Sendable;
import com.lanmessenger.thread.UpdatedSender;
import com.lanmessenger.users.User;
import java.net.Socket;
import java.util.Arrays;

public class Hello extends Command {

    @Override
    public void phrase(Chatable chatRoom, Socket socket) {
        User user = getUser();
        if (!chatRoom.getUserList().isEmpty()) {

            for (User u : chatRoom.getUserList()) {
                if (!user.equals(u)) {
                    chatRoom.addUserAndSocketToMap(user, socket);
                    Sendable sender = new UpdatedSender(chatRoom, socket, user);
                    chatRoom.addObserver(sender);
                } else {
                    System.out.println(u.getUsername());
                    System.out.println(getUser().getUsername());
                      System.out.println("User already exist!");
                    return;
                }
            }
        } else {
            chatRoom.addUserAndSocketToMap(user, socket);
            Sendable sender = new UpdatedSender(chatRoom, socket, user);
            chatRoom.addObserver(sender);
            System.out.println("New user has Arrived: " + user.getUsername());
        }
        System.out.println(Arrays.toString(chatRoom.getUserList().toArray()));
    }
    public Hello(User user) {
        super(user);
    }

}