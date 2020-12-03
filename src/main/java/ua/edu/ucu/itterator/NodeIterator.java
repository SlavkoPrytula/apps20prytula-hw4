package ua.edu.ucu.itterator;

import ua.edu.ucu.queue.Queue;


public class NodeIterator implements Iterator<String> {
    Queue<String> queue;

    public NodeIterator(Queue<String> obj) {
        this.queue = obj;
    }

    public String next() {
        return queue.dequeue();
    }

    public boolean hasNext() {
        return queue.queue.size() > 0
                && queue.peek() != null;
    }
}
