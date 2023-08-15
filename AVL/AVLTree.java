package AVL;
/**
 * Classe que representar uma árvore AVL
 * @author João Gonçalo
 */
public class AVLTree <T extends Comparable> {
    private Node<T> root = null;
    
    
    public AVLTree() {
    }
    
    // Metodo para saber se a arvore esta vazia
    public boolean isEmpty(){
        // se a árvore está vazia, então sua raiz é nula
        return root == null;
    } 
    

    // Metodo para inserir um elemento na arvore
    public AVLTree<T> insert(T data){
        root = insert(data, root);
        System.out.println("Add...");
        return this;
    }

    // Metoto para remover um elemento
    public AVLTree<T> remove(T data){
        root = remove(data, root);
        System.out.println("removed...");
        return this;
    }

    // Função de busca de elemento
    public boolean search(T data) {
        return search(root, data);
    }

    // Metodo para exibir a arvore em ordem
    public void inOrder(){
        inOrder(root);
    }

    // Metodo para exibir a arvore em pré ordem
    public void preOrder(){
        preOrder(root);
    }
    
    // Metodo para exibir a arvore em pos ordem
    public void postOrder(){
        postOrder(root);
    }

//////////////////////////////////////
        /* Funções privadas */
/////////////////////////////////////
    
    // função para inserir dado na arvore
    private Node<T> insert(T data, Node<T> node) {
        if (node == null) 
            return new Node<>(data);
        if (data.compareTo(node.getData()) < 0)
            node.setLeft(insert(data, node.getLeft()));
        else if (data.compareTo(node.getData()) > 0)
            node.setRight(insert(data, node.getRight()));
        else 
            return node;
        updateHeight(node);
        return balanced(node);
    }


    // função para remover node da arvore
    private Node<T> remove(T data, Node<T> node) {
        if (node == null) {
            return null;
        }
        if (data.compareTo(node.getData()) < 0)
            node.setLeft(remove(data, node.getLeft()));
        else if (data.compareTo(node.getData()) > 0) {
            node.setRight(remove(data, node.getRight()));
        } else {
            // caso o node seja uma folha ou só tenha um filho
            if (node.getLeft() == null) {
                return node.getRight();
            } else if (node.getRight() == null) {
                return node.getLeft();
            }
            // se o nó tiver sucessores dos dois lados
            node.setData(max(node.getLeft()));
            node.setLeft(remove(node.getData(), node.getLeft()));
        }
        updateHeight(node);
        return balanced(node);
    }


    // função para exibir a arvore em pós ordem
    private void postOrder(Node<T> node) {
        if (node != null) {
            postOrder(node.getLeft());
            postOrder(node.getRight());
            System.out.println("data---> " + node.getData());
            System.out.println("altura-> " + node.getHeight());
        }
    }

    // função para exibir a arvore e pré ordem
    private void preOrder(Node<T> node) {
        if (node != null) {
            System.out.println("data---> " + node.getData());
            System.out.println("altura-> " + node.getHeight());
            preOrder(node.getLeft());
            preOrder(node.getRight());
        }
    }

    // função para exibir o a arvore em ordem, esquerda, raiz, direita
    private void inOrder(Node<T> node) {
        if (node != null) {
            inOrder(node.getLeft());
            System.out.println("data---> " + node.getData());
            System.out.println("altura-> " + node.getHeight());
            inOrder(node.getRight());
        }
    }

    // função para encontrar elemento na arvore
    private boolean search(Node<T> node, T data) {
        if (node == null) {
            return false;                           // Não encontrou o elemento
        }

        int compareResult = data.compareTo(node.getData());

        if (compareResult == 0)
            return true;                            // Encontrou o elemento
        else if (compareResult < 0)
            return search(node.getLeft(), data);    // Busca na subárvore esquerda
        else 
            return search(node.getRight(), data);   // Busca na subárvore direita

    }

    // para encontrar o node minimo
    private T min(Node<T> node){
        /**
         * A função é chamada recursivamente ate ele encontrar um node null, que seria o valor minimo
         * caso não encontre nenhum null, retorna o data do ultimo node
         */
        if(node.getLeft()!= null){
            return min(node.getLeft()); 
        }
        return node.getData();
    }

    // para encontrar o node máximo, utiliza a mesma lógica para achar o node minimo
    private T max(Node<T> node){
        if(node.getRight()!= null){
            return min(node.getRight());
        }
        return node.getData();
    }

    // função que atualiza altura do nó
    private void updateHeight(Node<T> node) {
        //calculando altura máxima entre os sucessores do nó
        int maxHeight = Math.max(
                height(node.getLeft()),
                height(node.getRight())
        );
        node.setHeight(maxHeight + 1);  //atualiza a altura, incrementando 1 para considerar o próprio nó
    }


    //Calcular fator de balanceamento
    private int FB(Node<T> node){
        if(node != null)
            return (height(node.getLeft()) - height(node.getRight()));
        else
            return 0;
    }


    //Metodo para que retorna a altura do nó
    private int height(Node<T> node){
        if(node != null)
            return node.getHeight();
        else
            return 0;
    }


    // Metodo para aplicar as rotações e balancear a árvore
    private Node<T> balanced(Node<T> node) {
        
        // pegando o fator de balanceamento atual da arvore
        int fb = FB(node);
        
        // se o fator de balanceamento da arvore foi maior significa que a subarvore a esquerda esta desbalanceada
        if (fb > 1) {
            if (FB(node.getLeft()) < 0) {
                node.setLeft(simpleLeft(node.getLeft()));
            }
            return simpleRight(node);
        }

        // se o fator for menor que -1, significa que a subarvore a direita esta desbalanceada
        if (fb < -1) {
            if (FB(node.getRight()) > 0) {
                node.setRight(simpleRight(node.getRight()));
            }
            return simpleLeft(node);
        }

        // retorna node balanceado
        return node;
    }
    
    ///////////////////////////////
            /* Rotações */
    ///////////////////////////////

    //Rotação simples a esquerda
    private Node<T> simpleLeft(Node<T> node) {
        Node<T> rightNode = node.getRight();
        Node<T> leftNode = rightNode.getLeft();
        
        //Definindo nó esquerdo do nó direito filho como o nó original
        rightNode.setLeft(node);
        //definindo o nó direito do filho como o nó esquerto
        node.setRight(leftNode);
        
        //atualizando alturas
        updateHeight(node);
        updateHeight(rightNode);

        return rightNode;
    }

    //Rotação simples a direita
    private Node<T> simpleRight(Node<T> node) {
        //Função segue o mesmo modelo da rotação a esquerda
        Node<T> leftNode = node.getLeft();
        Node<T> rightNode = leftNode.getRight();
        leftNode.setRight(node);
        node.setLeft(rightNode);
        updateHeight(node);
        updateHeight(leftNode);
        return leftNode;
    }
}