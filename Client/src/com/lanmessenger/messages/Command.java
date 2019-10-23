package com.lanmessenger.messages;

import com.lanmessenger.users.User;

import java.io.Serializable;

public class Command implements Messaging, Serializable {
    public String type;
    public User user;

    public Command(String type) {
        this.type = type;
    }

    @Override
    public void dupa() {

    }

    @Override
    public void printMessage() {

    }

    @Override
    public boolean isRecived() {
        return false;
    }

    @Override
    public Message setRecived(boolean recived) {
        return null;
    }

    @Override
    public boolean isSended() {
        return false;
    }

    @Override
    public Message setSended(boolean sended) {
        return null;
    }
}
