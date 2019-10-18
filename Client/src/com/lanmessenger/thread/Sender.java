package com.lanmessenger.thread;

import com.lanmessenger.messages.Message;

import java.io.IOException;
import java.io.ObjectOutputStream;
import java.io.OutputStream;
import java.net.Socket;

public class Sender implements Runnable {
    private Socket socket;
    private OutputStream outputStream;
    private ObjectOutputStream objectOutputStream;

    public Sender(Socket socket) {
            this.socket = socket;
    }

    @Override
    public void run() {
        System.out.println(" Wysyłam: ");
        Message message = new Message("Test");

        try {
            outputStream = socket.getOutputStream();
            objectOutputStream = new ObjectOutputStream(outputStream);
//            objectOutputStream.writeObject("Test");
            objectOutputStream.writeObject(message);
            objectOutputStream.flush();
        } catch (IOException e) {
            e.printStackTrace();
        }

    }
}
