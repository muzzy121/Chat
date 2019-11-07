package com.lanmessenger.thread;

import java.io.IOException;
import java.net.*;

public class Finder implements Runnable {
    private boolean isStart = true;

    public void stop() {
        this.isStart = false;
    }

    @Override
    public void run() {
        try {
            DatagramSocket datagramSocket = new DatagramSocket();
            datagramSocket.setBroadcast(true);

            // TODO: 2019-11-07 For test broadcast address set for 255.255.255.255 - need to think how, and if this is possible to send broadcast from more than one interface!!!
            byte[] data = "dupa".getBytes();

            try {
                DatagramPacket datagramPacket = new DatagramPacket(data, data.length, InetAddress.getByName("255.255.255.255"), 7778);
                datagramSocket.send(datagramPacket);
                System.out.println("Buffer sended!");
                stop();
            } catch (IOException e) {
                e.printStackTrace();
            }
        } catch (SocketException e) {
            e.printStackTrace();
        }


        // TODO: 2019-11-07 Class which allows me to find server in LAN Network
    }
}

