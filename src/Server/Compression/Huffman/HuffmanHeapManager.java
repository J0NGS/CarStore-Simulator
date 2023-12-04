package src.Server.Compression.Huffman;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;

public class HuffmanHeapManager implements Serializable{
    private List<HuffmanNode> nodeList;

    public HuffmanHeapManager() {
        this.nodeList = new ArrayList<>();
    }

    public void add(HuffmanNode value) {
        if (this.nodeList.isEmpty()) {
            nodeList.add(value);
        } else {
            nodeList.add(value);
            heapifyUp(nodeList.size() - 1);
        }
    }

    public HuffmanNode delete() {
        if (this.nodeList.isEmpty()) {
            return null;
        } else {
            HuffmanNode tmp = this.nodeList.get(0);
            this.nodeList.set(0, this.nodeList.get(this.nodeList.size() - 1));
            this.nodeList.remove(this.nodeList.size()-1);
            heapifyDown(0, this.nodeList.size());
            return tmp;
        }
    }

    private int floor(double number) {
        return (int) Math.floor(number);
    }

    private void heapifyUp(int position) {
        int j = floor((position - 1) / 2);

        if (j >= 0 && (nodeList.get(j).getFrequency() > nodeList.get(position).getFrequency())) {
            HuffmanNode temp = nodeList.get(j);
            nodeList.set(j, nodeList.get(position));
            nodeList.set(position, temp);
            heapifyUp(j);
        }
    }

    private void heapifyDown(int position, int size) {
        int j = (position * 2) + 1;

        if (j < size) {
            if (j < size - 1) {
                if (nodeList.get(j + 1).getFrequency() < nodeList.get(j).getFrequency()) {
                    j++;
                }
            }
            if (nodeList.get(position).getFrequency() > nodeList.get(j).getFrequency()) {
                HuffmanNode temp = nodeList.get(j);
                nodeList.set(j, nodeList.get(position));
                nodeList.set(position, temp);
                heapifyDown(j, size);
            }
        }
    }
}
