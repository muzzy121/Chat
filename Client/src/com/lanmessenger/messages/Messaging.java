package com.lanmessenger.messages;

import com.lanmessenger.thread.ChatRoom;
import java.net.Socket;

public interface Messaging {
    void phrase(ChatRoom chatRoom, Socket socket);
    void printMessage();
    void getUser();
    boolean isRecived();
    Message setRecived(boolean recived);
    boolean isSended();
    Message setSended(boolean sended);

}

