package com.lanmessenger;

import com.lanmessenger.messages.ScreenInput;
import com.lanmessenger.thread.ChatRoom;
import com.lanmessenger.thread.Listener;
import com.lanmessenger.thread.LookupListener;
import com.lanmessenger.users.User;

import java.io.IOException;
import java.io.InputStream;
import java.net.InetSocketAddress;
import java.net.ServerSocket;
import java.net.Socket;

public class Main {

    public static void main(String[] args) {
        final int PORT = 7777;
        final InetSocketAddress inetSocketAddress = new InetSocketAddress("0.0.0.0", 7778);
        ServerSocket serverSocket = null;
        Socket socket = null;
        User user = new User("Server", 1);
        ChatRoom chatRoom = new ChatRoom();
        LookupListener lookupListener = new LookupListener(inetSocketAddress);
        new Thread(lookupListener).start();

        
        
        ScreenInput screenInput = new ScreenInput(chatRoom, user);
        new Thread(screenInput).start();
    
        try {
            serverSocket = new ServerSocket(PORT);

        } catch (IOException e) {
            e.printStackTrace();
        }

        while (true) {
            try {
                socket = serverSocket.accept();

            } catch (IOException e) {
                e.printStackTrace();
            }

            Listener listener = new Listener(socket, chatRoom);
            new Thread(listener).start();
            if (socket.isConnected()) {
                System.out.println("");
                System.out.println("Connected");
            }
        }

    }
}

// CTLR+ALT+M ekstrakcja metody
// CTLR+ALT+V ekstrakcja zmiennej
// CTLR+ALT+F ekstrakcja pola

