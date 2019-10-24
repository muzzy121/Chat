package com.lanmessenger;

import com.lanmessenger.messages.ScreenInput;
import com.lanmessenger.thread.ChatRoom;
import com.lanmessenger.thread.Listener;
import com.lanmessenger.users.User;

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
        User user = new User("Muzzy", 1);
        ChatRoom chatRoom = new ChatRoom();
        //ScreenInput screenInput;


        ScreenInput screenInput = new ScreenInput(chatRoom, user);
        new Thread(screenInput).start();

        try {
            serverSocket = new ServerSocket(PORT);
            socket = serverSocket.accept();
            inputStream = socket.getInputStream();

        } catch (IOException e) {
            e.printStackTrace();
        }
        Listener listener = new Listener(socket, inputStream, chatRoom);
        new Thread(listener).start();
        if(socket.isConnected()){
            chatRoom.setSocket(socket); // TODO: 2019-10-24 On server side need to add Sockets to User, then to userlist!
            System.out.println("Connected");
        }

    }
}

