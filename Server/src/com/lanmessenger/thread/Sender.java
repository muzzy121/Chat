package com.lanmessenger.thread;

import com.lanmessenger.messages.Messaging;
import com.lanmessenger.users.User;

import java.io.IOException;
import java.io.ObjectOutputStream;
import java.io.OutputStream;
import java.net.Socket;
import java.util.Collection;
import java.util.HashSet;

public class Sender implements Sendable {
    private ChatRoom chatRoom;
    private Socket socket;
    Collection<Socket> usersToSend = new HashSet<>();

    public Sender(ChatRoom chatRoom) {
        this.chatRoom = chatRoom;
    }

    @Override
    public void update() {

        for (Messaging message : chatRoom.getMessageToSend()) {

            usersToSend.addAll(chatRoom.getUsersToSend(message.getUser()));

            if (!usersToSend.isEmpty()) {
                for (Socket socket : usersToSend
                ) {
                    send(message);
                    chatRoom.removeSendedMessages();  // TODO: 2019-10-28 Need to fix this. Message are not moving to sended list if no one is on chat for now!
                }
            }
        }
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
    public Socket getSocket() {
        return null;
    }

    @Override
    public User getUser() {
        return null;
    }

}
//            System.out.println(message);
//            System.out.println(message.getUser().getUsername());
//            System.out.println(usersToSend + " ; " + usersToSend.getClass());
//            System.out.println("Test: " + message + " ; " + socket) ;
