package org.example.view;

import java.util.Scanner;

import org.example.controller.ControllerCommunication;

public class App {

    public static void main(String[] args) {

        ControllerCommunication controller = new ControllerCommunication();
        Scanner sc = new Scanner(System.in);

        System.out.println("¿Que nombre recibira el equipo?");
        String name = sc.nextLine();
        controller.defineNameUser(name);

        System.out.println("¿Con quien vas a conectarte?\nIp: ");
        String ip = sc.nextLine();

        System.out.println("Puerto: ");
        int port = sc.nextInt();
        sc.nextLine();

        controller.startCommunication(ip, port);

        System.out.println("Listo. Escribe tus mensajes (EXIT para salir):\n");

        String answer = "";

        while (!answer.equals("EXIT")) {
            answer = sc.nextLine();
            controller.sendMessage(answer);
        }

        controller.endCommunication();
        sc.close();
    }
}
