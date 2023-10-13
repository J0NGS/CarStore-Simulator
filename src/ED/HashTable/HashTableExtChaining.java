package src.ED.HashTable;

import java.lang.reflect.Field;
import java.util.ArrayList;
import java.util.LinkedList;
import java.util.List;

public class HashTableExtChaining<K, V> implements HashTable<K, V>{
    private static final int TABLE_SIZE = 101; // Tamanho inicial da tabela, levando em consideração que são 50 carros(50*2 e procura primo mais proximo)
    private LinkedList<Node<K, V>>[] table;

    public HashTableExtChaining() {
        // inicializa a tabela hash com listas encadeadas vazias
        table = new LinkedList[TABLE_SIZE];
        for (int i = 0; i < TABLE_SIZE; i++) {
            table[i] = new LinkedList<>();
        }
    }

    @Override
    public void insert(K key, V value) {
        // calcula o indice da tabela hash usando a função de dispersão
        int index = calculateIndex(key);

        // obtem a lista encadeada correspondente ao índice
        LinkedList<Node<K, V>> list = table[index];

        // verifica se já existe um nó com a mesma chave na lista
        for (Node<K, V> node : list) {
            if (node.getKey().equals(key)) {
                // Atualiza o valor se a chave já existe
                node.setValue(value);
                return; // sai da função após a atualização
            }
        }

        // caso contrário cria um novo no e adiciona a lista
        list.add(new Node<>(key, value));
    }

    @Override
    public void delete(K key) {
        // calcula o índice da tabela hash usando a função de dispersão
        int index = calculateIndex(key);

        // obtem a lista encadeada correspondente ao índice
        LinkedList<Node<K, V>> list = table[index];

        // procura o nó com a chave e o remove se existir
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
        // calcula o indice da tabela hash usando a funçao de dispersao
        int index = calculateIndex(key);

        // obtem a lista encadeada correspondente ao indice
        LinkedList<Node<K, V>> list = table[index];

        // procura o no com a chave e retorna o valor associado se existir
        for (Node<K, V> node : list) {
            if (node.getKey().equals(key)) {
                return node.getValue();
            }
        }

        // se não encontrar retorna null
        return null;
    }

    @Override
    public List<V> searchByValue(V value) {
        List<V> result = new LinkedList<>();

        // percorre todas as listas encadeadas na tabela hash
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

    // dispersão simples para calcular o indice da tabela hash
    private int calculateIndex(K key) {
        int hashCode = key.hashCode();
        return Math.abs(hashCode) % TABLE_SIZE;
    }

    @Override
    public Node<K, V> searchByAttribute(Object value, String attributeName) {
        // percorre todas as listas encadeadas na tabela hash
        for (LinkedList<Node<K, V>> list : table) {
            for (Node<K, V> node : list) {
                // obtem o valor do atributo do Value
                Object attributeValue = getAttributeValue(node.getValue(), attributeName);
    
                // verifica se o atributo corresponde ao valor desejado
                if (attributeValue != null && attributeValue.equals(value)) {
                    return node;
                }
            }
        }
    
        // Se não encontrar retorna null
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
    
    // metodo auxiliar para obter o valor de um atributo de um objeto
    private Object getAttributeValue(V value, String attributeName) {
        try {
            // Usa reflexao para obter o valor do atributo
            Field field = value.getClass().getDeclaredField(attributeName);
            field.setAccessible(true);
            return field.get(value);
        } catch (NoSuchFieldException | IllegalAccessException e) {
            e.printStackTrace();
            return null;
        }
    }
    
}
