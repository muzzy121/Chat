package com.lanmessenger.messages;

        import com.lanmessenger.users.User;

        import java.io.Serializable;
        import java.text.Format;
        import java.text.SimpleDateFormat;
        import java.util.Date;

public class Message implements Serializable, Messaging {
    private Date date;
    private String text;
    private User user;

    public Message(String text){
        this.date = new Date();
        this.text = text;
    }

    @Override
    public void dupa() {

    }

    @Override
    public void printMessage() {
        System.out.println("Wiadomosc klasy Message");
        System.out.println(text);
//        SimpleDateFormat simpleDateFormat = new SimpleDateFormat("HH:mm:ss");
//        System.out.println(simpleDateFormat.format(this.date) + ", " + this.text);
    }
}
