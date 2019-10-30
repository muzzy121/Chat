package com.lanmessenger.messages;

import com.lanmessenger.thread.Chatable;
import com.lanmessenger.thread.Listener;
import com.lanmessenger.thread.Sendable;
import com.lanmessenger.users.User;
import java.net.Socket;

public class End extends Command {
    public End(User user) {
        super(user);
    }

    @Override
    public void phrase(Chatable chatRoom, Socket socket, Listener listener) {
        System.out.println("Test end: " + user.getUsername());

    }
}
