package com.lanmessenger.thread;

import com.lanmessenger.messages.Messaging;

import java.io.IOException;
import java.io.InputStream;
import java.io.ObjectInputStream;
import java.net.Socket;

public class Listener implements Runnable, Listenable {
    private Socket socket;
    private InputStream inputStream;


    public Listener(Socket socket, InputStream inputStream) {
        this.socket = socket;
        this.inputStream = inputStream;
    }
    public Messaging Listen() {
        try {
            ObjectInputStream objectInputStream = new ObjectInputStream(inputStream);
            if (!socket.isClosed()) {
                Object object = objectInputStream.readObject();
//                System.out.println(object.getClass());
                return (Messaging) object;
            }
        } catch (IOException e) {
            e.printStackTrace();
        } catch (ClassNotFoundException e) {
            e.printStackTrace();
        }
        return null;
    }

    @Override
    public void run() {
        while (true) {
//            System.out.println(this.socket);
            Messaging mess = Listen();
            mess.printMessage();
        }
    }
}
