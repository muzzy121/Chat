package com.lanmessenger.messages;

import com.lanmessenger.thread.Chatable;
import com.lanmessenger.users.User;

import java.net.Socket;

public class Hello extends Command{
    @Override
    public void phrase(Chatable chatRoom, Socket socket) {
        System.out.println("Test Hello");
    }
    public Hello(User user) {
        super(user);
    }



}