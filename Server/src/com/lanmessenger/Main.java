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
        //ScreenInput screenInput;


        ScreenInput screenInput = new ScreenInput(chatRoom);
        new Thread(screenInput).start();

        try {
            serverSocket = new ServerSocket(PORT);
            socket = serverSocket.accept();
            inputStream = socket.getInputStream();

        } catch (IOException e) {
            e.printStackTrace();
        }
        Listener listener = new Listener(socket, inputStream);
        new Thread(listener).start();
        if(socket.isConnected()){chatRoom.addSocket(socket);
            System.out.println("Connected");
        }

    }
}

