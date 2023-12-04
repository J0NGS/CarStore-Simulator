package src.Server.Compression.Huffman;

import java.io.Serializable;
import java.util.*;
import java.util.Map.Entry;

public class HuffmanCompress implements Serializable {
    // mapeia os caracteres para suas respectivas frequências
    private Map<Character, Integer> charFrequencyMap;
    // a raiz da árvore de Huffman
    private HuffmanNode huffmanRoot;
    // o texto que será comprimido ou descomprimido
    private Character[] text;
    // gerenciador de fila de prioridade para construir a árvore de Huffman
    private HuffmanHeapManager priorityQueueManager;
    // contador para controlar o número de elementos na árvore
    private Integer itemCount;

    public HuffmanCompress() {
        // inicializa o mapa e o gerenciador de fila de prioridade
        this.charFrequencyMap = new HashMap<>();
        this.priorityQueueManager = new HuffmanHeapManager();
        this.huffmanRoot = null;
        this.itemCount = 0;
    }

    public void reset() {
        // reseta a árvore, o mapa e o texto
        resetTree(this.huffmanRoot);
        this.charFrequencyMap.clear();
        this.text = null;
    }

    private void resetTree(HuffmanNode node) {
        // reseta a árvore de forma recursiva
        if (node != null) {
            resetTree(node.getLeft());
            resetTree(node.getRight());
            node = null;
        }
    }

    private void computeCharFrequencies() {
        // calcula a frequência de cada caractere no texto
        int charFrequency = 1;
        for (int i = 0; i < text.length; i++) {
            if (this.charFrequencyMap.containsKey(text[i])) {
                this.charFrequencyMap.replace(text[i], this.charFrequencyMap.get(text[i]), (this.charFrequencyMap.get(text[i]) + 1));
            } else {
                this.charFrequencyMap.put(text[i], charFrequency);
            }
        }
    }

    private void constructPriorityQueue() {
        // constrói a fila de prioridade a partir do mapa de frequências
        Set<Map.Entry<Character, Integer>> set = this.charFrequencyMap.entrySet();

        for (Map.Entry<Character, Integer> entry : set) {
            this.priorityQueueManager.add(new HuffmanNode(entry.getKey(), entry.getValue()));
            itemCount++;
        }
    }

    public Character[] compress(String text) {
        // converte a string de entrada para um array de caracteres
        this.text = stringToCharacterArray(text);

        // constrói a árvore de Huffman
        buildHuffmanTree();

        // comprime o texto e retorna como um array de caracteres
        StringBuffer stringBuffer = new StringBuffer();
        for (int i = 0; i < text.length(); i++) {
            searchInTree(this.text[i], stringBuffer);
        }

        return stringToCharacterArray(stringBuffer.toString());
    }

    private Character[] stringToCharacterArray(String text) {
        // converte uma string para um array de caracteres
        Character[] characters = new Character[text.length()];

        for (int i = 0; i < text.length(); i++) {
            characters[i] = text.charAt(i);
        }
        return characters;
    }

    public String decompress(Character[] code) {
        // descomprime o código de Huffman e retorna como uma string
        StringBuffer stringBuffer = new StringBuffer();
        Integer counter = 0;
        decompressTree(this.huffmanRoot, code, counter, stringBuffer);
        return stringBuffer.toString();
    }

    private void decompressTree(HuffmanNode node, Character[] code, Integer counter, StringBuffer stringBuffer) {
        // descomprime de forma recursiva o código de Huffman
        if (node == null)
            return;

        if (isLeafNode(node)) {
            stringBuffer.append(node.getCharacter());
            decompressTree(this.huffmanRoot, code, counter, stringBuffer);
        }

        if (counter == (code.length))
            return;

        if (String.valueOf(code[counter]).equals("0")) {
            counter++;
            decompressTree(node.getLeft(), code, counter, stringBuffer);
        } else if (String.valueOf(code[counter]).equals("1")) {
            counter++;
            decompressTree(node.getRight(), code, counter, stringBuffer);
        }
    }

    private void searchInTree(Character character, StringBuffer stringBuffer) {
        // realiza a busca de um caractere na árvore de Huffman
        searchInTree(this.huffmanRoot, character, stringBuffer);
    }

    private void searchInTree(HuffmanNode node, Character character, StringBuffer stringBuffer) {
        // realiza a busca de um caractere na árvore de Huffman de forma recursiva
        if (isLeafNode(node))
            return;

        if (node.getLeft().getCharacter() == character) {
            stringBuffer.append("0");
            searchInTree(node.getLeft(), character, stringBuffer);
        } else {
            stringBuffer.append("1");
            searchInTree(node.getRight(), character, stringBuffer);
        }
    }

    private void buildHuffmanTree() {
        // calcula as frequências, constrói a fila de prioridade e a árvore de Huffman
        computeCharFrequencies();
        constructPriorityQueue();

        for (int i = 0; i < itemCount; i++) {
            insertIntoTree(this.priorityQueueManager.delete());
        }
    }

    private void insertIntoTree(HuffmanNode value) {
        // insere um novo nó na árvore de Huffman
        if (isTreeEmpty()) {
            this.huffmanRoot = value;
        } else {
            this.huffmanRoot = insertIntoTree(this.huffmanRoot, value);
        }
    }

    private HuffmanNode insertIntoTree(HuffmanNode node, HuffmanNode newNode) {
        // insere um novo nó na árvore de Huffman de forma recursiva
        int totalCount = node.getFrequency() + newNode.getFrequency();
        HuffmanNode newRoot = new HuffmanNode('*', totalCount);
        newRoot.setRight(node);
        newRoot.setLeft(newNode);
        return newRoot;
    }

    private boolean isLeafNode(HuffmanNode node) {
        // verifica se um nó é uma folha da árvore
        return node.getLeft() == null && node.getRight() == null;
    }

    private boolean isTreeEmpty() {
        // verifica se a árvore está vazia
        return this.huffmanRoot == null;
    }

    public void display() {
        // imprime o mapa de caracteres e suas frequências
        System.out.println(this.charFrequencyMap.toString());
    }
}

