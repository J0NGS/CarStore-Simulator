package src.AVL;
/**
 * Classe para representar um no de uma Arvore AVL
 * @author João Gonçalo
 */
public class Node <T> {
    private Node<T> left, right;   // Ponteiro para os nos seguintes
    private int height;            // Altura do no
    private T data;                // Elemento a ser guardado no no

    public Node(T data) {
        this.left = null;  
        this.right = null;
        this.height = 1;
        this.data = data;
    }


    //Getters & Setters
    public Node<T> getLeft() {
        return this.left;
    }

    public void setLeft(Node<T> left) {
        this.left = left;
    }

    public Node<T> getRight() {
        return this.right;
    }

    public void setRight(Node<T> right) {
        this.right = right;
    }

    public int getHeight() {
        return this.height;
    }

    public void setHeight(int height) {
        this.height = height;
    }

    public T getData() {
        return this.data;
    }

    public void setData(T data) {
        this.data = data;
    }

}
