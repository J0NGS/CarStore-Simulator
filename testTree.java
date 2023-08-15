import AVL.AVLTree;

public class testTree {
    public static void main(String[] args) {
        AVLTree<Integer> avlTree = new AVLTree<>();
        
        // Inserindo elementos na árvore
        avlTree.insert(10);
        avlTree.insert(20);
        avlTree.insert(5);
        avlTree.insert(15);
        avlTree.insert(25);
        
        // Exibindo árvore em ordens diferentes
        System.out.println("In-Order Traversal:");
        avlTree.inOrder();
        
        System.out.println("\nPre-Order Traversal:");
        avlTree.preOrder();
        
        System.out.println("\nPost-Order Traversal:");
        avlTree.postOrder();
        
        // Removendo elementos da árvore
        avlTree.remove(10);
        avlTree.remove(25);
        
        // Exibindo árvore após remoção
        System.out.println("\nIn-Order Traversal after removal:");
        avlTree.inOrder();
    }
}

