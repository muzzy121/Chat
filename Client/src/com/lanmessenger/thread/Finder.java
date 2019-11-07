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
            InetAddress broadcastAddress = InetAddress.getByName("192.168.88.255");
            DatagramSocket datagramSocket = new DatagramSocket();
            datagramSocket.setBroadcast(true);

            // TODO: 2019-11-07 For test broadcast address set for 192.168.88.255(wrong idea to send to everyone 255x4) - need to think how, and if this is possible to send broadcast from more than one interface!!!
            byte[] data = "dupa".getBytes();

            try {
                DatagramPacket datagramPacket = new DatagramPacket(data, data.length, broadcastAddress, 7778);
                datagramSocket.send(datagramPacket);
                System.out.println("Buffer sended!");
                stop();
            } catch (IOException e) {
                e.printStackTrace();
            }
        } catch (SocketException | UnknownHostException e) {
            e.printStackTrace();
        }


        // TODO: 2019-11-07 Class which allows me to find server in LAN Network
    }
}

