package com.lanmessenger.messages;


import com.lanmessenger.thread.Listener;
import com.lanmessenger.users.User;
import java.io.Serializable;
import java.text.SimpleDateFormat;
import java.util.Date;

public class Message implements Serializable, Messaging {
    private Date date;
    private String text;
    private User user;

    public User getUser() {
        return null;
    }

    public boolean isRecived() {
        return isRecived;
    }

    @Override
    public Message setRecived(boolean recived) {
        isRecived = recived;
        return this;
    }

    @Override
    public boolean isSended() {
        return isSended;
    }

    @Override
    public Message setSended(boolean sended) {
        isSended = sended;
        return this;
    }

    private boolean isRecived;
    private boolean isSended;

    public Message(String text, User user) {
        this.date = new Date();
        this.text = text;
        this.user = user;
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
        System.out.println(simpleDateFormat.format(this.date) + ", "+ this.user.getUsername() + ": " + this.text);
    }

}