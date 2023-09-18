package src.Test;

import src.HashTable.HashTableExtChaining;
import src.HashTable.HashTableOpenAdress;
import src.HashTable.Node;

public class TestSearchByAtribute { 
    public static void main(String[] args) {
        // Teste para HashTableExtChaining
        HashTableExtChaining<Integer, Car> extChainingTable = new HashTableExtChaining<>();
        extChainingTable.insert(1, new Car("Toyota", "Camry"));
        extChainingTable.insert(2, new Car("Honda", "Civic"));
        extChainingTable.insert(3, new Car("Ford", "Focus"));

        String attributeName = "model";
        String searchValue = "Civic";
        Node<Integer, Car> foundNode1 = extChainingTable.searchByAttribute(searchValue, attributeName);

        if (foundNode1 != null) {
            System.out.println("Found car with " + attributeName + " " + searchValue + ": " + foundNode1.getValue());
        } else {
            System.out.println("Car not found with " + attributeName + " " + searchValue);
        }

        // Teste para HashTableOpenAdress
        HashTableOpenAdress<Integer, Car> openAddressTable = new HashTableOpenAdress<>();
        openAddressTable.insert(1, new Car("Toyota", "Corolla"));
        openAddressTable.insert(2, new Car("Honda", "Accord"));
        openAddressTable.insert(3, new Car("Ford", "Fusion"));

        searchValue = "Accord";
        Node<Integer, Car> foundNode2 = openAddressTable.searchByValueAttribute(searchValue, attributeName);

        if (foundNode2 != null) {
            System.out.println("Found car with " + attributeName + " " + searchValue + ": " + foundNode2.getValue());
        } else {
            System.out.println("Car not found with " + attributeName + " " + searchValue);
        }
    }
}
