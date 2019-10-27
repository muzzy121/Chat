package com.lanmessenger.messages;

import com.lanmessenger.thread.ChatRoom;
import com.lanmessenger.users.User;

import java.net.Socket;

public interface Messaging {
    void phrase(ChatRoom chatRoom, Socket socket);
    void printMessage();
    User getUser();
    boolean isRecived();
    Message setRecived(boolean recived);
    boolean isSended();
    Message setSended(boolean sended);
}
