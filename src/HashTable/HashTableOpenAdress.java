package src.HashTable;

import java.lang.reflect.Field;
import java.util.ArrayList;
import java.util.LinkedList;
import java.util.List;

public class HashTableOpenAdress<K, V> implements HashTable<K, V> {
    private static final int TABLE_SIZE = 101; // Tamanho inicial da tabela
    private Node<K, V>[] table;

    public HashTableOpenAdress() {
        table = new Node[TABLE_SIZE];
    }

    @Override
    public void insert(K key, V value) {
        int index = calculateIndex(key);

        // Sondagem linear para encontrar um slot vazio
        while (table[index] != null) {
            index = (index + 1) % TABLE_SIZE;
        }

        table[index] = new Node<>(key, value);
    }

    @Override
    public void delete(K key) {
        int index = calculateIndex(key);

        // Sondagem linear para encontrar o nó com a chave
        while (table[index] != null) {
            if (table[index].getKey().equals(key)) {
                table[index] = null; // Marca o slot como vazio
                return;
            }
            index = (index + 1) % TABLE_SIZE;
        }
    }

    @Override
    public V searchByKey(K key) {
        int index = calculateIndex(key);

        // Sondagem linear para encontrar o nó com a chave
        while (table[index] != null) {
            if (table[index].getKey().equals(key)) {
                return table[index].getValue();
            }
            index = (index + 1) % TABLE_SIZE;
        }

        // Se não encontrar, retorna null
        return null;
    }

    @Override
    public List<V> searchByValue(V value) {
        List<V> result = new LinkedList<>();

        for (Node<K, V> node : table) {
            if (node != null && node.getValue().equals(value)) {
                result.add(node.getValue());
            }
        }

        return result;
    }

    @Override
    public double getLoadFactor() {
        int totalNodes = 0;
        for (Node<K, V> node : table) {
            if (node != null) {
                totalNodes++;
            }
        }

        return (double) totalNodes / TABLE_SIZE;
    }
    
    @Override
    public Integer getSize() {
        int totalNodes = 0;
        for (Node<K, V> node : table) {
            if (node != null) {
                totalNodes++;
            }
        }

        return totalNodes;
    }


    private int calculateIndex(K key) {
        int hashCode = key.hashCode();
        return Math.abs(hashCode) % TABLE_SIZE;
    }

    @Override
    public Node<K, V> searchByAttribute(Object value, String attributeName) {
        // Percorre a tabela hash
        for (Node<K, V> node : table) {
            if (node != null) {
                // Obtem o valor do atributo do Value (assumindo que o Value é uma classe com esse atributo)
                Object attributeValue = getAttributeValue(node.getValue(), attributeName);
    
                // Verifica se o atributo corresponde ao valor desejado
                if (attributeValue != null && attributeValue.equals(value)) {
                    return node;
                }
            }
        }
    
        // Se não encontrar, retorna null
        return null;
    }

    @Override
    public List<Node<K,V>> getAllNodes(){
        List<Node<K,V>> nodes = new ArrayList<>();
        for(Node<K, V> node:table){
            nodes.add(node);
        }
        return nodes;
    }
    
    // Método auxiliar para obter o valor de um atributo de um objeto
    private Object getAttributeValue(V value, String attributeName) {
        try {
            // Usa reflexão para obter o valor do atributo
            Field field = value.getClass().getDeclaredField(attributeName);
            field.setAccessible(true);
            return field.get(value);
        } catch (NoSuchFieldException | IllegalAccessException e) {
            // Trate exceções, se necessário
            e.printStackTrace();
            return null;
        }
    }
    
}
