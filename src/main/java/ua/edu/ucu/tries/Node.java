package ua.edu.ucu.tries;

public class Node {
    static final int DEFAULT_CAPACITY = 26;
    private Object index = null;
    private Object data = null;
    private final Node[] next = new Node[DEFAULT_CAPACITY];

    public Object getIndex() {
        return index;
    }

    public Object getData() {
        return data;
    }

    public void setIndex(Object index) {
        this.index = index;
    }

    public void setData(Object data) {
        this.data = data;
    }

    public Node[] getNext() {
        return next;
    }

    public int getDefaultCapacity() {
        return DEFAULT_CAPACITY;
    }
}
