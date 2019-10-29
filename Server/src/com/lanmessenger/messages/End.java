package com.lanmessenger.messages;

import com.lanmessenger.thread.Chatable;
import com.lanmessenger.thread.Sendable;
import com.lanmessenger.users.User;
import java.net.Socket;

public class End extends Command {
    public End(User user) {
        super(user);
    }

    @Override
    public void phrase(Chatable chatRoom, Socket socket) {
        for (Sendable sender: chatRoom.getSendable()
             ) {
            if(sender.getUser().equals(user)) {
                sender.send(new Bye(user));
                System.out.println("Test end");
            }

        }




    }
}
