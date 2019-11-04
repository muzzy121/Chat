package com.lanmessenger.messages;

import com.lanmessenger.thread.Chatable;
import com.lanmessenger.thread.Listener;
import com.lanmessenger.thread.Sendable;
import com.lanmessenger.users.User;
import java.util.Collection;

public interface Messaging {

    void phrase(Listener listener);
    void printMessage();
    User getUser();
    Collection<Sendable> getUsersToSend(User user, Chatable chatRoom);


}
