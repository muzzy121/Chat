package com.lanmessenger.messages;

import com.lanmessenger.thread.*;
import com.lanmessenger.users.User;

import java.net.InetAddress;
import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;
import java.util.Scanner;

public class ScreenInput implements Runnable {
    private boolean isStart = true;
    private Scanner scanner = new Scanner(System.in);
    private Chatable chatRoom;
    private Sendable sender;
    private User user;
    private Finder finder;


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

    public void sendHello() {
        Messaging packet = new Hello(user);
        chatRoom.addMessage(packet);
        chatRoom.update();
    }

    @Override
    public void run() {

        String content;
        chatRoom.addObserver(sender);

        while (isStart) {
            do {
                System.out.print("(" + user.getUsername() + "): ");
                content = scanner.nextLine();

            } while (content.equals(null) || content.trim().equals("")); // TODO: 2019-11-03 Prevent sending empty messages! Check if works now!

            if (content.matches("^//?.*$")) {
                switch (content) {
                    case "/diag": {
                        System.out.println("isConnected: " + chatRoom.getSocket().isConnected());
                        System.out.println("isClosed: " + chatRoom.getSocket().isClosed());
                        System.out.println("isBound: " + chatRoom.getSocket().isBound());
                        System.out.println("Socket: " + chatRoom.getSocket());
                        break;
                    }
                    case "/connect": {
                        int i = 0;
                        ServerList serverList = new ServerList();
                        finder = new Finder(serverList);
                        synchronized (serverList) {
                            new Thread(finder).start();
                            try {
                                serverList.wait();
                            } catch (InterruptedException e) {
                                e.printStackTrace();
                            }
//                            System.out.println("From ScreenInput Thread: " + finder.getServerList().keySet());

                            Iterator<InetAddress> iterator = finder.getServerList().iterator();

                            while (iterator.hasNext()) {
                                i++;
                                System.out.println(i + " - " + iterator.next().getHostName());
                            }
                            // TODO: 2019-11-09 Learn how to iterate MAP or LIST, I checked few ways to do that

                            Scanner select = new Scanner(System.in);
                            do {
                                System.out.print("Choose server: ");
                                i = select.nextInt();
                            } while (i < 0 && i >= finder.getServerList().size());
                            chatRoom.connect(finder.getServerList().get(i-1));
                            sendHello();
                        }
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
                    case "/search":
//                        finder = new Finder();
//                        new Thread(finder).start();

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
