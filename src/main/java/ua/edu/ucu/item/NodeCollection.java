package ua.edu.ucu.item;


import ua.edu.ucu.itterator.Iterator;
import ua.edu.ucu.itterator.NodeIterator;
import ua.edu.ucu.queue.Queue;
import ua.edu.ucu.tries.Tuple;

import java.util.ArrayList;
import java.util.List;

public class NodeCollection implements Collection {
     List<String> words;

    public NodeCollection() {
        words = new ArrayList<>();
    }

    public void addItem(String str) {
        Tuple tuple = new Tuple(str, str.length());
//        words.enqueue(tuple);
//        words.add();
    }

    public Iterator<String> createIterator() {
        return new NodeIterator(words);
    }
}
