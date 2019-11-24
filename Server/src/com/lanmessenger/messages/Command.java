package com.lanmessenger.messages;

import com.lanmessenger.users.User;

import java.io.Serializable;

public abstract class Command implements Messaging, Serializable {
    public User user;

    public Command(User user){
        this.user = user;
    };


    public User getUser() {
        return this.user;
    }

    @Override
    public void printMessage() {

    }
}
