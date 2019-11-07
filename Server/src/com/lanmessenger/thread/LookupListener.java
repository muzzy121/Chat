package com.lanmessenger.thread;


import java.io.IOException;
import java.net.DatagramPacket;
import java.net.DatagramSocket;
import java.net.InetSocketAddress;
import java.net.SocketException;

//Class for a socket Listener for broadcast pockets - used for check if server is available in the network
public class LookupListener implements Runnable{
    private boolean isStart = true;
    private DatagramSocket datagramSocket;
    private int port;
    InetSocketAddress inetSocketAddress = new InetSocketAddress("0.0.0.0", port);

    public LookupListener(int lookupPort) {
        this.port = lookupPort;
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
                byte[] buffer = new byte[1500];
                DatagramPacket datagramPacket = new DatagramPacket(buffer, buffer.length);
            try {
                datagramSocket.receive(datagramPacket);
                System.out.println("Recived pocket: " + datagramPacket.getData());
                System.out.println("Recived from: "+ datagramPacket.getAddress());
                // TODO: 2019-11-07 See if I can catch broadcast packet!
            } catch (IOException e) {
                e.printStackTrace();
            }


        }
    }

// TODO: 2019-11-07 I have no idea what to do :) Here i need to listen for a clients - they will search in the network an opened port by udp broadcast!

}
