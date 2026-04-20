package org.example.model;

import java.io.IOException;
import java.net.DatagramPacket;
import java.net.DatagramSocket;
import java.net.InetAddress;

public class UDPConnection implements Runnable {
    
    private static UDPConnection instance;
    private DatagramSocket socket;

    private UDPConnection() {}

    public static UDPConnection getInstance() {
        if (instance == null) {
            instance = new UDPConnection();
        }
        return instance;
    }

    public void defineChannel(int port, String ip) {
        try {
            this.socket = new DatagramSocket(port);
            socket.connect(InetAddress.getByName(ip), port);

            System.out.println("Conectado con el servidor");
        } catch (IOException e) {
            System.out.println("Error al definir el puerto de comunicación " + e.getMessage());
        }
    }

    private void close() {
        this.socket.close();
    }

    @Override
    public void run() {

        try {
            byte [] buffer = new byte[1024];

            DatagramPacket packet = new DatagramPacket(buffer, buffer.length);

            socket.receive(packet);

            String msg = new String(packet.getData());
            System.out.println(msg);

            if (msg.equals("EXIT")) {
                close();
                System.out.println("Conexión finalizada");
            }
            
        } catch (Exception e) {
            System.out.println("Error durante la lectura de mensajes del socket");
        }
        
    }

    public void sendData(String msg) {

        try {
            DatagramPacket packet = new DatagramPacket(msg.getBytes(), msg.length());
            socket.send(packet);
        } catch (IOException e) {
            System.out.println("Error al enviar paquete de información " + e.getMessage());
        }
        
    }
}
