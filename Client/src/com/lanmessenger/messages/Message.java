package com.lanmessenger.messages;

import com.lanmessenger.users.User;

import java.io.Serializable;
import java.util.Date;

public class Message implements Serializable, Messaging{
    private Date date;
    private String text;
    private User user;

    public Message(String text){
        this.date = new Date();
        this.text = text;
    }

    @Override
    public void dupa() {

    }

    @Override
    public void printMessage() {

    }
}
