package com.lanmessenger.thread;

import java.io.IOException;
import java.net.*;
import java.util.*;

public class Finder implements Runnable {
    private boolean isStart = true;
    private List<InetAddress> broadcastAddresses = new LinkedList<>();
    private ServerList serverList;

    public List<InetAddress> getServerList() {
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

            for (InetAddress broadcastAddress : broadcastAddresses
            ) {
                try {

//                    Inet4Address i4address = (Inet4Address) Inet4Address.getByName("localhost");
//                    InetAddress inetAddress = InetAddress.getByName("localhost");
//
//                    System.out.println("1: " +i4address.getAddress().getClass());
//                    System.out.println("2: " +i4address.getAddress());
//                    System.out.println("3: " +inetAddress.getAddress().getClass());
//                    System.out.println("4: " +inetAddress.getClass());

                    DatagramSocket datagramSocket = new DatagramSocket();
                    datagramSocket.setBroadcast(true);

                    DatagramPacket datagramPacket = new DatagramPacket(request, request.length, broadcastAddress, 7778);
                    datagramSocket.send(datagramPacket);

                    DatagramPacket toRecivePacket = new DatagramPacket(response, response.length);
                    datagramSocket.setSoTimeout(5000);
                    datagramSocket.receive(toRecivePacket);

                    String message = new String(toRecivePacket.getData()).trim(); // TODO: 2019-11-18 Verify if secret code is ok
                    serverList.add(toRecivePacket.getAddress());

                } catch (SocketException e) {
                    e.printStackTrace();
                } catch (SocketTimeoutException ste) {
                    System.out.println("Couldn't find any Chat servers in Network");
                } catch (
                        IOException e) {
                    e.printStackTrace();
                }
            }
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
                        Inet4Address broadcast = (Inet4Address)interfaceAddress.getBroadcast();

                        if (broadcast != null)
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

