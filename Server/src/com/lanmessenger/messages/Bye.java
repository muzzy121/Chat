package com.lanmessenger.messages;

import com.lanmessenger.thread.Listener;
import com.lanmessenger.users.User;

import java.io.IOException;
import java.io.Serializable;

// TODO: 2019-11-23 Change implementation on Client side
public class Bye extends Command implements Serializable{

    public Bye(User user) {
        super(user);
    }

    @Override
    public void phrase(Listener listener) {
        try {
            listener.getSocket().close();

        } catch (IOException e) {
            System.out.println("Can't close socket");
        }
    }

}
