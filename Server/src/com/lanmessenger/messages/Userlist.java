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
        users = new ArrayList(listener.getChatRoom().getUsersFromSendable());
        listener.getChatRoom().getSendableMap().get(user).send(this); // TODO: 2019-11-04 Should I make that kind of construction?
    }

    @Override
    public Collection<Sendable> getUsersToSend(User user, Chatable chatRoom) {
        return null;
    }
}
