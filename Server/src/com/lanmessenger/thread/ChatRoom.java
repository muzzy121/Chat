package com.lanmessenger.thread;

import com.lanmessenger.messages.Message;
import com.lanmessenger.users.User;

import java.net.Socket;
import java.util.List;
import java.util.Map;

public class ChatRoom {
    private List<Message> messageList;
    private Map<User, Socket> userSocketMap;
    private static ChatRoom instance;

    private ChatRoom(){
        if(instance != null) {
            throw new IllegalStateException("Can't create instance via constructor");

        }
    }
    public ChatRoom getInstance(){
        return new ChatRoom();
    }

}
