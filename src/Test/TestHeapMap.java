package src.Test;

import src.ED.LinkedList.Node;
import src.Server.Compression.Huffman.HuffmanHeapManager;
import src.Server.Compression.Huffman.HuffmanNode;

public class TestHeapMap {
       public static void main(String[] args) {
        HuffmanHeapManager heap = new HuffmanHeapManager();

        heap.add(new HuffmanNode('a', 1));
        heap.add(new HuffmanNode('b', 2));
        heap.add(new HuffmanNode('c', 3));
        heap.add(new HuffmanNode('d', 4));
        heap.add(new HuffmanNode('e', 5));

        HuffmanNode minNode = heap.delete();
        System.out.println("Menor nó extraído: " + minNode.getCharacter() + " -> " + minNode);

        minNode = heap.delete();
        System.out.println("Menor nó extraído: " + minNode.getCharacter() + " -> " + minNode.getFrequency());

    }
}

