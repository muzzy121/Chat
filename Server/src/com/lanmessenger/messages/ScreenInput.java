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
    private Sendable sender;


    public ScreenInput(ChatRoom chatRoom, User user) {
        this.chatRoom = chatRoom;
        this.sender = new Sender(chatRoom);
    }

    @Override
    public void run() {
        chatRoom.addObserver(sender);
        while (!end) {
            do {
                System.out.print("Napisz: ");
                content = scanner.nextLine();
            } while (content.equals(null));

            Message message = new Message(content);
            chatRoom.addMessage(message);
            chatRoom.update();
        }
    }
}
