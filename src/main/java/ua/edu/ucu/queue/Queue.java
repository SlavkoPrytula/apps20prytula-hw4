package ua.edu.ucu.queue;

import ua.edu.ucu.item.NodeCollection;
import ua.edu.ucu.itterator.NodeIterator;

import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;

public class Queue<E> implements Iterable<String> {
    private final List<String> queue;
    NodeCollection nodes;

    public Queue() {
        this.queue = new ArrayList<>();
    }

    public Object peek() {
        return queue.get(0);
    }

    public Object dequeue() {
        return queue.remove(0);
    }

    public void enqueue(String e) {
        queue.add(e);
    }

    @Override
    public Iterator<String> iterator() {
//        NodeIterator iterator = new NodeIterator(queue);
//        return (Iterator<String>) nodes.createIterator();
        return null;
    }
}