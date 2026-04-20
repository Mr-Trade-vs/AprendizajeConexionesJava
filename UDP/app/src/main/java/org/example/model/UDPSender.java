package org.example.model;

import java.io.IOException;
import java.net.DatagramPacket;
import java.net.DatagramSocket;
import java.net.InetAddress;

public class UDPSender {

    private static UDPSender instance;
    private DatagramSocket socket;
    private InetAddress targetAddress;
    private int targetPort;

    private UDPSender() {}

    public static UDPSender getInstance() {
        if (instance == null) {
            instance = new UDPSender();
        }
        return instance;
    }

    public void defineChannel(int port, String ip) {
        try {
            this.socket = new DatagramSocket(port);
            this.targetAddress = InetAddress.getByName(ip);
            this.targetPort = port;

            System.out.println("Canal abierto en puerto " + port + ". Enviando a " + ip);
        } catch (IOException e) {
            System.out.println("Error al definir el canal: " + e.getMessage());
        }
    }

    public DatagramSocket getSocket() {
        return socket;
    }

    public void sendData(String msg) {
        try {
            byte[] data = msg.getBytes();
            DatagramPacket packet = new DatagramPacket(data, data.length, targetAddress, targetPort);
            socket.send(packet);
        } catch (IOException e) {
            System.out.println("Error al enviar paquete: " + e.getMessage());
        }
    }

    public void close() {
        if (socket != null && !socket.isClosed()) {
            socket.close();
        }
    }
}
