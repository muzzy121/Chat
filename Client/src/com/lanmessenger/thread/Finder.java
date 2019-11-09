package com.lanmessenger.thread;

import java.io.IOException;
import java.net.*;
import java.util.*;

public class Finder implements Runnable {
    private boolean isStart = true;
    private List<InetAddress> broadcastAddresses = new LinkedList<>();
    private ServerList serverList;

    public Map<String, InetAddress> getServerList() {
        return serverList.getServerList();
    }

    public void stop() {
        this.isStart = false;
    }

    public Finder(ServerList serverList) {
        setBroadcastAddresses();
        this.serverList = serverList;
    }

    @Override
    public void run() {
        synchronized (serverList) {
            byte[] request = "secret_code".getBytes();
            byte[] response = new byte[15000];


            // TODO: 2019-11-07 For test broadcast address set for 192.168.88.255(wrong idea to send to everyone 255x4) - need to think how, and if this is possible to send broadcast from more than one interface!!!
            // TODO: 2019-11-08 Looks like I need to get all of interfaces, check IP's and then take a broadcast IP from every single Interface
            // TODO: 2019-11-08 Messege need to be checked on server so has to be identified by server that comes from my chat app

//            Collection<NetworkInterface> interfaces = null;
//            try {
//                interfaces = NetworkInterface.getNetworkInterfaces();  // Returns new type - Enumerations - check how this works
//            } catch (SocketException e) {
//                e.printStackTrace();
//            }

            for (InetAddress broadcastAddress : broadcastAddresses
            ) {
                try {
                    DatagramSocket datagramSocket = new DatagramSocket();
                    datagramSocket.setBroadcast(true);

                    DatagramPacket datagramPacket = new DatagramPacket(request, request.length, broadcastAddress, 7778);
                    datagramSocket.send(datagramPacket);

                    //For tests how InetAddress works!
//                    InetAddress inetAddress = InetAddress.getByName("192.168.2.2");
//                    System.out.println(inetAddress.getClass());
                    // ready to wait for response
                    // create new datagram to catch response

                    DatagramPacket toRecivePacket = new DatagramPacket(response, response.length);
                    datagramSocket.setSoTimeout(5000);
                    datagramSocket.receive(toRecivePacket);
                    String message = new String(toRecivePacket.getData()).trim();
                    //System.out.println("Found " + message + " at address: " + toRecivePacket.getAddress().getHostAddress());

                    serverList.put(message, toRecivePacket.getAddress());
                    //stop();


                } catch (SocketException e) {
                    e.printStackTrace();
                } catch (SocketTimeoutException ste) {
                    System.out.println("Couldn't find any Chat servers in Network");

                } catch (IOException e) {
                    e.printStackTrace();
                }
            }
            System.out.println("In Finder Thread: " + getServerList().keySet());
            serverList.notify();
        }
    }
        // TODO: 2019-11-07 Class which allows me to find server in LAN Network
    private void setBroadcastAddresses() {
        Enumeration networkInterfaces = null;
        try {
            networkInterfaces = NetworkInterface.getNetworkInterfaces();

            while (networkInterfaces.hasMoreElements()) {
                NetworkInterface networkInterface = (NetworkInterface) networkInterfaces.nextElement();
                if (networkInterface.isUp() && !networkInterface.isVirtual() && !networkInterface.isLoopback() && !networkInterface.isPointToPoint()) {
                    for (InterfaceAddress interfaceAddress :
                            networkInterface.getInterfaceAddresses()) {
                        InetAddress broadcast = interfaceAddress.getBroadcast();

                        if (broadcast != null)
//                            System.out.println(broadcast.getClass()); // Whats wrong with this!!!
//                            System.out.println(broadcast);
                            this.broadcastAddresses.add(broadcast);
                    }
                }
            }
            if (this.broadcastAddresses.isEmpty()) {
                System.out.println("No connected interfaces");
            }
        } catch (SocketException e) {
            System.out.println("No connected interfaces");
            e.printStackTrace();
        }
    }
}

