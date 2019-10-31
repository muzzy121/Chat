package com.lanmessenger.messages;

import com.lanmessenger.thread.Listener;
import com.lanmessenger.users.User;

public class Hello extends Command{
    @Override
    public void phrase(Listener listener) {
        System.out.println("Test Hello");
    }
    public Hello(User user) {
        super(user);
    }



}