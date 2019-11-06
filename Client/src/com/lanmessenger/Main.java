package com.lanmessenger;

import com.lanmessenger.messages.ScreenInput;
import com.lanmessenger.thread.ChatRoom;
import com.lanmessenger.thread.Chatable;
import com.lanmessenger.thread.Listener;
import com.lanmessenger.users.User;

import java.io.IOException;
import java.io.InputStream;
import java.net.InetSocketAddress;
import java.net.Socket;
import java.net.SocketAddress;

public class Main {
    final static String HOST = "localhost";
    final static int PORT = 7777;
    public final static InetSocketAddress serverAddress = new InetSocketAddress(HOST,PORT);

    public static void main(String[] args) {


        Socket socket = new Socket();
        InputStream inputStream = null;
        User user = new User("Muzzy", 11);
        Chatable chatRoom = new ChatRoom(user);
        ScreenInput screenInput = new ScreenInput(chatRoom, user);
        new Thread(screenInput).start();

        try {
            //socket.connect(serverAddress);
            System.out.println("");
            System.out.println("My socket is: "+ (socket.isConnected() ? "Connected" : "Not connected!"));

        } catch (/*IOException e*/ Exception e) {
//            e.printStackTrace();
            System.out.println("Unable to connect to "+ HOST);
        }

        chatRoom.setSocket(socket);
//        Listener listener = new Listener(socket, chatRoom);
//        new Thread(listener).start();


//        if (socket.isConnected()) {
//        }
    }
}
