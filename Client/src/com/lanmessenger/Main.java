package com.lanmessenger;

import com.lanmessenger.thread.Sender;

import java.io.IOException;
import java.net.Socket;

public class Main {

    public static void main(String[] args) {
        // write your code here
        Socket socket = null;
        try {
            socket = new Socket("localhost",7777);
        } catch (IOException e) {
            e.printStackTrace();
        }

        new Sender(socket).run();
    }
}
