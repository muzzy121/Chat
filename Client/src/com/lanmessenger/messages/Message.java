package com.lanmessenger.messages;


import com.lanmessenger.thread.Chatable;
import com.lanmessenger.thread.Listener;
import com.lanmessenger.thread.Sendable;
import com.lanmessenger.users.User;
import java.io.Serializable;
import java.text.SimpleDateFormat;
import java.util.Collection;
import java.util.Date;

public class Message implements Serializable, Messaging {
    private Date date;
    private String text;
    private User user;

    public Message(String text, User user) {
        this.date = new Date();
        this.text = text;
        this.user = user;
    }

    public User getUser() {
        return null;
    }

    @Override
    public Collection<Sendable> getUsersToSend(User user, Chatable chatRoom) {
        return null;
    }

    @Override
    public void phrase(Listener listener) {
        if (!listener.getChatRoom().getUser().equals(this.user)) {
            printMessage();
        }
    }

    @Override
    public void printMessage() {
        SimpleDateFormat simpleDateFormat = new SimpleDateFormat("HH:mm:ss");
//        System.out.println("");
        System.out.println(simpleDateFormat.format(this.date) + ", "+ this.user.getUsername() + ": " + this.text);
    }

}