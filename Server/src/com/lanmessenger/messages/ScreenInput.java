package com.lanmessenger.messages;

import com.lanmessenger.thread.ChatRoom;
import com.lanmessenger.users.User;

import java.util.Arrays;
import java.util.Scanner;

public class ScreenInput implements Runnable {
    private boolean end = false;
    private String content;
    private Scanner scanner = new Scanner(System.in);
    private ChatRoom chatRoom;
    private User user;


    public ScreenInput(ChatRoom chatRoom, User user) {
        this.chatRoom = chatRoom;
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
        while (!end) {
            do {
                System.out.print("Write: ");
                content = scanner.nextLine();
            } while (content.equals(null));
            if (content.matches("^//?.*$")) {
                switch (content) {
                    case "/users": {
                        System.out.println(Arrays.toString(chatRoom.getUsersFromSendable().toArray()));
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
                    default:
                        System.out.println("Unknown command, use /help");
                        continue;
                }
            } else {
                Message message = new Message(content, user);
                chatRoom.addMessage(message);
                chatRoom.update();
            }
        }
    }
}
