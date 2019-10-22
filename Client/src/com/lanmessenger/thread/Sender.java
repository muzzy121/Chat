package com.lanmessenger.thread;

import com.lanmessenger.messages.Message;

import java.io.ObjectOutputStream;

public class Sender implements Sendable {
    private ChatRoom chatRoom;

    public Sender(ChatRoom chatRoom) {
        this.chatRoom = chatRoom;
    }


    @Override
    public void update() {
        for (Message message : chatRoom.getMessageToSend()) {

        }
        ;
    }
    try
    {
        outputStream = socket.getOutputStream();
        objectOutputStream = new ObjectOutputStream(outputStream);
//            objectOutputStream.writeObject("Test");
        objectOutputStream.writeObject(message);
        objectOutputStream.flush();
    } catch(
    IOException e)

    {
        e.printStackTrace();
    }
}


//package com.lanmessenger.thread;
//
//import com.lanmessenger.messages.Command;
//import com.lanmessenger.messages.Message;
//import com.lanmessenger.messages.Messaging;
//
//import java.io.IOException;
//import java.io.ObjectOutputStream;
//import java.io.OutputStream;
//import java.net.Socket;
//
//public class Sender implements Runnable {
//    private Socket socket;
//    private OutputStream outputStream;
//    private ObjectOutputStream objectOutputStream;
//
//    public Sender(Socket socket) {
//            this.socket = socket;
//    }
//
//    @Override
//    public void run() {
//        System.out.println(" Wysyłam: ");
////        Message message = new Message("Test");
//        Messaging message = new Command("Exit");
//

//
//    }
//}
