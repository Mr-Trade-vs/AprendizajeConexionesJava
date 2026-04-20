package org.example.model;

public class Client {
    private String name;
    private UDPConnection connection;

    public Client(String name) {
        this.name = name;
        this.connection = UDPConnection.getInstance();
    }

    public void startConnection(String ip, int port) {
        this.connection.defineChannel(port, ip);
        connection.run();
    }

    public void sendMessage(String msg) {
        connection.sendData(msg);
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }
}
