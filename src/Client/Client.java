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
        //definindo endereço do servidor
        String name = "rmi://localhost:1099/BDVehicle";
        //Procurando servidor no endereço
        Protocol server = (Protocol) Naming.lookup(name);
        
        int option;
        try {
            //Inicializando a classe Menu
            Menu menu = new Menu(server);
            do {
                //Exibindo menu de opções
                menu.options();
                option = scn.nextInt();
                //Executando a opção
                menu.run(option);
            } while (option != 0);
        } catch (Exception e) {
            e.printStackTrace();
        }
    }
}
