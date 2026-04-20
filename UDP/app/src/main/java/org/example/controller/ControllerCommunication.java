package org.example.controller;

import org.example.model.Client;

public class ControllerCommunication {
    
    private Client client;

    public void defineNameUser(String name) {
        client = new Client(name);
    }

    public void startCommunication(String ip, int port) {
        client.startConnection(ip, port);
    }

    public void sendMessage(String msg) {
        client.sendMessage(msg);
    }
}
