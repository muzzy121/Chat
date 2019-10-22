package com.lanmessenger.thread;

public class Sender implements Sendable {
    private ChatRoom chatRoom;

    public Sender(ChatRoom chatRoom) {
        this.chatRoom = chatRoom;
    }


    @Override
    public void update() {
    }
}
