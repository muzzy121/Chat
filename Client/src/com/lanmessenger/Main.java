package com.lanmessenger;

import com.lanmessenger.messages.ScreenInput;
import com.lanmessenger.thread.ChatRoom;
import com.lanmessenger.thread.Chatable;
import com.lanmessenger.thread.Listener;
import com.lanmessenger.users.User;

import java.io.IOException;
import java.io.InputStream;
import java.net.Socket;

public class Main {

    public static void main(String[] args) {
        final String HOST = "localhost";
        final int PORT = 7777;

        Socket socket = null;
        InputStream inputStream = null;
        User user = new User("Muzzy", 11);
        Chatable chatRoom = new ChatRoom(user);
        ScreenInput screenInput = new ScreenInput(chatRoom, user);
        new Thread(screenInput).start();

        try {
            socket = new Socket("localhost", PORT);

        } catch (IOException e) {
            e.printStackTrace();
        }

        Listener listener = new Listener(socket, chatRoom);
        new Thread(listener).start();


        if (socket.isConnected()) {
            chatRoom.setSocket(socket);
            System.out.println("My socket is: "+ socket);
        }
    }
}
