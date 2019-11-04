package com.lanmessenger.messages;

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
    public void sendHello(){
        Messaging packet = new Hello(user);
        chatRoom.addMessage(packet);
        chatRoom.update();
    }

    @Override
    public void run() {
        String content;
        chatRoom.addObserver(sender);
        //sendHello();

        while (!end) {
            do {
                System.out.print("("+ user.getUsername() +"): ");
                content = scanner.nextLine();

            } while (content.equals(null));
            // TODO: 2019-11-03 Prevent sending empty messages!

            if(content.matches("^//?.*$")) {
                switch (content) {
                    case "/connect":{
                        // TODO: 2019-10-31 Add method to create new socket!
                        sendHello();
//                        System.out.println(packet.getUser().getUsername());
                        break;
                    }
                    case "/end":
                        Messaging end = new End(user);
                        chatRoom.addMessage(end);
                        chatRoom.update();
                        break;
                    case "/list":
                        System.out.println("");
                        chatRoom.printSendedMessages();
                        break;
                    case "/help":
                        printHelp();
                        break;
                    case "/users":
                        Messaging userList = new Userlist(user);
                        chatRoom.addMessage(userList);
                        chatRoom.update();
                        break;
                    default:
                        System.out.println("Unknown command, use /help");
                        continue;
                }
            } else {
                Messaging packet = new Message(content, user);
                chatRoom.addMessage(packet);
                chatRoom.update();
            }


        }
    }


}
