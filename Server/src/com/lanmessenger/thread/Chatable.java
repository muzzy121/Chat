package com.lanmessenger.thread;

public interface Chatable {
    public void addUser(Sendable sender);
    public void removeUser(Sendable sender);
    public void update();
}
