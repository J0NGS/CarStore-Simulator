import AVL.AVLTree;
import entity.Driver;
import entity.Vehicle;

public class testTree {
    public static void main(String[] args) {
        AVLTree<Vehicle> vehicleTree = new AVLTree<>();

        // Criando veículos
        Vehicle vehicle1 = new Vehicle("ABC123", "12345678901", new Driver("John", "123456789"), "Sedan", 2020);
        Vehicle vehicle2 = new Vehicle("XYZ789", "98765432101", new Driver("Jane", "987654321"), "SUV", 2022);
        Vehicle vehicle3 = new Vehicle("DEF456", "45678901201", new Driver("Alice", "456789012"), "Hatchback", 2018);

        // Inserindo veículos na árvore
        vehicleTree.insert(vehicle1);
        vehicleTree.insert(vehicle2);
        vehicleTree.insert(vehicle3);

        // Exibindo árvore em ordens diferentes
        System.out.println("In-Order Traversal:");
        vehicleTree.inOrder();

        // Buscando veículo na árvore
        System.out.println("\nSearching for a vehicle:");
        Vehicle searchVehicle = new Vehicle("", "45678901200", null, "", 0);
        boolean found = vehicleTree.search(searchVehicle);
        System.out.println("Vehicle found: " + found);
    }
}

