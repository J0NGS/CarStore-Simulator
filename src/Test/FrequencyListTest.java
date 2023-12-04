package src.Test;

import src.ED.LinkedList.FrequencyLinkedList;
import src.ED.LinkedList.Node;

public class FrequencyListTest {
    public static void main(String[] args) {
        FrequencyLinkedList<String, Integer> list = new FrequencyLinkedList<>();
        
        // Adicionando elementos à lista
        list.add("um", 1);
        list.add("dois", 2);
        list.add("tres", 3);
        
        // Acessando elementos para incrementar a frequência
        list.get("um");
        list.get("dois");        
        list.get("dois");
        list.get("dois");
        list.get("tres");
        list.get("tres");

        // Imprimindo detalhes dos nós
        System.out.println(list.getNodeDetails("um")); // Deve imprimir: Chave: um, Frequência: 1, Índice: 2
        System.out.println(list.getNodeDetails("dois")); // Deve imprimir: Chave: dois, Frequência: 3, Índice: 0
        System.out.println(list.getNodeDetails("tres")); // Deve imprimir: Chave: tres, Frequência: 2, Índice: 1

        // Removendo um elemento
        System.out.println("Removendo 'um': " + list.remove("um")); // Deve imprimir: Removendo 'um': true

        // Verificando o tamanho da lista após a remoção
        System.out.println("Tamanho após a remoção: " + list.size()); // Deve imprimir: Tamanho após a remoção: 2

        // Tentando remover um elemento que não existe na lista
        System.out.println("Removendo 'quatro': " + list.remove("quatro")); // Deve imprimir: Removendo 'quatro': false

        // Verificando o tamanho da lista após tentar remover um elemento que não existe
        System.out.println("Tamanho após tentar remover 'quatro': " + list.size()); // Deve imprimir: Tamanho após tentar remover 'quatro': 2

        // Iterando sobre a lista usando um loop for-each
        for (Node<String, Integer> node : list) {
            System.out.println(node); // Deve imprimir os detalhes de cada nó na lista
        }
    }
}

