package com.lanmessenger.messages;

        import com.lanmessenger.thread.Chatable;
        import com.lanmessenger.thread.Listener;
        import com.lanmessenger.thread.Sendable;
        import com.lanmessenger.users.User;
        import java.io.Serializable;
        import java.net.Socket;
        import java.text.SimpleDateFormat;
        import java.util.Collection;
        import java.util.Date;
        import java.util.Map;
        import java.util.Set;
        import java.util.stream.Collectors;

public class Message implements Serializable, Messaging {

    private Date date;
    private String text;
    private User user;

    public boolean isRecived() {
        return isRecived;
    }

    @Override
    public Collection<Sendable> getUsersToSend(User user, Chatable chatRoom) {
        Set<Sendable> result;
        Set<Map.Entry<User, Sendable>> entrySet = chatRoom.getSendableMap().entrySet();
        result = entrySet.stream()
                .filter(x -> !user.equals(x.getKey()))
                .map(y -> y.getValue())
                .collect(Collectors.toSet());
        return result;
    }

    @Override
    public Message setRecived(boolean recived) {
        isRecived = recived;
        return this;
    }

    @Override
    public boolean isSended() {
        return isSended;
    }

    @Override
    public Message setSended(boolean sended) {
        isSended = sended;
        return this;
    }

    @Override
    public User getUser() {
        return this.user;
    }

    private boolean isRecived;
    private boolean isSended;

    public Message(String text, User user) {
        this.date = new Date();
        this.text = text;
        this.user = user;
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
