package src.Server.Compression.Huffman;

import java.io.Serializable;

public class HuffmanNode implements Comparable<HuffmanNode>, Serializable {
    private char character;
    private int frequency;
    private HuffmanNode left;
    private HuffmanNode right;

    public HuffmanNode(char character, int frequency) {
        this.character = character;
        this.frequency = frequency;
    }

    public HuffmanNode(char character, int frequency, HuffmanNode left, HuffmanNode right) {
        this.character = character;
        this.frequency = frequency;
        this.left = left;
        this.right = right;
    }

    public boolean isLeaf() {
        return left == null && right == null;
    }
    

    public HuffmanNode() {
    }

    public char getCharacter() {
        return character;
    }

    public int getFrequency() {
        return frequency;
    }

    public HuffmanNode getLeft() {
        return left;
    }

    public HuffmanNode getRight() {
        return right;
    }

    public void setCharacter(char character) {
        this.character = character;
    }
    public void setFrequency(int frequency) {
        this.frequency = frequency;
    }
    public void setLeft(HuffmanNode left) {
        this.left = left;
    }
    public void setRight(HuffmanNode right) {
        this.right = right;
    }


    @Override
    public int compareTo(HuffmanNode other) {
        return this.frequency - other.frequency;
    }

    public void setValue(HuffmanNode parent) {
        this.character = parent.getCharacter();
        this.frequency = parent.getFrequency();
        this.left = parent.getLeft();
        this.right = parent.getRight();
    }


    @Override
    public String toString() {
        return "{" +
            " character='" + getCharacter() + "'" +
            ", frequency='" + getFrequency() + "'" +
            ", left='" + getLeft() + "'" +
            ", right='" + getRight() + "'" +
            "}";
    }

}

