package src.ED.HashTable;

import java.util.List;

public interface HashTable <K, V>{
    void insert(K key, V value);
    V searchByKey(K key);
    List<V> searchByValue(V value);
    Node<K, V> searchByAttribute(Object value, String attributeName);
    List<Node<K,V>> getAllNodes(); 
    void delete(K key);
    double getLoadFactor();
    Integer getSize();
}
