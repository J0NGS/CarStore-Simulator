package src.Test;

import src.HashTable.HashTableOpenAdress;

public class HashTableOpenAdressTest {
    public static void main(String[] args) {
        HashTableOpenAdress<String, Integer> table = new HashTableOpenAdress<>();

        // Teste de inserção e busca
        table.insert("Key1", 10);
        table.insert("Key2", 20);

        System.out.println("Busca por Key1: " + table.searchByKey("Key1")); // Deve imprimir 10
        System.out.println("Busca por Key2: " + table.searchByKey("Key2")); // Deve imprimir 20
        System.out.println("Busca por Key3: " + table.searchByKey("Key3")); // Deve imprimir null

        // Teste de exclusão
        table.delete("Key1");
        System.out.println("Busca por Key1 após exclusão: " + table.searchByKey("Key1")); // Deve imprimir null
        System.out.println("Busca por Key2 após exclusão: " + table.searchByKey("Key2")); // Deve imprimir 20

        // Teste de busca por valor
        table.insert("Key3", 10);
        table.insert("Key4", 30);

        System.out.println("Busca por valor 10: " + table.searchByValue(10)); // Deve imprimir [10, 10]
        System.out.println("Busca por valor 20: " + table.searchByValue(20)); // Deve imprimir []

        // Teste do fator de carga
        System.out.println("Fator de carga: " + table.getLoadFactor()); // Deve imprimir próximo de 0.4
    }
}
