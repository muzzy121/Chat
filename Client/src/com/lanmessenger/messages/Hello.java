package com.lanmessenger.messages;

import com.lanmessenger.thread.Chatable;
import com.lanmessenger.thread.Listener;
import com.lanmessenger.thread.Sendable;
import com.lanmessenger.users.User;

import java.util.Collection;

public class Hello extends Command{
    @Override
    public void phrase(Listener listener) {
        System.out.println("Test Hello");
    }

    @Override
    public Collection<Sendable> getUsersToSend(User user, Chatable chatRoom) {
        return null;
    }

    public Hello(User user) {
        super(user);
    }



}