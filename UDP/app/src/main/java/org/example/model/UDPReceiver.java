package org.example.model;

import java.net.DatagramPacket;
import java.net.DatagramSocket;

public class UDPReceiver implements Runnable {

    private DatagramSocket socket;
    private boolean active;

    public UDPReceiver(DatagramSocket socket) {
        this.socket = socket;
        this.active = true;
    }

    @Override
    public void run() {

        try {
            while (active) {
                byte[] buffer = new byte[1024];
                DatagramPacket packet = new DatagramPacket(buffer, buffer.length);

                socket.receive(packet);

                String msg = new String(packet.getData());
                System.out.println("\n[" + packet.getAddress().getHostAddress() + "]: " + msg);

                if (msg.equals("EXIT")) {
                    active = false;
                }
            }
        } catch (Exception e) {
            System.out.println("Receiver cerrado.");
        }
    }

    public void stop() {
        this.active = false;
    }
}
