package com.lanmessenger.messages;

import com.lanmessenger.thread.Chatable;
import com.lanmessenger.thread.Listener;
import com.lanmessenger.thread.Sendable;
import com.lanmessenger.users.User;

import java.io.IOException;
import java.net.Socket;

public class End extends Command {
    public End(User user) {
        super(user);
    }

    @Override
    public void phrase(Listener listener) {
        for (Sendable sender: listener.getChatRoom().getSendable()
             ) {
            if(sender.getUser().equals(user)) {
                sender.send(new Bye(user));
                System.out.println("Test end");
                try {
                    listener.stop();
                    sender.getSocket().close();

                } catch (IOException e) {
                    e.printStackTrace();
                }
            }

        }




    }
}
