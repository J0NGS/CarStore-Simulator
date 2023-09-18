package src.Test;

import src.Server.ProtocolHashTable;
import src.entity.Vehicle;
import src.entity.Driver;

public class ProtocolHashTableTest {
    public static void main(String[] args) {
        // Create a ProtocolHashTable instance using External Chaining
        ProtocolHashTable externalChainingHashTable = new ProtocolHashTable(ProtocolHashTable.HashTableType.EXTERNAL_CHAINING);

        // Create some Vehicle objects
        Vehicle vehicle1 = new Vehicle("ABC1234", "12345678901", new Driver("John Doe", "12345678901"), "Sedan", 2022);
        Vehicle vehicle2 = new Vehicle("XYZ5678", "23456789012", new Driver("Jane Smith", "23456789012"), "SUV", 2023);
        Vehicle vehicle3 = new Vehicle("DEF9876", "34567890123", new Driver("Bob Johnson", "34567890123"), "Truck", 2021);

        // Insert vehicles into the hash table
        externalChainingHashTable.insert(vehicle1);
        externalChainingHashTable.insert(vehicle2);
        externalChainingHashTable.insert(vehicle3);

        // Search for a vehicle by renavam
        String renavamToSearch = "23456789012";
        Vehicle foundVehicle = externalChainingHashTable.search(renavamToSearch);
        if (foundVehicle != null) {
            System.out.println("Found vehicle by renavam: " + foundVehicle);
        } else {
            System.out.println("Vehicle not found by renavam: " + renavamToSearch);
        }

        // Remove a vehicle
        boolean removed = externalChainingHashTable.remove(vehicle2);
        if (removed) {
            System.out.println("Vehicle removed successfully: " + vehicle2);
        } else {
            System.out.println("Failed to remove vehicle: " + vehicle2);
        }

        // List all vehicles
        System.out.println("List of all vehicles:");
        for (Vehicle vehicle : externalChainingHashTable.listAll()) {
            System.out.println(vehicle);
        }

        // Get the size of the hash table
        int size = externalChainingHashTable.size();
        System.out.println("Size of the hash table: " + size);
    }
}

