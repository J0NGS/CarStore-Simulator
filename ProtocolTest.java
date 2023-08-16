import Server.Protocol;
import Server.ProtocolImpl;
import entity.Driver;
import entity.Vehicle;

public class ProtocolTest {
    public static void main(String[] args) {
        try {
            // Criação do protocolo
            Protocol protocol = new ProtocolImpl();

            // Criação de alguns motoristas
            Driver driver1 = new Driver("John Doe", "12345678900");
            Driver driver2 = new Driver("Jane Smith", "98765432100");

            // Criação de alguns veículos
            Vehicle vehicle1 = new Vehicle("ABC123", "123456789", driver1, "Sedan", 2020);
            Vehicle vehicle2 = new Vehicle("XYZ789", "987654321", driver2, "SUV", 2018);

            // Inserção dos veículos
            protocol.insert(vehicle1);
            protocol.insert(vehicle2);

            // Listagem de todos os veículos
            System.out.println("Lista de veículos:");
            protocol.listAll();

            // Busca por renavam
            String searchRenavam = "123456789";
            Vehicle searchedVehicle = protocol.search(searchRenavam);
            if (searchedVehicle != null) {
                System.out.println("Veículo encontrado pelo renavam " + searchRenavam + ":");
                System.out.println(searchedVehicle);
            } else {
                System.out.println("Nenhum veículo encontrado com o renavam " + searchRenavam);
            }

            // Busca por placa
            String searchPlate = "XYZ789";
            Vehicle searchedByPlateVehicle = protocol.searchByPlate(searchPlate);
            if (searchedByPlateVehicle != null) {
                System.out.println("Veículo encontrado pela placa " + searchPlate + ":");
                System.out.println(searchedByPlateVehicle);
            } else {
                System.out.println("Nenhum veículo encontrado com a placa " + searchPlate);
            }

            // Atualização de um veículo
            Vehicle updatedVehicle = new Vehicle("ABC123", "123456789", driver1, "Coupe", 2022);
            protocol.update(updatedVehicle);

            // Listagem de todos os veículos após atualização
            System.out.println("Lista de veículos após atualização:");
            protocol.listAll();

            // Remoção de um veículo
            protocol.remove(vehicle2);

            // Listagem de todos os veículos após remoção
            System.out.println("Lista de veículos após remoção:");
            protocol.listAll();

            // Tamanho da árvore após as operações
            int treeSize = protocol.size();
            System.out.println("Tamanho da árvore: " + treeSize);
        } catch (Exception e) {
            e.printStackTrace();
        }
    }
}
