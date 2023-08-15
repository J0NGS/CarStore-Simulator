import AVL.AVLTree;

public class testTree {
    public static void main(String[] args) {
        AVLTree<Integer> avlTree = new AVLTree<>();

        avlTree.insert(10);
        avlTree.insert(20);
        avlTree.insert(5);
        avlTree.insert(3);
        avlTree.insert(15);

        System.out.println("In-order traversal:");
        avlTree.inOrder();

        System.out.println("Pre-order traversal:");
        avlTree.preOrder();

        System.out.println("Post-order traversal:");
        avlTree.postOrder();

        System.out.println("Height of the tree: " + avlTree.height());

        System.out.println("Searching for value 5: " + avlTree.search(5));

        avlTree.remove(10);

        System.out.println("In-order traversal after removing 10:");
        avlTree.inOrder();
    }
}

