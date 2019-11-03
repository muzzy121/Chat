package com.lanmessenger.messages;


import com.lanmessenger.thread.Chatable;
import com.lanmessenger.thread.Listener;
import com.lanmessenger.thread.Sendable;
import com.lanmessenger.users.User;

import java.io.IOException;
import java.io.Serializable;
import java.util.Collection;

public class Bye implements Messaging, Serializable {
    private User user;

    public Bye(User user) {
        this.user = user;
    }

    @Override
    public void phrase(Listener listener) {
        try {
            System.out.println("");
            System.out.println("Disconnected from server!");
            listener.stop();
            listener.getSocket().close();


        } catch (IOException e) {
            System.out.println("Pipa");
//            e.printStackTrace();
        }
    }

    @Override
    public void printMessage() {

    }

    @Override
    public User getUser() {
        return null;
    }

    @Override
    public Collection<Sendable> getUsersToSend(User user, Chatable chatRoom) {
        return null;
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
