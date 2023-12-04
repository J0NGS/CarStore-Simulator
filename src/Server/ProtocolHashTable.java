package src.Server;
import java.io.File;
import java.rmi.RemoteException;
import java.rmi.server.UnicastRemoteObject;
import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;

import src.ED.HashTable.HashTable;
import src.ED.HashTable.HashTableExtChaining;
import src.ED.LinkedList.Node;
import src.Server.Compression.Huffman.HuffmanCompress;
import src.Server.Compression.Huffman.Transport;
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

                    hashTable.insert(vehicle.getRenavam(), vehicle);
                    System.out.println("Veículo [" + vehicle.toString() + "] Inserido");
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
        //else
            //hashTable = new HashTableOpenAdress<>();
    }

    @Override
    public void insert(Character[] vehicle, HuffmanCompress compressor){
        try {
            String vehicleString = compressor.decompress(vehicle);
            Vehicle vehicleObject = new Vehicle();
            vehicleObject = vehicleObject.stringToVehicle(vehicleString);
            hashTable.insert(vehicleObject.getRenavam(), vehicleObject);
            System.out.println("Veículo [" + vehicleObject.toString() + "] Inserido");

            //Log
            System.out.println("Fator de carga ->" + hashTable.getLoadFactor());
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    @Override
    public Transport remove(Character[] renavam, HuffmanCompress compressor) {
        try {
            String renavamString = compressor.decompress(renavam);
            hashTable.delete(renavamString);
            System.out.println("Renavam[" + renavamString + "] Removido");
            //Log
            System.out.println("Fator de carga->" + hashTable.getLoadFactor());
            HuffmanCompress newCompressor = new HuffmanCompress();
            Transport transport = new Transport(newCompressor.compress("true"), newCompressor);
            return transport;
        } catch (Exception e) {
            e.printStackTrace();
            HuffmanCompress newCompressor = new HuffmanCompress();
            Transport transport = new Transport(newCompressor.compress("false"), newCompressor);
            return transport;
        }
    }


    @Override
    public Transport search(Character[] renavam, HuffmanCompress compressor) {
        try {
            String reanavamString = compressor.decompress(renavam);
            Vehicle vehicle = hashTable.searchByKey(reanavamString);
            if(vehicle == null)
                throw new Exception("Nenhum elemento encontrado na busca pelo Renavam[" + renavam + "]"); 
            HuffmanCompress newCompressor = new HuffmanCompress();
            Transport transport = new Transport(newCompressor.compress(vehicle.toString()), newCompressor);
            System.out.println("Elemento encontrado com renavam [ " + vehicle.getRenavam() + " ]\n" + "Contador de  frequência do node" +" [" + hashTable.getNode(reanavamString).getFrequency() +" ]");
            return transport;
        } catch (Exception e) {
            HuffmanCompress newCompressor = new HuffmanCompress();
            Transport transport = new Transport(newCompressor.compress(e.getMessage()), newCompressor);
            return transport;
        }
    }

    @Override
    public Transport searchByPlate(Character[] plate, HuffmanCompress compressor) {
        try {
            String plateString = compressor.decompress(plate);
            
            //Mais lento, percorro toda a hashtable para poder encontrar
            Node<String, Vehicle> nodeFound = hashTable.searchByAttribute(plateString, "carPlate");
            if (nodeFound == null)
                throw new Exception("Não encontrado nenhum valor");
            else{
                HuffmanCompress newCompressor = new HuffmanCompress();
                Transport transport = new Transport(newCompressor.compress(nodeFound.getValue().toString()), newCompressor);
                System.out.println("Elemento encontrado com placa [ " + plateString + " ]\n" + "Contador de  frequência do node" +" [" + 
                    hashTable.getNode(nodeFound.getValue().getRenavam()).getFrequency() +" ]");
                return transport;
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
        return null;
    }

    @Override
    public Transport size(){
        try {
            HuffmanCompress compressor = new HuffmanCompress();
            Transport transport = new Transport(compressor.compress(hashTable.getSize().toString()), compressor);
            return transport;
        } catch (Exception e) {
            e.printStackTrace();
            return null;
        }
    }

    @Override
    public void update(Character[] newVehicle, HuffmanCompress compressor) {
        try {
            String vehicleString = compressor.decompress(newVehicle);
            Vehicle vehicleObject = new Vehicle();
            vehicleObject = vehicleObject.stringToVehicle(vehicleString);

            // Garantindo que veículo existe
            if(hashTable.searchByKey(vehicleObject.getRenavam()) ==  null){
                throw new Exception("Nenhum veiculo encontrado com o renavam [" + vehicleObject.getRenavam()+ "]");
            } else{
                hashTable.searchByAttribute(vehicleObject.getRenavam(), "renavam").setValue(vehicleObject);
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
    }


    @Override
    public List<Transport> listAll(){
        try {
            List<Vehicle> vehicles = new ArrayList<>();
            List<Transport> transports = new ArrayList<>();
            for(Node<String, Vehicle> node : hashTable.getAllNodes()){
                vehicles.add(node.getValue());
            }
            if(vehicles.isEmpty())
                throw new Exception("Nennhum veiculo retornado");
            for(Vehicle node : vehicles){
                HuffmanCompress compressor = new HuffmanCompress();
                Transport trasport = search(compressor.compress(node.getRenavam().toString()), compressor);
                transports.add(trasport);
            }
            return transports;
        } catch (Exception e) {
            e.printStackTrace();
            return null;
        }
    }
}
