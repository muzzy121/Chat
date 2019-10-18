package com.lanmessenger.messages;

        import java.io.Serializable;
        import java.text.Format;
        import java.text.SimpleDateFormat;
        import java.util.Date;

public class Message implements Serializable, Messaging {
    private Date date;
    private String text;

    public Message(String text){
        this.date = new Date();
        this.text = text;
    }

    @Override
    public void dupa() {

    }

    @Override
    public void printMessage() {
        SimpleDateFormat simpleDateFormat = new SimpleDateFormat("HH:mm:ss");
        System.out.println(simpleDateFormat.format(this.date) + ", " + this.text);
    }
}
