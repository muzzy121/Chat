package com.lanmessenger;

import com.lanmessenger.messages.ScreenInput;
import com.lanmessenger.thread.ChatRoom;
import com.lanmessenger.thread.Listener;
import com.lanmessenger.users.User;

import java.io.IOException;
import java.io.InputStream;
import java.net.Socket;

public class Main {

    public static void main(String[] args) {
        final String HOST = "localhost";
        final int PORT = 7777;


        // write your code here

        Socket socket = null;
        InputStream inputStream = null;
        User user = new User("Muzzy", 11);
        ChatRoom chatRoom = new ChatRoom();
        ScreenInput screenInput = new ScreenInput(chatRoom, user);
        new Thread(screenInput).start();

        try {
            socket = new Socket("localhost", PORT);
            inputStream = socket.getInputStream();
        } catch (IOException e) {
            e.printStackTrace();
        }
        Listener listener = new Listener(socket, inputStream);
        new Thread(listener).start();

        if (socket.isConnected()) {
            chatRoom.setSocket(socket);
            //user.setSocket(socket);
        }
    }
}
