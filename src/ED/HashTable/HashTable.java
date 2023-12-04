package src.ED.HashTable;

import java.util.List;

import src.ED.LinkedList.FrequencyLinkedList;
import src.ED.LinkedList.Node;

public interface HashTable <K, V>{
    void insert(K key, V value);
    V searchByKey(K key);
    FrequencyLinkedList<K, V> searchByValue(V value);
    Node<K, V> searchByAttribute(Object value, String attributeName);
    FrequencyLinkedList<K, V> getAllNodes(); 
    void delete(K key);
    double getLoadFactor();
    Integer getSize();
    public Node<K, V> getNode(K key);
}
