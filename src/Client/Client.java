package src.Client;

import java.io.IOException;
import java.net.UnknownHostException;
import java.rmi.Naming;
import java.rmi.NotBoundException;
import java.util.Scanner;

import src.Server.Protocol;


public class Client {
    static Scanner scn = new Scanner(System.in);

    public static void main(String[] args) throws UnknownHostException, IOException, NotBoundException {
        String name = "rmi://localhost:1099/BDVehicle";
        Protocol server = (Protocol) Naming.lookup(name);
        int option;
        try {
            Menu menu = new Menu(server);
            do {
                menu.options();
                option = scn.nextInt();
                menu.run(option);
            } while (option != 0);
            
        } catch (Exception e) {
            e.printStackTrace();
        }

    }

}
