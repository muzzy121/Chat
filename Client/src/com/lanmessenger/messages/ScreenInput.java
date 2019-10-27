package com.lanmessenger.messages;

import com.lanmessenger.thread.ChatRoom;
import com.lanmessenger.thread.Sendable;
import com.lanmessenger.thread.Sender;
import com.lanmessenger.users.User;

import java.util.Scanner;

public class ScreenInput implements Runnable {
    private boolean end = false;
    private Scanner scanner = new Scanner(System.in);
    private ChatRoom chatRoom;
    private Sendable sender;
    private User user;

    public ScreenInput(ChatRoom chatRoom, User user) {
        this.chatRoom = chatRoom;
        this.sender = new Sender(chatRoom);
        this.user = user;
    }

    @Override
    public void run() {
        String content;
        chatRoom.addObserver(sender);

        while (!end) {
            do {
                System.out.print("Napisz: ");
                content = scanner.nextLine();
            } while (content.equals(null));

            if(content.matches("^//?.*$")) {
                switch (content) {
                    case "/hello":{
                        Messaging packet = new Hello(user);
                        System.out.println(packet.getUser().getUsername());
//                        user.printUsername();
                        chatRoom.addMessage(packet);
                        chatRoom.update();
                        break;
                    }
                    case "/end":
                        Messaging packet = new End(user);
                        chatRoom.addMessage(packet);
                        chatRoom.update();
                    default: continue;
                }
            } else {
                Messaging packet = new Message(content);
                chatRoom.addMessage(packet);
                chatRoom.update();
            }


        }
    }


}
