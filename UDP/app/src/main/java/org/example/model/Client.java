package org.example.model;

public class Client {

    private String name;
    private UDPSender connection;

    public Client(String name) {
        this.name = name;
        this.connection = UDPSender.getInstance();
    }

    public void startConnection(String ip, int port) {
        connection.defineChannel(port, ip);

        UDPReceiver receiver = new UDPReceiver(connection.getSocket());
        new Thread(receiver).start();
    }

    public void sendMessage(String msg) {
        connection.sendData(msg);
    }

    public void endConnection() {
        connection.sendData("EXIT");
        connection.close();
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }
}
