package src.ED.HashTable;


import src.ED.LinkedList.FrequencyLinkedList;
import src.ED.LinkedList.Node;
import java.lang.reflect.Field;

public class HashTableExtChaining<K, V> implements HashTable<K, V>{
    private static final int TABLE_SIZE = 101; // Tamanho inicial da tabela, levando em consideração que são 50 carros(50*2 e procura primo mais proximo)
    private FrequencyLinkedList<K, V>[] table;

    public HashTableExtChaining() {
        // inicializa a tabela hash com listas encadeadas vazias
        table = new FrequencyLinkedList[TABLE_SIZE];
        for (int i = 0; i < TABLE_SIZE; i++) {
            table[i] = new FrequencyLinkedList<>();
        }
    }

    @Override
    public void insert(K key, V value) {
        // calcula o indice da tabela hash usando a função de dispersão
        int index = calculateIndex(key);

        // obtem a lista encadeada correspondente ao índice
        FrequencyLinkedList<K, V> list = table[index];

        // verifica se já existe um nó com a mesma chave na lista
        for (Node<K, V> node : list) {
            if (node.getKey().equals(key)) {
                // Atualiza o valor se a chave já existe
                node.setValue(value);
                return; // sai da função após a atualização
            }
        }

        // caso contrário cria um novo no e adiciona a lista
        list.add(key, value);
    }

    @Override
    public void delete(K key) {
        // calcula o índice da tabela hash usando a função de dispersão
        int index = calculateIndex(key);

        // obtem a lista encadeada correspondente ao índice
        FrequencyLinkedList<K, V> list = table[index];

        // procura o nó com a chave e o remove se existir
        Node<K, V> nodeToRemove = null;
        for (Node<K, V> node : list) {
            if (node.getKey().equals(key)) {
                nodeToRemove = node;
                break;
            }
        }

        if (nodeToRemove != null) {
            list.remove(nodeToRemove.getKey());
        }
    }

    @Override
    public V searchByKey(K key) {
        try {
            // calcula o indice da tabela hash usando a funçao de dispersao
            int index = calculateIndex(key);
    
            // obtem a lista encadeada correspondente ao indice
            FrequencyLinkedList<K, V> list = table[index];
    
            V value = list.get(key);
            
            if(value == null)
                throw new Exception("Nada encontrado");
            else
                return value;
        } catch (Exception e) {
            e.printStackTrace();
            return null;
            // se não encontrar retorna null
        }
            
    }

    @Override
    public FrequencyLinkedList<K, V> searchByValue(V value) {
        FrequencyLinkedList<K, V> result =new FrequencyLinkedList<>();

        // percorre todas as listas encadeadas na tabela hash
        for (FrequencyLinkedList<K, V> list : table) {
            for (Node<K, V> node : list) {
                if (node.getValue().equals(value)) {
                    result.add(node.getKey(), node.getValue());
                }
            }
        }
        return result;
    }

    @Override
    public double getLoadFactor() {
        int totalNodes = 0;
        for (FrequencyLinkedList<K, V> list : table) {
            totalNodes += list.size();
        }

        return (double) totalNodes / TABLE_SIZE;
    }

    @Override
    public Integer getSize() {
        int totalNodes = 0;
        for (FrequencyLinkedList<K, V> list : table) {
            totalNodes += list.size();
        }

        return totalNodes;
    }

    // dispersão DJB2
    private int calculateIndex(K key) {
        int hash = 5381;
        for (int i = 0; i < 11; i++) {
            char character = ((String) key).charAt(i);
            hash = ((hash << 5) + hash) + character;
        }
        // Certifique-se de que o valor hash é positivo
        if (hash < 0) {
            hash = -hash;
        }
        return hash % TABLE_SIZE;
    }

    @Override
    public Node<K, V> searchByAttribute(Object value, String attributeName) {
        // percorre todas as listas encadeadas na tabela hash
        for (FrequencyLinkedList<K, V> list : table) {
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
    public FrequencyLinkedList<K, V> getAllNodes(){
        FrequencyLinkedList<K, V> nodes = new FrequencyLinkedList<>();
        for(FrequencyLinkedList<K, V> list : table){
            for(Node<K, V> node : list){
                nodes.add(node.getKey(), node.getValue());
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
    
    @Override
    public Node<K, V> getNode(K key) {
        // calcula o inndice da tabela hash usando a funçao de dispersao
        int index = calculateIndex(key);
    
        // obtem a lista encadeada correspondente ao índice
        FrequencyLinkedList<K, V> list = table[index];
    
        // procura o no com a chave e o retorna se existir
        for (Node<K, V> node : list) {
            if (node.getKey().equals(key)) {
                return node;
            }
        }
    
        // se não encontrar, retorna null
        return null;
    }
}
