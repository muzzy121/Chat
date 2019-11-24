package com.lanmessenger.messages;

import com.lanmessenger.thread.Listener;
import com.lanmessenger.thread.Sendable;
import com.lanmessenger.users.User;

import java.io.IOException;

public class End extends Command {
    public End(User user) {
        super(user);
    }

    @Override
    /**
     * Sends finish message to client,
     * closeing listener Thread, closesing Socket, removes Sender from observer list
     */
    public void phrase(Listener listener) {
        for (Sendable sender : listener.getChatRoom().getSendableMap().values()
        ) {
            if (sender.getUser().equals(user)) {
                sender.send(new Bye(user));
                try {
                    listener.stop();
                    sender.getSocket().close();
                    listener.getChatRoom().removeObserver(user);

                } catch (IOException e) {
                    System.out.println("Can't close socket!");
                    e.printStackTrace();
                }
            } else {
                sender.send(new Message("Bye!", user));
            }

        }

    }
}
