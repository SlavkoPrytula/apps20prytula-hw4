package ua.edu.ucu.tries;

import ua.edu.ucu.queue.Queue;

public class RWayTrie implements Trie {
    static final int ASCII_SUBTRACT = 97;
    private final Node root = new Node();

    @Override
    public void add(Tuple t) {
        if (t.term == null) {
            throw new NullPointerException();
        }
        Node node = root;

        for (char chr : t.term.toCharArray()) {
            Node nextNode = new Node();
            if (node.getNext()[chr - ASCII_SUBTRACT] != null) {
                node = node.getNext()[chr - ASCII_SUBTRACT];
                continue;
            }
            node.getNext()[chr - ASCII_SUBTRACT] = nextNode;
            nextNode.setData(chr);
            node = nextNode;

            if (chr == t.term.charAt(t.term.length() - 1)) {
                node.setIndex(t.weight);
            }
        }
    }

    @Override
    public boolean contains(String word) {
        Node node = root;

        for (char chr : word.toCharArray()) {
            if (node.getNext()[chr - ASCII_SUBTRACT] != null
                    && node.getNext()[chr
                    - ASCII_SUBTRACT].getData() != null
                    && node.getNext()[chr
                    - ASCII_SUBTRACT].getData().equals(chr)) {
                node = node.getNext()[chr - ASCII_SUBTRACT];
            } else {
                return false;
            }
        }
        return true;
    }

    @Override
    public boolean delete(String word) {
        if (word == null) {
            throw new NullPointerException();
        }
        return delete(root.getNext()[word.charAt(0)
                        - ASCII_SUBTRACT],
                word, 1) != null;
    }

    private static int countElements(Node[] node) {
        int count = 0;
        for (Node el : node) {
            if (el != null) {
                count++;
            }
        }
        return count;
    }

    private static Node delete(Node node, String word, int index) {
        char chr = word.charAt(index);
        if (node == null) {
            return null;
        }

        if (index == word.length() - 1) {
            node.getNext()[chr - ASCII_SUBTRACT] = null;
            return node;
        } else {
            node.getNext()[chr - ASCII_SUBTRACT]
                    = delete(node.getNext()[chr - ASCII_SUBTRACT],
                    word,
                    index + 1);
        }
        if (countElements(node.getNext()) == 1) {
            node.getNext()[chr - ASCII_SUBTRACT] = null;
        }
        if (node.getData() != null) {
            return node;
        }

        for (char c = 0; c < node.getDefaultCapacity(); c++) {
            if (node.getNext()[c] != null) {
                return node;
            }
        }
        return null;
    }

    @Override
    public Iterable<String> words() {
        return wordsWithPrefix("");
    }

    @Override
    public Iterable<String> wordsWithPrefix(String pref) {
        Queue<String> queue = new Queue<>();
        Node node = root;
        for (char chr : pref.toCharArray()) {
            if (node.getNext()[chr - ASCII_SUBTRACT] != null) {
                node = node.getNext()[chr - ASCII_SUBTRACT];
            }
        }
        getWords(node, queue, pref);
        return queue;
    }

    public void getWords(Node node,
                         Queue<String> queue, String pref) {
        if (node == null) {
            return;
        }
        if (node.getIndex() != null) {
            queue.enqueue(pref);
        }
        for (char c = 0; c < node.getDefaultCapacity(); c++) {
            getWords(node.getNext()[c], queue,
                    pref + (char) (c + ASCII_SUBTRACT));
        }
    }

    @Override
    public int size() {
        return size(root);
    }

    private int size(Node node) {
        if (node == null) {
            return 0;
        }
        int counter = 0;
        if (node.getIndex() != null) {
            counter++;
        }
        for (char c = 0; c < node.getDefaultCapacity(); c++) {
            counter += size(node.getNext()[c]);
        }
        return counter;
    }
}
