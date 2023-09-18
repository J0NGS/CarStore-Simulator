package src.HashTable;

import java.lang.reflect.Field;
import java.util.ArrayList;
import java.util.LinkedList;
import java.util.List;

public class HashTableExtChaining<K, V> implements HashTable<K, V>{
    private static final int TABLE_SIZE = 101; // Tamanho inicial da tabela, levando em consideração que são 50 carros(50*2 e procura primo mais proximo)
    private LinkedList<Node<K, V>>[] table;

    public HashTableExtChaining() {
        // Inicializa a tabela hash com listas encadeadas vazias
        table = new LinkedList[TABLE_SIZE];
        for (int i = 0; i < TABLE_SIZE; i++) {
            table[i] = new LinkedList<>();
        }
    }

    @Override
    public void insert(K key, V value) {
        // Calcula o índice da tabela hash usando a função de dispersão
        int index = calculateIndex(key);

        // Obtém a lista encadeada correspondente ao índice
        LinkedList<Node<K, V>> list = table[index];

        // Verifica se já existe um nó com a mesma chave na lista
        for (Node<K, V> node : list) {
            if (node.getKey().equals(key)) {
                // Atualiza o valor se a chave já existe
                node.setValue(value);
                return; // Sai da função após a atualização
            }
        }

        // Caso contrário, cria um novo nó e adiciona à lista
        list.add(new Node<>(key, value));
    }

    @Override
    public void delete(K key) {
        // Calcula o índice da tabela hash usando a função de dispersão
        int index = calculateIndex(key);

        // Obtém a lista encadeada correspondente ao índice
        LinkedList<Node<K, V>> list = table[index];

        // Procura o nó com a chave e o remove, se existir
        Node<K, V> nodeToRemove = null;
        for (Node<K, V> node : list) {
            if (node.getKey().equals(key)) {
                nodeToRemove = node;
                break;
            }
        }

        if (nodeToRemove != null) {
            list.remove(nodeToRemove);
        }
    }

    @Override
    public V searchByKey(K key) {
        // Calcula o índice da tabela hash usando a função de dispersão
        int index = calculateIndex(key);

        // Obtém a lista encadeada correspondente ao índice
        LinkedList<Node<K, V>> list = table[index];

        // Procura o nó com a chave e retorna o valor associado, se existir
        for (Node<K, V> node : list) {
            if (node.getKey().equals(key)) {
                return node.getValue();
            }
        }

        // Se não encontrar, retorna null
        return null;
    }

    @Override
    public List<V> searchByValue(V value) {
        List<V> result = new LinkedList<>();

        // Percorre todas as listas encadeadas na tabela hash
        for (LinkedList<Node<K, V>> list : table) {
            for (Node<K, V> node : list) {
                if (node.getValue().equals(value)) {
                    result.add(node.getValue());
                }
            }
        }

        return result;
    }

    @Override
    public double getLoadFactor() {
        int totalNodes = 0;
        for (LinkedList<Node<K, V>> list : table) {
            totalNodes += list.size();
        }

        return (double) totalNodes / TABLE_SIZE;
    }

    @Override
    public Integer getSize() {
        int totalNodes = 0;
        for (LinkedList<Node<K, V>> list : table) {
            totalNodes += list.size();
        }

        return totalNodes;
    }

    // Função de dispersão simples para calcular o índice da tabela hash
    private int calculateIndex(K key) {
        int hashCode = key.hashCode();
        return Math.abs(hashCode) % TABLE_SIZE;
    }

    @Override
    public Node<K, V> searchByAttribute(Object value, String attributeName) {
        // Percorre todas as listas encadeadas na tabela hash
        for (LinkedList<Node<K, V>> list : table) {
            for (Node<K, V> node : list) {
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
    public List<Node<K, V>> getAllNodes(){
        List<Node<K, V>> nodes = new ArrayList<>();
        for(LinkedList<Node<K, V>> list : table){
            for(Node<K, V> node : list){
                nodes.add(node);
            }
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
