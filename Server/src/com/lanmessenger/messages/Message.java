package com.lanmessenger.messages;

        import com.lanmessenger.thread.Listener;
        import com.lanmessenger.users.User;
        import java.io.Serializable;
        import java.text.SimpleDateFormat;
        import java.util.Date;

public class Message implements Serializable, Messaging {
    private Date date;
    private String text;
    private User user;

    public Message(String text, User user) {
        this.date = new Date();
        this.text = text;
        this.user = user;
    }

    @Override
    public User getUser() {
        return this.user;
    }

    @Override
    public void phrase(Listener listener) {
        if(listener.getChatRoom().getUsersFromSendable().contains(this.user)) {
            printMessage();
            listener.getChatRoom().addMessage(this);
            listener.getChatRoom().update();
        }
    }

    @Override
    public void printMessage() {
        SimpleDateFormat simpleDateFormat = new SimpleDateFormat("HH:mm:ss");
        System.out.println(simpleDateFormat.format(this.date) + ", " + this.user.getUsername() + ": " + this.text);
    }

}
