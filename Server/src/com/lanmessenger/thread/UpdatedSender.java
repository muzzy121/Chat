package com.lanmessenger.thread;

import com.lanmessenger.messages.Messaging;
import com.lanmessenger.users.User;

import java.io.IOException;
import java.io.ObjectOutputStream;
import java.io.OutputStream;
import java.net.Socket;

public class UpdatedSender implements Sendable{
    private Chatable chatRoom;
    private Socket socket;
    private User user;


    public User getUser() {
        return user;
    }



    public Socket getSocket() {
        return socket;
    }


    public UpdatedSender(Chatable chatRoom, Socket socket, User user) {
        this.chatRoom = chatRoom;
        this.socket = socket;
        this.user = user;

    }

    public void send(Messaging message) {
        try {
            OutputStream outputStream = socket.getOutputStream();
            ObjectOutputStream objectOutputStream = new ObjectOutputStream(outputStream);

            objectOutputStream.writeObject(message);
            objectOutputStream.flush();
        } catch (
                IOException e) {
            e.printStackTrace();
        }
    }

    @Override
    public void update() {
        for (Messaging message : chatRoom.getMessageToSend()) {
            System.out.println("Message sent by: " + message.getUser().getUsername());
//            chatRoom.getSendable();

            send(message);
        }


    }
}
