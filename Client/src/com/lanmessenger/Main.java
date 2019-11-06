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

        // TODO: 2019-11-06 Most difficult. Solution for find a server in Network class - need to put this on different branch!!!
        User user = new User("Muzzy", 11); // TODO: 2019-11-06 Random user id creator!
        Chatable chatRoom = new ChatRoom(user);
        ScreenInput screenInput = new ScreenInput(chatRoom, user);
        new Thread(screenInput).start();

    }
}
