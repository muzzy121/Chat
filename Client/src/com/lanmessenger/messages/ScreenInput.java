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

            Message message = new Message(content);
            Messaging helloTest = new Hello(user);
            ((Hello) helloTest).getUser();
            chatRoom.addMessage(helloTest);
            chatRoom.update();


//            Messaging endTest = new End();
//            Messaging hello = new Hello("Dupa");
//            endTest.dupa();
//            helloTest.dupa();
//            ((Hello) helloTest).getType();
//            ((Hello) hello).getType();
//            System.out.println(helloTest.getClass());
//            chatRoom.addMessage(message);


//            chatRoom.update();

        }
    }


}
