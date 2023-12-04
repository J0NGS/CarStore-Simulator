package src.ED.LinkedList;
import java.util.Objects;

public class Node<K, V> {
    K key;
    V value;
    Node<K, V> next;
    int frequency;

    public Node(K key, V value) {
        this.key = key;
        this.value = value;
        this.next = null;
        this.frequency = 0;
    }


    public Node() {
    }

    public Node(K key, V value, Node<K,V> next, int frequency) {
        this.key = key;
        this.value = value;
        this.next = next;
        this.frequency = frequency;
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

    public int getFrequency() {
        return this.frequency;
    }

    public void setFrequency(int frequency) {
        this.frequency = frequency;
    }


    public Node<K,V> next(Node<K,V> next) {
        setNext(next);
        return this;
    }

    public Node<K,V> frequency(int frequency) {
        setFrequency(frequency);
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
        return Objects.equals(key, node.key) && Objects.equals(value, node.value) && Objects.equals(next, node.next) && frequency == node.frequency;
    }

    @Override
    public int hashCode() {
        return Objects.hash(key, value, next, frequency);
    }

    @Override
    public String toString() {
        return "{" +
            " key='" + getKey() + "'" +
            ", value='" + getValue() + "'" +
            ", next='" + getNext() + "'" +
            ", frequency='" + getFrequency() + "'" +
            "}";
    }
    
}

