package com.lanmessenger.thread;

import com.lanmessenger.messages.Messaging;

import java.io.IOException;
import java.io.InputStream;
import java.io.ObjectInputStream;
import java.net.Socket;


public class Listener implements Runnable, Listenable {
    private Socket socket;

    private InputStream inputStream;
    private Chatable chatRoom;
    //    private Sendable sender;
    private Messaging pocket = null;
    private boolean isStart = true;

    public void stop() {
        this.isStart = false;
    }


    public Socket getSocket() {
        return socket;
    }

    public Listener(Socket socket, ChatRoom chatRoom) {
        this.socket = socket;
//        this.inputStream = inputStream;
        this.chatRoom = chatRoom;

    }

    public Messaging Listen() {
        try {
                if (!socket.isClosed()) {
                inputStream = socket.getInputStream();
                ObjectInputStream objectInputStream = new ObjectInputStream(inputStream);
                Object object = objectInputStream.readObject();
//                System.out.println(object.getClass());
                return (Messaging) object;
            }
        } catch (IOException e) {
            System.out.println("");
            System.out.println("Disconnected without bye!");
            e.printStackTrace();
        } catch (ClassNotFoundException e) {
            e.printStackTrace();
        }
        return null;
    }

    @Override
    public void run() {
        while (isStart) {
//            System.out.println("Waiting for data...");
            pocket = Listen();
            System.out.println(pocket.getClass());
            if(!pocket.equals(null)) {                              // TODO: 2019-10-30 Question to Pawel - where to check if null ?!
                pocket.phrase(this);

            }
            if(socket.isClosed()) { stop();}
        }
    }

    @Override
    public Chatable getChatRoom() {
        return this.chatRoom;
    }
}
