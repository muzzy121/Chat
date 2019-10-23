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
        System.out.println("Wiadomosc klasy command");
        System.out.println(type);
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
