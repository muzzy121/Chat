package com.lanmessenger.thread;


import java.io.IOException;
import java.net.*;

//Class for a socket Listener for broadcast pockets - used for check if server is available in the network
public class LookupListener implements Runnable{
    private boolean isStart = true;
    private DatagramSocket datagramSocket;
    private InetSocketAddress inetSocketAddress;


    public LookupListener(InetSocketAddress inetSocketAddress) {
        this.inetSocketAddress = inetSocketAddress;
    }

    public void stop() {
        this.isStart = false;
    }
    @Override
    public void run() {
        try {
            datagramSocket = new DatagramSocket(inetSocketAddress);
            datagramSocket.setBroadcast(true);
        } catch (SocketException e) {
            e.printStackTrace();
        }
        while (isStart) {
                byte[] buffer = new byte[15000];  // create a buffer, which can handle packet ones will be get later in  datagramSocket.receive!
                DatagramPacket datagramPacket = new DatagramPacket(buffer, buffer.length);
            try {
                System.out.println("Waiting for packet!");
                datagramSocket.receive(datagramPacket);
                System.out.println("Recived pocket: " + datagramPacket.getData());
                System.out.println("Recived from: "+ datagramPacket.getAddress());
                // TODO: 2019-11-07 See if I can catch broadcast packet!
                // TODO: 2019-11-07 Check if packet is fine and send back packet
                // TODO: 2019-11-07 In response send Servername!!!! 
                DatagramPacket packet = new DatagramPacket();

            } catch (IOException e) {
                e.printStackTrace();
            }


        }
    }

// TODO: 2019-11-07 I have no idea what to do :) Here i need to listen for a clients - they will search in the network an opened port by udp broadcast!

}
