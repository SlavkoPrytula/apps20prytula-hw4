package ua.edu.ucu.queue;

import ua.edu.ucu.item.NodeCollection;
import ua.edu.ucu.itterator.Iterator;
import ua.edu.ucu.tries.RWayTrie;

import java.util.ArrayList;

public class Queue<E> implements Iterable<String> {
    private final ArrayList<String> queue;

    public Queue() {
        this.queue = new ArrayList<>();
    }

    public Queue(ArrayList<String> arrayList) {
        this.queue = new ArrayList<>();
        queue.addAll(arrayList);
    }

    public String peek() {
        return queue.get(0);
    }

    public String dequeue() {
        return queue.remove(0);
    }

    public void enqueue(String e) {
        queue.add(e);
    }

    public int size() {
        return queue.size();
    }

    @Override
    public Iterator<String> iterator() {
        Queue<String> q = new Queue<>(queue);
        NodeCollection nodes = new NodeCollection(q);
        return nodes.createIterator();
    }
}