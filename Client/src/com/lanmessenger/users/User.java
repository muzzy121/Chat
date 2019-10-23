package com.lanmessenger.users;

import java.io.Serializable;
import java.net.Socket;

public class User implements Serializable {
    private String name;
    private int id;

    public User(String name, int id) {
        this.name = name;
        this.id = id;
    }
}