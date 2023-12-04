package src.ED.LinkedList;

public class LinkedList<V> {
    private Node<Integer, V> head;

    public LinkedList() {
        this.head = null;
    }

    public void add(V value) {
        Node<Integer, V> newNode = new Node<>(size(), value);
        if (head == null) {
            head = newNode;
        } else {
            Node<Integer, V> temp = head;
            while (temp.getNext() != null) {
                temp = temp.getNext();
            }
            temp.setNext(newNode);
        }
    }

    public void remove(int key) {
        if (head == null) return;
        if (head.getKey().equals(key)) {
            head = head.getNext();
            return;
        }
        Node<Integer, V> current = head;
        while (current.getNext() != null && !current.getNext().getKey().equals(key)) {
            current = current.getNext();
        }
        if (current.getNext() != null) {
            current.setNext(current.getNext().getNext());
        }
    }

    public V get(int key) {
        Node<Integer, V> current = head;
        while (current != null) {
            if (current.getKey().equals(key)) {
                return current.getValue();
            }
            current = current.getNext();
        }
        return null;
    }

    public boolean isEmpty() {
        return head == null;
    }

    public int size() {
        int size = 0;
        Node<Integer, V> current = head;
        while (current != null) {
            size++;
            current = current.getNext();
        }
        return size;
    }

    public Node<Integer, V> getFirst() {
        return head;
    }

    public Node<Integer, V> getLast() {
        if (head == null) {
            return null;
        }
        Node<Integer, V> temp = head;
        while (temp.getNext() != null) {
            temp = temp.getNext();
        }
        return temp;
    }
}
