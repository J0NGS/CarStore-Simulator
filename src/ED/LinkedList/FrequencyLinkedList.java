package src.ED.LinkedList;

import java.util.Iterator;

public class FrequencyLinkedList<K, V> implements Iterable<Node<K, V>> {
    private Node<K, V> head; // Referência para o primeiro nó da lista
    private int size; // Tamanho da lista

    public FrequencyLinkedList() {
        this.head = null;
        this.size = 0;
    }

    public V get(K key) {
        if (head == null) {
            return null; // Lista vazia, chave não encontrada.
        }

        Node<K, V> prev = null;
        Node<K, V> current = head;

        while (current != null) {
            if (current.getKey().equals(key)) {
                current.setFrequency(current.getFrequency() + 1);
                if (prev != null) {
                    prev.setNext(current.getNext());
                    adjustNode(current);
                }
                return current.getValue();
            }
            prev = current;
            current = current.getNext();
        }

        return null; // Chave não encontrada.
    }

    public void add(K key, V value) {
        Node<K, V> node = new Node<>(key, value);
        node.setNext(head);
        head = node;
        size++;
    }

    public boolean remove(K key) {
        if (head == null) {
            return false; // Lista vazia, remoção impossível.
        }

        if (head.getKey().equals(key)) {
            head = head.getNext();
            size--;
            return true;
        }

        Node<K, V> prev = head;
        Node<K, V> current = head.getNext();

        while (current != null) {
            if (current.getKey().equals(key)) {
                prev.setNext(current.getNext());
                size--;
                return true;
            }
            prev = current;
            current = current.getNext();
        }

        return false; // Chave não encontrada na lista.
    }

    public int size() {
        return size;
    }

    private void adjustNode(Node<K, V> node) {
        if (head == null || head.getFrequency() >= node.getFrequency()) {
            node.setNext(head);
            head = node;
            return;
        }

        Node<K, V> current = head;

        while (current.getNext() != null && current.getNext().getFrequency() < node.getFrequency()) {
            current = current.getNext();
        }
        node.setNext(current.getNext());
        current.setNext(node);
    }

    public String getNodeDetails(K key) {
        Node<K, V> current = head;
        int index = 0;

        while (current != null) {
            if (current.getKey().equals(key)) {
                return "Chave: " + key + ", Frequencia: " + current.getFrequency() + ", Indice: " + index;
            }
            current = current.getNext();
            index++;
        }

        return "Chave nao encontrada na lista.";
    }

    @Override
    public Iterator<Node<K, V>> iterator() {
        return new Iterator<Node<K, V>>() {
            private Node<K, V> current = head;

            @Override
            public boolean hasNext() {
                return current != null;
            }

            @Override
            public Node<K, V> next() {
                Node<K, V> node = current;
                current = current.getNext();
                return node;
            }
        };
    }

    public Node<K, V> getFirst() {
        return head;
    }

    public Node<K, V> getLast() {
        Node<K, V> node = head;
        while (node.getNext() != null) {
            node = node.getNext();
        }
        return node;
    }

    public boolean isEmpty() {
        return size() == 0;
    }
}
