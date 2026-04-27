package org.example.model;

import java.io.IOException;
import java.net.ServerSocket;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;

public class Server {
    private ServerSocket serverSocket;
    private ExecutorService pool;

    public Server() {
        this.pool = Executors.newFixedThreadPool(5);
    }

    public void startServer() {
        if (serverSocket != null) {
            try {
                pool.execute(new ClientHandler(serverSocket.accept()));
            } catch (IOException e) {
                pool.shutdown();
                System.out.println("Error al iniciar el server: " + e.getMessage());
            }
        } else System.out.println("Defina el puerto de comunicación del Servidor");
    }

    public void definePort(int port) {
        try {
            this.serverSocket = new ServerSocket(port);
        } catch (IOException e) {
            System.out.println("Error al definir el puerto de comunicación del Servidor: " + e.getMessage());
        }
    }
    
    
}
