package AVL;
/**
 * Classe que representar uma árvore AVL
 * @author João Gonçalo
 */
public class AVLTree <T extends Comparable> {
    private Node<T> root = null;


    public AVLTree() {
    }



    //
    private T max(Node<T> node){
        if(node.getRight()!= null){
            return min(node.getRight());
        }
        return node.getData();
    }

    //
    private T min(Node<T> node){
        if(node.getLeft()!= null){
            return min(node.getLeft());
        }
        return node.getData();
    }

    //Calcular fator de balanceamento
    private int FB(Node<T> node){
        if(node != null)
            return (height(node.getLeft()) - height(node.getRight()));
        else
            return 0;
    }


    //Metodo para receber a altura do nó
    private int height(Node<T> node){
        if(node != null)
            return node.getHeight();
        else
            return 0;
    }
}