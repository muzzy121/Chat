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
            InetAddress broadcastAddress = InetAddress.getByName("192.168.0.255");
            DatagramSocket datagramSocket = new DatagramSocket();
            datagramSocket.setBroadcast(true);

            // TODO: 2019-11-07 For test broadcast address set for 192.168.88.255(wrong idea to send to everyone 255x4) - need to think how, and if this is possible to send broadcast from more than one interface!!!
            // TODO: 2019-11-08 Looks like I need to get all of interfaces, check IP's and then take a broadcast IP from every single Interface

            // TODO: 2019-11-08 Messege need to be checked on server so has to be identified by server that comes from my chat app
            byte[] request = "secret_code".getBytes();
            byte[] response = new byte[15000];

            try {
                DatagramPacket datagramPacket = new DatagramPacket(request, request.length, broadcastAddress, 7778);
                datagramSocket.send(datagramPacket);
                System.out.println("Buffer sended!");

                // ready to wait for response

                // create new datagram to catch response

                DatagramPacket toRecivePacket = new DatagramPacket(response, response.length);
                datagramSocket.setSoTimeout(1000);
                datagramSocket.receive(toRecivePacket);
                System.out.println("Recived response from : "+ toRecivePacket.getData().toString() + " from address: "+ toRecivePacket.getAddress().getHostAddress());
                //stop();



            } catch (IOException e) {
                e.printStackTrace();
            }
        } catch (SocketException | UnknownHostException e) {
            e.printStackTrace();
        }


        // TODO: 2019-11-07 Class which allows me to find server in LAN Network
    }
}

