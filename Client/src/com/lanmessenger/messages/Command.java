package com.lanmessenger.messages;

import java.io.Serializable;

public class Command implements Messaging, Serializable {
    public String type;

    public Command(String type) {
        this.type = type;
    }

    @Override
    public void dupa() {

    }

    @Override
    public void printMessage() {

    }
}
