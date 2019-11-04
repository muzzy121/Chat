package com.lanmessenger.messages;

import com.lanmessenger.thread.Chatable;
import com.lanmessenger.thread.Listener;
import com.lanmessenger.thread.Sendable;
import com.lanmessenger.users.User;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.Collection;

public class Userlist extends Command implements Serializable {
    private ArrayList<User> users = null;

    public Userlist(User user) {
        super(user);
    }

    public Userlist setUsers(ArrayList<User> users) {
        this.users = users;
        return this;
    }


    public Collection<User> getUsers() {
        return users;
    }

    @Override
    public void phrase(Listener listener) {
        System.out.println("");
        for (User user : users
        ) {
            if (this.user.equals(user)) {
                System.out.println(user.getUsername() + " (me)");
            } else {
                System.out.println(user.getUsername());
            }
        }
    }

    @Override
    public Collection<Sendable> getUsersToSend(User user, Chatable chatRoom) {
        return null;
    }
}