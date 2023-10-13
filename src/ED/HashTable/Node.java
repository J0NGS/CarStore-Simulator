package src.ED.HashTable;
import java.util.Objects;

public class Node <K, V>{
    private K key;
    private V value;
    Node<K,V> next;


    public Node() {
    }


    public Node(K key, V value, Node<K,V> next) {
        this.key = key;
        this.value = value;
        this.next = next;
    }
    
    public Node(K key, V value) {
        this.key = key;
        this.value = value;
    }

    public K getKey() {
        return this.key;
    }

    public void setKey(K key) {
        this.key = key;
    }

    public V getValue() {
        return this.value;
    }

    public void setValue(V value) {
        this.value = value;
    }

    public Node<K,V> getNext() {
        return this.next;
    }

    public void setNext(Node<K,V> next) {
        this.next = next;
    }

    public Node key(K key) {
        setKey(key);
        return this;
    }

    public Node value(V value) {
        setValue(value);
        return this;
    }

    public Node next(Node<K,V> next) {
        setNext(next);
        return this;
    }

    @Override
    public boolean equals(Object o) {
        if (o == this)
            return true;
        if (!(o instanceof Node)) {
            return false;
        }
        Node node = (Node) o;
        return Objects.equals(key, node.key) && Objects.equals(value, node.value) && Objects.equals(next, node.next);
    }

    @Override
    public int hashCode() {
        return Objects.hash(key, value, next);
    }

    @Override
    public String toString() {
        return "{" +
            " key='" + getKey() + "'" +
            ", value='" + getValue() + "'" +
            ", next='" + getNext() + "'" +
            "}";
    }
    
}
