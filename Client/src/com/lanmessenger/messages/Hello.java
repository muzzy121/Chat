package com.lanmessenger.messages;

import com.lanmessenger.thread.ChatRoom;
import com.lanmessenger.users.User;

import java.net.Socket;

public class Hello extends Command{
    @Override
    public void phrase(ChatRoom chatRoom, Socket socket) {
        System.out.println("Test Hello");
    }
    public Hello(User user) {
        super(user);
    }



    @Override
    public void getUser() {
        System.out.println(user);
    }
}