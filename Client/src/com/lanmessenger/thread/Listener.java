package com.lanmessenger.thread;

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


    @Override
    public void run() {
        try {
            ObjectInputStream objectInputStream = new ObjectInputStream(inputStream);

            while (true) {
                objectInputStream.readObject();
            }


        } catch (IOException e) {
            e.printStackTrace();
        } catch (ClassNotFoundException e) {
            e.printStackTrace();
        }

    }
}
