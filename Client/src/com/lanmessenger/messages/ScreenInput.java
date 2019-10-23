package com.lanmessenger.messages;

import com.lanmessenger.thread.ChatRoom;
import com.lanmessenger.thread.Sendable;
import com.lanmessenger.thread.Sender;

import java.util.Scanner;

public class ScreenInput implements Runnable {
    private boolean end = false;
    private Scanner scanner = new Scanner(System.in);
    private ChatRoom chatRoom;
    private Sendable sender;

    public ScreenInput(ChatRoom chatRoom) {
        this.chatRoom = chatRoom;
        this.sender = new Sender(chatRoom);
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
//            Command command = new Command("exit");
//            chatRoom.addMessage(command);


            Message message = new Message(content);
            chatRoom.addMessage(message);
            chatRoom.update();

        }
    }


}
