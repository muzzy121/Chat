package com.lanmessenger;

import com.lanmessenger.messages.ScreenInput;
import com.lanmessenger.thread.ChatRoom;

import java.io.IOException;
import java.net.Socket;

public class Main {

    public static void main(String[] args) {
        // write your code here
        Socket socket = null;
        ChatRoom chatRoom = new ChatRoom();
        ScreenInput screenInput;
        new ScreenInput(chatRoom).run();

        try {
            socket = new Socket("localhost",7777);
        } catch (IOException e) {
            e.printStackTrace();
        }
        if(socket.isConnected()){chatRoom.addSocket(socket);}
//        new Sender(socket).run();
    }
}
