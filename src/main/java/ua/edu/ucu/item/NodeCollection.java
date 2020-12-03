package ua.edu.ucu.item;

import ua.edu.ucu.itterator.Iterator;
import ua.edu.ucu.itterator.NodeIterator;
import ua.edu.ucu.queue.Queue;

public class NodeCollection implements Collection {
     private final Queue<String> nodes;

    public NodeCollection(Queue<String> q) {
        this.nodes = q;
    }

    public Iterator<String> createIterator() {
        return new NodeIterator(nodes);
    }
}
