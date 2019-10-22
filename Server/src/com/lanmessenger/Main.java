package com.lanmessenger;

import com.lanmessenger.messages.ScreenInput;
import com.lanmessenger.thread.ChatRoom;
import com.lanmessenger.thread.Listener;

import java.io.IOException;
import java.io.InputStream;
import java.net.ServerSocket;
import java.net.Socket;

public class Main {

    public static void main(String[] args) {
        final int PORT = 7777;
        ServerSocket serverSocket;
        Socket socket = null;
        InputStream inputStream = null;
        ChatRoom chatRoom = new ChatRoom();
        ScreenInput screenInput;
        new ScreenInput(chatRoom).run();

        try {
            serverSocket = new ServerSocket(PORT);
            socket = serverSocket.accept();
            inputStream = socket.getInputStream();

        } catch (IOException e) {
            e.printStackTrace();
        }

        new Listener(socket, inputStream).run();

    }
}

