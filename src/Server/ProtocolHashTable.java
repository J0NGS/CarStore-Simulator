package src.Server;
import java.io.File;
import java.util.List;
import java.util.Optional;
import java.util.Scanner;

import src.HashTable.HashTable;
import src.HashTable.HashTableExtChaining;
import src.HashTable.HashTableOpenAdress;
import src.HashTable.Node;
import src.entity.Driver;
import src.entity.Vehicle;

public class ProtocolHashTable implements Protocol{
    private HashTable<String, Vehicle> hashTable;

    public enum HashTableType{
        EXTERNAL_CHAINING,
        OPEN_ADRESS
    }

    public ProtocolHashTable(HashTableType type){
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
            System.out.println("Fator de carga" + hashTable.getLoadFactor());
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
            System.out.println("Fator de carga" + hashTable.getLoadFactor());
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

}
