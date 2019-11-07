package com.lanmessenger.thread;


import java.net.DatagramSocket;
import java.net.InetSocketAddress;
import java.net.SocketException;

//Class for a socket Listener for broadcast pockets - used for check if server is available in the network
public class LookupListener implements Runnable{
    private boolean isStart = true;
    private DatagramSocket datagramSocket;
    InetSocketAddress inetSocketAddress = new InetSocketAddress("0.0.0.0", 7778);

    public void stop() {
        this.isStart = false;
    }
    @Override
    public void run() {
        while (isStart) {
            try {
                datagramSocket = new DatagramSocket(inetSocketAddress);
                datagramSocket.setBroadcast(true);

                int tmp = datagramSocket.getBroadcast();

            } catch (SocketException e) {
                e.printStackTrace();
            }
        }
    }

// TODO: 2019-11-07 I have no idea what to do :) Here i need to listen for a clients - they will search in the network an opened port by udp broadcast!

}
