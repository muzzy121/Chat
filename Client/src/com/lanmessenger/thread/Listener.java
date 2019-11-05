package com.lanmessenger.thread;

import com.lanmessenger.messages.Bye;
import com.lanmessenger.messages.End;
import com.lanmessenger.messages.Messaging;

import java.io.IOException;
import java.io.InputStream;
import java.io.ObjectInputStream;
import java.net.Socket;

public class Listener implements Runnable, Listenable {
    private Socket socket;
    private InputStream inputStream;
    private Chatable chatRoom;
    private Messaging packet = null;
    private boolean isStart = true;

    public Listener(Socket socket, Chatable chatRoom) {
        this.socket = socket;
        this.chatRoom = chatRoom;

    }

    public void stop() {
        this.isStart = false;
    }

    public Socket getSocket() {
        return socket;
    }

    public Messaging Listen() {
        try {
            inputStream = socket.getInputStream();
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
    public Chatable getChatRoom() {
        return this.chatRoom;
    }

    @Override
    public void run() {
        while (isStart) {
            packet = Listen();
            try {
                if (!packet.equals(null)) {
                    packet.phrase(this);
                }
                if (socket.isClosed()) {
                    stop();
                }
            } catch (NullPointerException npe) {

            }
        }
    }
}