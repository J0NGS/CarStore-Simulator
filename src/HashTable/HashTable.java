package src.HashTable;

import java.util.List;

public interface HashTable <K, V>{
    void insert(K key, V value);
    V searchByKey(K key);
    List<V>searchByValue(V value);
    Node<K, V> searchByAttribute(Object value, String attributeName);
    void delete(K key);
    double getLoadFactor();
}
