package src.Server;
import java.io.File;
import java.rmi.RemoteException;
import java.rmi.server.UnicastRemoteObject;
import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;

import src.HashTable.HashTable;
import src.HashTable.HashTableExtChaining;
import src.HashTable.HashTableOpenAdress;
import src.HashTable.Node;
import src.entity.Driver;
import src.entity.Vehicle;

public class ProtocolHashTable extends UnicastRemoteObject implements Protocol{
    private HashTable<String, Vehicle> hashTable;
    
    public enum HashTableType{
        EXTERNAL_CHAINING,
        OPEN_ADRESS
    }
    
    @Override
    public void init() throws RemoteException {
        String fileName = "E:/Documentos/Projetos/joao-goncalo-pratica-off-1-main/src/Server/carros.txt"; // Nome do arquivo de dados
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
    
    public ProtocolHashTable(HashTableType type) throws RemoteException{
        if(type.equals(HashTableType.EXTERNAL_CHAINING))
            hashTable = new HashTableExtChaining<>();
        else
            hashTable = new HashTableOpenAdress<>();
    }

    @Override
    public void insert(Vehicle vehicle){
        try {
            hashTable.insert(vehicle.getRenavam(), vehicle);
            System.out.println("Veículo [" + vehicle.toString() + "] Inserido");

            //Log
            System.out.println("Fator de carga ->" + hashTable.getLoadFactor());
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    @Override
    public boolean remove(Vehicle vehicle) {
        try {
            hashTable.delete(vehicle.getRenavam());
            System.out.println("Renavam[" + vehicle.getRenavam() + "] Removido");
            //Log
            System.out.println("Fator de carga->" + hashTable.getLoadFactor());
            return true;
        } catch (Exception e) {
            e.printStackTrace();
            return false;
        }
    }


    @Override
    public Vehicle search(String renavam) {
        try {
            Vehicle vehicle = hashTable.searchByKey(renavam);
            if(vehicle == null)
                throw new Exception("Nenhum elemento encontrado na busca pelo Renavam[" + renavam + "]"); 
            return vehicle;
        } catch (Exception e) {
            e.getMessage();
            return null;
        }
    }

    @Override
    public Vehicle searchByPlate(String plate) {
        try {
            //Mais lento, percorro toda a hashtable para poder encontrar
            Node<String, Vehicle> nodeFound = hashTable.searchByAttribute(plate, "carPlate");
            if (nodeFound == null)
                throw new Exception("Não encontrado nenhum valor");
            else
                return nodeFound.getValue();
        } catch (Exception e) {
            e.printStackTrace();
        }
        return null;
    }

    @Override
    public int size(){
        try {
            return hashTable.getSize();
        } catch (Exception e) {
            e.printStackTrace();
            return -1;
        }
    }

    @Override
    public void update(Vehicle newVehicle) {
        try {
            // Garantindo que veículo existe
            if(hashTable.searchByKey(newVehicle.getRenavam()) ==  null){
                throw new Exception("Nenhum veiculo encontrado com o renavam [" + newVehicle.getRenavam()+ "]");
            } else{
                hashTable.searchByAttribute(newVehicle.getRenavam(), "renavam").setValue(newVehicle);
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
    }


    @Override
    public List<Vehicle> listAll(){
        try {
            List<Vehicle> vehicles = new ArrayList<>();
            for(Node<String, Vehicle> node : hashTable.getAllNodes()){
                vehicles.add(node.getValue());
            }
            if(vehicles.isEmpty())
                throw new Exception("Nennhum veiculo retornado");
            return vehicles;
        } catch (Exception e) {
            e.printStackTrace();
            return new ArrayList<Vehicle>();
        }
    }


}
