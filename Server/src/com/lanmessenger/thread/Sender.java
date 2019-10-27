package com.lanmessenger.thread;

import com.lanmessenger.messages.Messaging;
import com.lanmessenger.users.User;

import java.io.IOException;
import java.io.ObjectOutputStream;
import java.io.OutputStream;
import java.net.Socket;
import java.util.Collection;

public class Sender implements Sendable {
    private ChatRoom chatRoom;

    public Sender(ChatRoom chatRoom) {
        this.chatRoom = chatRoom;
    }


    @Override
    public void update() {
        for (Messaging message : chatRoom.getMessageToSend()) {
            System.out.println(message);
            System.out.println(message.getUser().getUsername());
            Collection<Socket> usersToSend = chatRoom.getUsersToSend(message.getUser());

//            for (User user: usersToSend
//                 ) {
//                System.out.println(chatRoom.getSocket());
//                send(chatRoom.getSocket(), message);
//                chatRoom.moveMessageToList();
//            }

        }
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
