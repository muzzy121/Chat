package com.lanmessenger.messages;

import com.lanmessenger.thread.Chatable;
import com.lanmessenger.users.User;

import java.io.Serializable;
import java.net.Socket;

public abstract class Command implements Messaging, Serializable {
    //    public String type;
    public User user;

    public Command(User user){
        this.user = user;
    };


    public User getUser() {
        return this.user;
    }

    @Override
    public void phrase(Chatable chatRoom, Socket socket) {

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
