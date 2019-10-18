package com.lanmessenger.thread;

import com.lanmessenger.messages.Message;

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

    public Message Listen() {
        try {
            ObjectInputStream objectInputStream = new ObjectInputStream(inputStream);
            if (!socket.isClosed()) {
                Object object = objectInputStream.readObject();

                if (object instanceof Message) {
                    return (Message) object;
                }
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
            System.out.println("Waiting for data...");
            Message message = Listen();

            message.printMessage();

        }


    }
}
