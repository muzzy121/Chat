package com.lanmessenger.messages;

import com.lanmessenger.thread.ChatRoom;
import com.lanmessenger.users.User;
import java.util.Scanner;


public class ScreenInput implements Runnable {
    private boolean isStart = true;
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


    private void end() {
        Messaging packet = new End(user);
        chatRoom.addMessage(packet);
        chatRoom.update();
    }


    @Override
    public void run() {
        while (isStart) {
            do {
                System.out.print("("+ user.getUsername() +"): ");
                content = scanner.nextLine();
            } while (content.equals(null));
            if (content.matches("^//?.*$")) {
                switch (content) {
                    case "/users": {
                        chatRoom.getUsersFromSendable().stream().forEach(z -> System.out.println("\t" +z.getUsername()));
                        break;
                    }
                    case "/end":
                        end();
                        break;
                    case "/list":
                        chatRoom.printSendedMessages();
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
