package AVL;
/**
 * Classe para representar um nó de uma Árvore AVL
 * @author João Gonçalo
 */
public class Node <T> {
    private String Key;
    private Node<T> left, right;   // Ponteiro para os nós seguintes
    private int height;            // Altura do nó
    private T data;                // Elemento a ser guardado no nó




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
