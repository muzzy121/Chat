package com.lanmessenger.thread;

import com.lanmessenger.messages.Messaging;

import java.io.IOException;
import java.io.InputStream;
import java.io.ObjectInputStream;
import java.net.Socket;
import java.util.Optional;


public class Listener implements Runnable, Listenable {
    private Socket socket;
    private InputStream inputStream;
    private Chatable chatRoom;
    private Messaging pocket = null;
    private boolean isStart = true;

    public void stop() {
        this.isStart = false;
    }


    public Listener(Socket socket, ChatRoom chatRoom) {
        this.socket = socket;
        this.chatRoom = chatRoom;

    }

    public Socket getSocket() {
        return socket;
    }

    @Override
    public Chatable getChatRoom() {
        return this.chatRoom;
    }

    public Optional<Messaging> Listen() {
        try {
                if (!socket.isClosed()) {
                inputStream = socket.getInputStream();
                ObjectInputStream objectInputStream = new ObjectInputStream(inputStream);
                Object object = objectInputStream.readObject();
                return (Optional<Messaging>)object;
            }
        } catch (IOException e) {
            System.out.println("");
            System.out.println("Disconnected without bye!");

            getChatRoom().removeObserver(getChatRoom().getUserBySocket(socket));
            // TODO: 2019-11-04 Remove broken user from MAP! Have no idea how!

        } catch (ClassNotFoundException e) {
            e.printStackTrace();
        }
        return null;
    }

    @Override
    public void run() {
        while (isStart) {
//            System.out.println("Waiting for data...");

            Optional<Messaging> pocket = Listen();

            if(pocket.isPresent()) {
                if (!pocket.equals(null)) {
                    System.out.println(pocket.getClass());
                    pocket.get().phrase(this);
                }
            }
            if(socket.isClosed()) { stop();}
        }

    }
}
