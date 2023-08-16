package protocol;

import entity.Vehicle;

public class Protocol {
    // Tipos de operações
    public static final int INSERT = 1;
    public static final int LIST_ALL = 2;
    public static final int UPDATE = 3;
    public static final int REMOVE = 4;
    public static final int COUNT = 5;
    public static final int SEARCH_BY_RENAVAM = 6;
    public static final int SEARCH_BY_PLATE = 7;

    // Delimitador usado para separar os campos nas mensagens
    private static final String DELIMITER = " | ";

    // Função para criar uma mensagem de inserção
    public static String createInsertMessage(Vehicle vehicle) {
        return String.format("%d%s%s%s%s%s%s%s%s%s%s%d",
                INSERT, DELIMITER,
                vehicle.getCarPlate(), DELIMITER,
                vehicle.getRenavam(), DELIMITER,
                vehicle.getDriver().getName(), DELIMITER,
                vehicle.getDriver().getCpf(), DELIMITER,
                vehicle.getModel(), DELIMITER,
                vehicle.getYearProduction()
        );
    }

    // Função para criar uma mensagem de listagem
    public static String createListAllMessage() {
        return String.valueOf(LIST_ALL);
    }

    // Função para criar uma mensagem de atualização
    public static String createUpdateMessage(String renavamSearch, Vehicle newVehicle) {
        return String.format("%d%s%s%s%s%s%s%s%s%s%s%s%s%s%s%s%d",
                UPDATE, DELIMITER,
                renavamSearch, DELIMITER,
                newVehicle.getCarPlate(), DELIMITER,
                newVehicle.getDriver().getName(), DELIMITER,
                newVehicle.getDriver().getCpf(), DELIMITER,
                newVehicle.getModel(), DELIMITER,
                newVehicle.getYearProduction()
        );
    }

    // Função para criar uma mensagem de remoção
    public static String createRemoveMessage(String renavamSearch) {
        return String.format("%d%s%s", REMOVE, DELIMITER, renavamSearch);
    }

    // Função para criar uma mensagem de contagem
    public static String createCountMessage() {
        return String.valueOf(COUNT);
    }

    // Função para criar uma mensagem de busca por renavam
    public static String createSearchByRenavamMessage(String renavamSearch) {
        return String.format("%d%s%s", SEARCH_BY_RENAVAM, DELIMITER, renavamSearch);
    }

    // Função para criar uma mensagem de busca por placa
    public static String createSearchByPlateMessage(String plateSearch) {
        return String.format("%d%s%s", SEARCH_BY_PLATE, DELIMITER, plateSearch);
    }

    // Função para obter o tipo de operação a partir de uma mensagem
    public static int getOperationType(String message) {
        return Integer.parseInt(message.split(DELIMITER)[0]);
    }
}

