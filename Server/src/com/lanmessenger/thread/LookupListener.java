package com.lanmessenger.thread;


import java.io.IOException;
import java.net.*;

//Class for a socket Listener for broadcast pockets - used for check if server is available in the network
public class LookupListener implements Runnable{
    private boolean isStart = true;
    private DatagramSocket datagramSocket;
    private InetSocketAddress inetSocketAddress;
    private final String CODE = "secret_code";


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
                byte[] response = new byte[15000];  // create a response, which can handle packet ones will be get later in  datagramSocket.receive!
                DatagramPacket toRecivePacket = new DatagramPacket(response, response.length);
            try {
                System.out.println("Waiting for packet!");
                datagramSocket.receive(toRecivePacket);
                System.out.println("Recived pocket: " + toRecivePacket.getData());
                System.out.println("Recived from: "+ toRecivePacket.getAddress().getHostAddress());

                // TODO: 2019-11-07 See if I can catch broadcast packet!
                // TODO: 2019-11-07 Check if packet is fine and send back packet
                // TODO: 2019-11-07 In response send Servername!!!!

                if (CODE.equals(toRecivePacket.getData().toString().trim())) {
                    System.out.println("Data comes from chat app!");

                                            // if someone will send proper broadcast datagram i need to send him info that im alive, info who am i, and my ipaddress, cause he dont know it yet!

                    InetAddress clientAddress = toRecivePacket.getAddress();
                    byte[] request = "Server".getBytes();
                    DatagramPacket toSendPacket = new DatagramPacket(request, request.length, clientAddress, 7778);
                    datagramSocket.send(toSendPacket);
                }

            } catch (IOException e) {
                e.printStackTrace();
            }


        }
    }

// TODO: 2019-11-07 I have no idea what to do :) Here i need to listen for a clients - they will search in the network an opened port by udp broadcast!

}
