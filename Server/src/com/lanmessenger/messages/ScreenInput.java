package com.lanmessenger.messages;

import com.lanmessenger.thread.ChatRoom;
import com.lanmessenger.thread.Sendable;
import com.lanmessenger.thread.Sender;
import com.lanmessenger.users.User;

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

    @Override
    public void run() {
        while (!end) {
            do {
                System.out.print("Napisz: ");
                content = scanner.nextLine();
            } while (content.equals(null));

            Message message = new Message(content, user);
            chatRoom.addMessage(message);
            chatRoom.update();
        }
    }
}
