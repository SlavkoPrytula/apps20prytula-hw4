package ua.edu.ucu.itterator;
import ua.edu.ucu.queue.Queue;

import java.util.List;

public class NodeIterator implements Iterator<String> {
    List<String> trie;

    public NodeIterator(List<String> obj) {
        this.trie = obj;
    }

    public Object next() {
        return null;
    }

    public boolean hasNext() {
        return true;
    }
}
