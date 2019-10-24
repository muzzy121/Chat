package com.lanmessenger.messages;

import com.lanmessenger.thread.ChatRoom;
import com.lanmessenger.users.User;

import java.io.ObjectOutputStream;
import java.net.Socket;
import java.util.Arrays;

public class Hello extends Command {
    @Override
    public void phrase(ChatRoom chatRoom, Socket socket) {
        System.out.println(user);

        System.out.println(chatRoom.getUserList().equals(null));
        Object[] s = chatRoom.getUserList().toArray();
        System.out.println(Arrays.toString(s));
        System.out.println(s.length);
        if(!chatRoom.getUserList().isEmpty()) {
            for (Socket s : chatRoom.getUserList()) {
                System.out.println("do dodania: " + socket);
                System.out.println("z listy: " + s);
                System.out.println(socket.equals(s));
                if (!socket.equals(socket)) {
                    System.out.println("OK?");
                    chatRoom.addUserAndSocketToMap(socket, user);
                }


        }
        System.out.println(Arrays.toString(chatRoom.getUserList().toArray()));
    }

    public Hello(User user) {
        super(user);
    }

    @Override
    public void getUser() {
        System.out.println(user);
    }
}