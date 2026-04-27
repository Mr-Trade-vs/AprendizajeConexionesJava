package org.example.model;

import java.io.BufferedWriter;
import java.io.IOException;
import java.io.OutputStreamWriter;
import java.net.Socket;

public class Client {
    private Socket clientSocket;

    public Client(String ipServer, int port) {
        try {
            this.clientSocket = new Socket(ipServer, port);
        } catch (IOException e) {
            System.out.println("Error al inicializar le socket del cliente: " + e.getMessage());
        }
    }

    public void sendMessage(String msg) {
        try {
            BufferedWriter wr = new BufferedWriter(new OutputStreamWriter(clientSocket.getOutputStream()));
            wr.write(msg);
            wr.flush();
            if (msg.equals("EXIT")) {
                wr.close();
                clientSocket.close();
            }
        } catch (IOException e) {
            System.out.println("Error al enviar un mensaje al servidor: " + e.getMessage());
        }

    }
}
