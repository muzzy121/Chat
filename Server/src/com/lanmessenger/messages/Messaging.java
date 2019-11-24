package com.lanmessenger.messages;

import com.lanmessenger.thread.Listener;
import com.lanmessenger.users.User;

public interface Messaging {

    void phrase(Listener listener);
    void printMessage();
    User getUser();


}
