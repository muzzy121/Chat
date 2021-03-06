package com.lanmessenger.users;

import java.io.Serializable;
import java.net.Socket;
import java.util.Objects;

public class User implements Serializable {
    private String name;
    private int id;

    public User(String name, int id) {
        this.name = name;
        this.id = id;
    }

    public void printUsername() {
        System.out.println(this.name);
    }
    public String getUsername() {
        return this.name;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (!(o instanceof User)) return false;
        User user = (User) o;
        return id == user.id;
    }

    @Override
    public int hashCode() {
        return Objects.hash(id);
    }
}
