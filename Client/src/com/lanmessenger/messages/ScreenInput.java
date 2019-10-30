package com.lanmessenger.messages;

import com.lanmessenger.thread.ChatRoom;
import com.lanmessenger.thread.Chatable;
import com.lanmessenger.thread.Sendable;
import com.lanmessenger.thread.Sender;
import com.lanmessenger.users.User;

import java.util.Scanner;

public class ScreenInput implements Runnable {
    private boolean end = false;
    private Scanner scanner = new Scanner(System.in);
    private Chatable chatRoom;
    private Sendable sender;
    private User user;

    public ScreenInput(Chatable chatRoom, User user) {
        this.chatRoom = chatRoom;
        this.sender = new Sender(chatRoom);
        this.user = user;
    }
    public void printHelp() {
        System.out.println("");
        System.out.println("Write: ");
        System.out.println("'/help' - see help ");
        System.out.println("'/connect' - to connect with server ");
        System.out.println("'/end' - to disconnect from server ");
        System.out.println("'/list' - to see all messages ");
        System.out.println("'/exit' - to close app ");
        System.out.println();
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
                    case "/connect":{

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
                        break;
                    case "/list":
                        break;
                    case "/help":
                        printHelp();
                        break;
                    default: continue;
                }
            } else {
                Messaging packet = new Message(content, user);
                chatRoom.addMessage(packet);
                chatRoom.update();
            }


        }
    }


}
