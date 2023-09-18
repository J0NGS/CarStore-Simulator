package src.Server;

import java.io.BufferedReader;
import java.io.File;
import java.io.FileReader;
import java.rmi.RemoteException;
import java.rmi.server.UnicastRemoteObject;
import java.util.List;
import java.util.Scanner;

import src.AVL.AVLTree;
import src.entity.Driver;
import src.entity.Vehicle;


public class ProtocolAVL extends UnicastRemoteObject implements Protocol {
    private AVLTree <Vehicle> tree;
    

    public ProtocolAVL() throws RemoteException {
        this.tree = new AVLTree<>();
    }


    // Função para inicializar os dados a partir do arquivo
    public void init() {
        String fileName = "E:/Documentos/GitHub/joao-goncalo-pratica-off-1-main/src/Server/carros.txt"; // Nome do arquivo de dados
        int linesToRead = 50; // Número de linhas a serem lidas

        try (Scanner scanner = new Scanner(new File(fileName))) {
            int linesRead = 0;

            while (scanner.hasNextLine() && linesRead < linesToRead) {
                String line = scanner.nextLine();
                //Separando string por separador
                String[] strings = line.split("\\|");
                
                //Instanciando veículo a partir das string
                if (strings.length == 6) {
                    String carPlate = strings[0];
                    String renavam = strings[1];
                    String driverName = strings[2];
                    String driverCpf = strings[3];
                    String model = strings[4];
                    int yearProduction = Integer.parseInt(strings[5]);

                    Vehicle vehicle = new Vehicle(carPlate, renavam, new Driver(driverName, driverCpf), model, yearProduction);

                    insert(vehicle);
                    linesRead++;
                }
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    @Override
    public void insert(Vehicle vehicle) {
        try {
            this.tree.insert(vehicle);
            System.out.println("Renavam[" + vehicle.getRenavam() + "]");
            System.out.println("Altura da arvore[" + this.tree.getTreeHeight() + "]");
        } catch (Exception e) {
            e.printStackTrace();
        }
    }


    @Override
    public boolean remove(Vehicle vehicle) {
        try {
            this.tree.remove(vehicle);
            System.out.println("Renavam[" + vehicle.getRenavam() + "]");
            System.out.println("Altura da arvore[" + this.tree.getTreeHeight() + "]");
            return true;
        } catch (Exception e) {
            e.printStackTrace();
            return false;
        }
    }


    @Override
    public List<Vehicle> listAll() {
        return this.tree.getAllData();        
    }


    @Override
    public Vehicle search(String renavam) {
        try {
            Vehicle vehicle = new Vehicle(renavam);
            vehicle = this.tree.search(vehicle);
            if(vehicle == null)
                throw new Exception("Nenhum elemento encontrado na busca pelo Renavam[" + renavam + "]"); 
            return vehicle;
        } catch (Exception e) {
            e.printStackTrace();
        }
        return null;
    }


    @Override
    public Vehicle searchByPlate(String plate) {
        try {
            //Mais lento, utilizo lista pois a minha ordenação é baseada no renavam
            List<Vehicle> list = this.tree.getAllData();
            Vehicle vehicle = null;
            for(int i = 0; i < list.size(); i++){
                if(list.get(i).getCarPlate().equals(plate))
                    vehicle = list.get(i);
                }
            if(vehicle == null)
                System.out.println("Nenhum veículo encontrado na busca pela placa[" + plate + "]");
            return vehicle;
        } catch (Exception e) {
            e.printStackTrace();
        }
        return null;
    }


    @Override
    public void update(Vehicle newVehicle) {
        try {
            // Garantindo que veículo existe
            if(this.tree.search(newVehicle) ==  null){
                throw new Exception("Nenhum veiculo encontrado com o renavam [" + newVehicle.getRenavam()+ "]");
            } else{
                //Gambiarra? eu acho que não.
                remove(newVehicle);
                insert(newVehicle);
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
    }


    @Override
    public int size() {
        return this.tree.countNodes();
    }    
}
