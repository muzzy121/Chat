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
    Collection<Socket> usersToSend = new HashSet<>();

    public Sender(ChatRoom chatRoom) {
        this.chatRoom = chatRoom;
    }


    @Override
    public void update() {
        for (Messaging message : chatRoom.getMessageToSend()) {
//            System.out.println(message);
//            System.out.println(message.getUser().getUsername());

            usersToSend.addAll(chatRoom.getUsersToSend(message.getUser()));
            System.out.println(usersToSend + " ; " + usersToSend.getClass());

            if (!usersToSend.isEmpty()) {
                for (Socket socket : usersToSend
                ) {
                    send(socket, message);

                    chatRoom.moveMessageToList();  // TODO: 2019-10-28 Need to fix this. Message are not moving to sended list if no one is on chat for now! 
                } 
            }
        }
//                System.out.println(chatRoom.getSocket());
    }

    public void send(Socket socket, Messaging message) {
        try {
            OutputStream outputStream = socket.getOutputStream();
            ObjectOutputStream objectOutputStream = new ObjectOutputStream(outputStream);
//            objectOutputStream.writeObject("Test");
            System.out.println("Test: " + message + " ; " + socket) ;
            objectOutputStream.writeObject(message);
            objectOutputStream.flush();
        } catch (
                IOException e) {
            e.printStackTrace();
        }
    }
}
