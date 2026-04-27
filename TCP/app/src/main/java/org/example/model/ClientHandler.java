package org.example.model;

import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.OutputStreamWriter;
import java.net.Socket;

public class ClientHandler implements Runnable  {
    private Socket clientSocket;
    private BufferedWriter wr;
    private BufferedReader rd;

    
    public ClientHandler(Socket clientSocket) {
        this.clientSocket = clientSocket;
        try {
            this.wr = new BufferedWriter(new OutputStreamWriter(clientSocket.getOutputStream()));
            this.rd = new BufferedReader(new InputStreamReader(clientSocket.getInputStream()));            
        } catch (Exception e) {
            System.out.println("Error al inicializar el escritor y lector del socket: " + e.getMessage());
        }
    }

    private void startCommunication() {
        String msg;
        try {
            while ((msg = rd.readLine()) != null) {
                if (msg.equals("EXIT")) endCommunication();

                System.out.println(msg);
                wr.write("De momento no contamos con comando de respuesta");
                wr.flush();
            }
        } catch (Exception e) {
            System.out.println("Error al definir un puente de comunicación: " + e.getMessage());
        }

    }

    private void endCommunication() {
        try {
            wr.flush();
            wr.close();
            rd.close();
            clientSocket.close();
        } catch (IOException e) {
            System.out.println("Error al cerrar el puente de comunicación: " + e.getMessage());
        }
 
    }


    @Override
    public void run() {
        startCommunication();
    }
}
