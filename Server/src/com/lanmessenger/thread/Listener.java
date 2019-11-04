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

    public Messaging Listen() {
        try {
                if (!socket.isClosed()) {
                inputStream = socket.getInputStream();
                ObjectInputStream objectInputStream = new ObjectInputStream(inputStream);
                Object object = objectInputStream.readObject();
                return (Messaging) object;
            }
        } catch (IOException e) {
            System.out.println("");
            System.out.println("Disconnected without bye!");
            getChatRoom().removeObserver(getChatRoom().getUserBySocket(socket));
            // TODO: 2019-11-04 Remove broken user from MAP! Have no idea how!
            //e.printStackTrace();
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
            if(!pocket.equals(null)) {                              // TODO: 2019-10-30 Question to Pawel - where to check if null ?!
                System.out.println(pocket.getClass());
                pocket.phrase(this);

            }
            if(socket.isClosed()) { stop();}
        }
    }
}
