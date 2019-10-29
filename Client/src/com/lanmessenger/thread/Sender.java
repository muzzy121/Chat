package com.lanmessenger.thread;

import com.lanmessenger.messages.Message;
import com.lanmessenger.messages.Messaging;
import com.lanmessenger.users.User;

import java.io.IOException;
import java.io.ObjectOutputStream;
import java.io.OutputStream;
import java.net.Socket;

public class Sender implements Sendable {
    private Chatable chatRoom;

    public Sender(Chatable chatRoom) {
        this.chatRoom = chatRoom;
    }


    @Override
    public void update() {
        for (Messaging message : chatRoom.getMessageToSend()) {
            send(chatRoom.getSocket(),message);
            chatRoom.moveMessageToList();

        }
    }

    @Override
    public Socket getSocket() {
        return null;
    }

    @Override
    public User getUser() {
        return null;
    }

    public void send(Socket socket, Messaging message) {
        try {

            OutputStream outputStream = socket.getOutputStream();
            ObjectOutputStream objectOutputStream = new ObjectOutputStream(outputStream);
//            objectOutputStream.writeObject("Test");
            objectOutputStream.writeObject(message);
            objectOutputStream.flush();
        } catch (
                IOException e) {
            e.printStackTrace();
        }
    }
}


