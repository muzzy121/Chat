package com.lanmessenger.messages;

import com.lanmessenger.thread.Chatable;
import com.lanmessenger.thread.Listener;
import com.lanmessenger.thread.Sendable;
import com.lanmessenger.users.User;

import java.io.IOException;
import java.util.Collection;

public class End extends Command {
    public End(User user) {
        super(user);
    }

    @Override
    public void phrase(Listener listener) {
        for (Sendable sender: listener.getChatRoom().getSendableMap().values()
             ) {

            if(sender.getUser().equals(user)) {
                sender.send(new Bye(user));
//                System.out.println("Recived end pocket. Sending bye to: " + user);
                try {
                    listener.stop();
                    sender.getSocket().close();
                    listener.getChatRoom().removeObserver(user);


                } catch (IOException e) {
                    e.printStackTrace();
                }
            } else {
                sender.send(new Message("Bye!", user));
            }

        }




    }

    @Override
    public Collection<Sendable> getUsersToSend(User user, Chatable chatRoom) {
        return null;
    }
}
